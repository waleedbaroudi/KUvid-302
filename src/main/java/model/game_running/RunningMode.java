package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_entities.*;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.EntityGeneratorRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.runnables.ShooterMovementRunnable;
import model.game_running.states.GameState;
import model.game_running.states.PausedState;
import model.game_running.states.RunningState;
import model.game_space.Blender;
import model.game_space.GameStatistics;
import model.game_space.Player;
import org.apache.log4j.Logger;
import services.database.MongoDBAdapter;
import services.utils.IOHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    public Logger logger = Logger.getLogger(this.getClass().getName());
    private Player player;

    // game state
    GameState currentState, pausedState, runningState;

    //space objects
    private  CopyOnWriteArrayList<AutonomousEntity> autonomousEntities;
    private ProjectileContainer projectileContainer;
    private Shooter shooter;

    private boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    private final RunningStateListener runningStateListener;
    private final GameEntitiesListener gameEntitiesListener;
    private final SessionLoader.SessionLoadListener sessionLoadListener;

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
    private Blender blender;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener, SessionLoader.SessionLoadListener sessionLoadListener) {
        autonomousEntities = new CopyOnWriteArrayList<>();

        Configuration config = Configuration.getInstance();

        runningState = new RunningState(this);
        pausedState = new PausedState(this);
        currentState = runningState;

        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;
        this.sessionLoadListener = sessionLoadListener;

        this.projectileContainer = new ProjectileContainer(
                this,
                config.getNumAlphaAtoms(),
                config.getNumBetaAtoms(),
                config.getNumSigmaAtoms(),
                config.getNumGammaAtoms());

        this.blender = new Blender(this.projectileContainer);
        this.shooter = new Shooter(projectileContainer);
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
        if (state == GameConstants.GAME_STATE_PAUSED)
            currentState = pausedState;
        else if (state == GameConstants.GAME_STATE_RESUMED)
            currentState = runningState;
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

    public SessionLoader.SessionLoadListener getSessionLoadListener() {
        return this.sessionLoadListener;
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
        gameEntitiesListener.onEntitiesRemove(tmp);
        autonomousEntities.remove(entity);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateStatisticsProjectileCount(SuperType type, EntityType entityType, int newCount) {
        if (player != null)
            player.updateOwnedProjectiles(type, entityType, newCount);
    }

    public void updateHealth(int damageAmount) {
        if (player != null)
            if (player.loseHealth(damageAmount))
                this.setRunningState(GameConstants.GAME_STATE_STOP);
    }

    public void updateTimer(int amountInMillis) {
        if (player != null) {
            boolean timerOver = !player.updateTime(amountInMillis);
            if (timerOver)
                endGame();
        }
    }

    public void endGame() {
        setRunningState(GameConstants.GAME_STATE_STOP);
        runningStateListener.onGameOver();
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

    public Player getPlayer() {
        return player;
    }

    public void increaseScore() {
        if (player != null) player.incrementScore();
    }

    public void collectPowerUp(Powerup powerup) {
        projectileContainer.addPowerUp(powerup);
    }

    public boolean isGameFinished() {
        return shooter.getCurrentProjectile() == null && noAtomsOnScreen();
    }


    public void showSavedSessions() {
        currentState.showSavedSessions();
    }

    public void loadGameSession(GameBundle session) {

        // update the game configuration
        Configuration.resetConfig(session.getConfig());

        // update the entities in the game view
        this.autonomousEntities.clear();
        this.autonomousEntities.addAll(session.getAtoms());
        this.autonomousEntities.addAll(session.getBlockers());
        this.autonomousEntities.addAll(session.getMolecules());
        this.autonomousEntities.addAll(session.getPowerUps());

        // update the projectile containers
        this.projectileContainer = session.getProjectileContainer();
        this.projectileContainer.setRunningMode(this);

        // update the shooter state
        this.shooter = session.getShooter();
        this.shooter.setContainer(this.projectileContainer);

        // update the player state and statistics listener
        GameStatistics.GameStatisticsListener listener = this.player.getStatisticsListener();
        this.player = session.getPlayer();
        this.player.setStatisticsListener(listener);

        // update runnables
        this.blender = new Blender(this.projectileContainer);
        this.shooterRunnable.setShooter(this.shooter);
        this.entityGeneratorRunnable.initializeMaps();

        // reflect the changes in the UI
        gameEntitiesListener.onGameReset();
        for (AutonomousEntity entity : this.autonomousEntities){
            gameEntitiesListener.onEntityAdd(entity);
        }

    }
    public void saveGameRequest(){
        this.currentState.saveGameSession();
    }

    public void saveGameSession() {
        GameBundle.Builder builder = new GameBundle.Builder();
        builder.setPlayer(getPlayer()).
                setShooter(getShooter()).
                setProjectileContainer(getProjectileContainer()).
                setConfig(Configuration.getInstance());

        getAutonomousEntities().forEach(entity -> entity.saveState(builder));

        GameBundle bundle = builder.build();
        String fileName = IOHandler.formatFileNameWithDate("Session1", ""); // TODO: Take name from user
        try {
            MongoDBAdapter.getInstance().save(GameConstants.SESSION_COLLECTION_TITLE, fileName, bundle); //
        } catch (IOException e) {
            logger.error("Could not save the game session", e);
        }
    }

    public interface RunningStateListener {
        void onRunningStateChanged(int state);

        void onGameOver();
    }

    public interface GameEntitiesListener {
        void onEntityAdd(AutonomousEntity entity);

        void onEntitiesRemove(Collection<AutonomousEntity> entities);

        /**
         * Reset all game components in the UI
         */
        void onGameReset();
    }

}
