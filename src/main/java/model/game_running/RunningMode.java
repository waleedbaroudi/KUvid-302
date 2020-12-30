package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.AutonomousEntity;
import model.game_entities.Powerup;
import model.game_entities.Entity;
import model.game_entities.Projectile;
import model.game_entities.Shooter;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.ShieldType;
import model.game_entities.enums.SuperType;
import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.runnables.ShooterMovementRunnable;
import model.game_running.runnables.EntityGeneratorRunnable;
import model.game_space.Blender;
import model.game_space.GameStatistics;
import org.apache.log4j.Logger;
import utils.Coordinates;
import utils.MathUtils;
import ui.windows.RunningWindow;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    public Logger logger = Logger.getLogger(this.getClass().getName());
    private GameStatistics statistics;

    //space objects
    private final CopyOnWriteArrayList<AutonomousEntity> autonomousEntities;
    private final ProjectileContainer projectileContainer;
    private final Shooter shooter;
    private final ShieldHandler shieldHandler;
    private boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    private final RunningStateListener runningStateListener;
    private final GameEntitiesListener gameEntitiesListener;

    // Runnables
    private MovementRunnable movementRunnable;
    private CollisionRunnable collisionRunnable;
    private ShooterMovementRunnable shooterRunnable;
    private EntityGeneratorRunnable entityGeneratorRunnable;

    // Threads
    private Thread movementThread;
    private Thread collisionThread;
    private Thread shooterThread;
    private Thread entityGeneratorThread;

    // Blender
    private final Blender blender;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener) {
        autonomousEntities = new CopyOnWriteArrayList<>();

        Configuration config = Configuration.getInstance();

        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;

        this.projectileContainer = new ProjectileContainer(
                this,
                config.getNumAlphaAtoms(),
                config.getNumBetaAtoms(),
                config.getNumSigmaAtoms(),
                config.getNumGammaAtoms(),
                0,
                0,
                0,
                0);


        this.blender = new Blender(this.projectileContainer);
        this.shooter = new Shooter(projectileContainer);
        this.shieldHandler = new ShieldHandler(this);
        initialize();
    }

    /**
     * instantiates the threads and runnables. fills the list. sets "initialized" to true
     */
    private void initialize() {
        movementRunnable = new MovementRunnable(this.autonomousEntities);
        collisionRunnable = new CollisionRunnable(this, new CollisionHandler(this));
        shooterRunnable = new ShooterMovementRunnable(this.shooter);
        entityGeneratorRunnable = new EntityGeneratorRunnable(this);

        movementThread = new Thread(this.movementRunnable);
        collisionThread = new Thread(this.collisionRunnable);
        shooterThread = new Thread(this.shooterRunnable);
        entityGeneratorThread = new Thread(this.entityGeneratorRunnable);

        this.isInitialized = true;
    }

    public MovementRunnable getMovementRunnable() {
        return movementRunnable;
    }

    /**
     * starts the Movement, collision, shooter, and EntityGenerator threads
     */
    public void startThreads() {
        if (!isInitialized) {
            logger.error("Game is not yet initialized");
            return;
        }

        // Starting the threads.
        movementThread.start();
        collisionThread.start();
        shooterThread.start();
        entityGeneratorThread.start();
    }

    /**
     * calls pause on all runnables and interrupts all threads.
     */
    public void stop() {
        setRunningState(GameConstants.GAME_STATE_PAUSED);
        movementThread.interrupt();
        collisionThread.interrupt();
        shooterThread.interrupt();
    }

    /**
     * Pauses/Resumes/Stops all runnables.
     */
    public void setRunningState(int state) {
        runningStateListener.onRunningStateChanged(state);
        movementRunnable.setRunnableState(state);
        collisionRunnable.setRunnableState(state);
        shooterRunnable.setRunnableState(state);
        entityGeneratorRunnable.setRunnableState(state);
    }

    public void moveShooter(int direction) {
        shooterRunnable.setMovementState(direction);
    }

    public void rotateShooter(int direction) {
        shooterRunnable.setRotationState(direction);
    }

    /**
     * static so that all classes
     *
     * @return returns the list of autonomous entities
     */
    public CopyOnWriteArrayList<AutonomousEntity> getAutonomousEntities() {
        return autonomousEntities;
    }

    /**
     * @param entity the entity to be added to the list of entities
     * @return a boolean indicating whether the entity was added successfully
     */
    public boolean addEntity(AutonomousEntity entity) {
        gameEntitiesListener.onEntityAdd(entity);
        return autonomousEntities.add(entity);
    }

    /**
     * Shoot entity at the tip of the Shooter
     */
    public void shootProjectile() {
        Projectile shotEntity = this.shooter.shoot();
        if (shotEntity == null) {
            if (noAtomsOnScreen())
                endGame();
            return;
        }
        // projectileContainer.decreaseAtoms(shotEntity.getType().getValue(), 1);
        addEntity(shotEntity);
    }

    public boolean noAtomsOnScreen() {
        for (Entity entity : autonomousEntities)
            if (entity.getSuperType() == SuperType.ATOM)
                return false;
        return true;
    }

    /**
     * @param removedEntities autonomous entities to be removed from the list of elements in the space
     */
    public void removeAutonomousEntities(Collection<AutonomousEntity> removedEntities) {
        gameEntitiesListener.onEntitiesRemove(removedEntities);
        autonomousEntities.removeAll(removedEntities);
    }

    /**
     * TODO ADD DOCUMENTATION
     *
     * @param entity to be removed
     */
    public void removeEntity(AutonomousEntity entity) {
        // TODO: change the gamerListener to removeEntity. Handle multiple entities by calling removeEntity on them one by one.
        ArrayList<AutonomousEntity> tmp = new ArrayList<>();
        tmp.add(entity);
        autonomousEntities.remove(entity);
        gameEntitiesListener.onEntitiesRemove(tmp);
    }

    public void setStatisticsController(GameStatistics gameStatistics) {
        this.statistics = gameStatistics;
    }

    public void updateStatisticsProjectileCount(SuperType type, EntityType entityType, int newCount) {
        if (statistics != null)
            statistics.changeProjectileCount(type, entityType, newCount);
    }

    public void updateStatisticsShieldCount(ShieldType type, int newCount) {
        if (statistics != null)
            statistics.changeShieldCount(type, newCount);
    }

    public void updateHealth(int damageAmount) {
        if (statistics != null)
            if (statistics.decreaseHealth(damageAmount))
                this.setRunningState(GameConstants.GAME_STATE_STOP);
    }

    public void updateTimer(int amountInMillis) {
        if (statistics != null) {
            boolean timerOver = !statistics.updateTimer(amountInMillis);
            if (timerOver)
                endGame();
        }
    }

    public void endGame() {
        setRunningState(GameConstants.GAME_STATE_STOP);
        runningStateListener.onGameOver();
    }

    public void switchAtom() {
        getShooter().switchAtom();
    }

    public Blender getBlender() {
        return this.blender;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public ProjectileContainer getProjectileContainer() {
        return this.projectileContainer;
    }

    public void increaseScore() {
        statistics.incrementScore();
    }

    public void collectPowerUp(Powerup powerup) {
        projectileContainer.addPowerUp(powerup);
    }

    public boolean isGameFinished() {
        return shooter.getCurrentProjectile() == null && noAtomsOnScreen();
    }


    public void applyEtaShield() {
        if (shooter.projectileIsAtom())
            shooter.setCurrentProjectile(shieldHandler.applyEtaShield(shooter.getAtomProjectile()));
    }

    public void applyLotaShield() {
        if (shooter.projectileIsAtom())
            shooter.setCurrentProjectile(shieldHandler.applyLotaShield(shooter.getAtomProjectile()));
    }

    public void applyThetaShield() {
        if (shooter.projectileIsAtom())
            shooter.setCurrentProjectile(shieldHandler.applyThetaShield(shooter.getAtomProjectile()));
    }

    public void applyZetaShield() {
        if (shooter.projectileIsAtom())
            shooter.setCurrentProjectile(shieldHandler.applyZetaShield(shooter.getAtomProjectile()));
    }


    public interface RunningStateListener {
        void onRunningStateChanged(int state);

        void onGameOver();
    }

    public interface GameEntitiesListener {
        void onEntityAdd(AutonomousEntity entity);

        void onEntitiesRemove(Collection<AutonomousEntity> entities);
    }
}
