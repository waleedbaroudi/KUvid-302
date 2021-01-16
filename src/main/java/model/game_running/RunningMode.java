package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_entities.*;
import model.game_entities.enums.ShieldType;
import model.game_entities.enums.SuperType;
import model.game_running.runnables.*;
import model.game_running.states.GameState;
import model.game_running.states.PausedState;
import model.game_running.states.RunningState;
import model.game_space.Blender;
import model.game_space.GameStatistics;
import model.game_space.Player;
import org.apache.log4j.Logger;
import services.database.IDatabase;

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
    private GameStatistics statistics;

    // game state
    GameState currentState, pausedState, runningState;

    //space objects
    private final CopyOnWriteArrayList<AutonomousEntity> autonomousEntities;
    private ProjectileContainer projectileContainer;
    private Shooter shooter;
    private ShieldHandler shieldHandler;
    private boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listeners
    private final RunningStateListener runningStateListener;
    private final GameEntitiesListener gameEntitiesListener;
    private final SessionLoader.SessionLoadListener sessionLoadListener;
    private final SaveSessionListener saveSessionListener;

    // Runnables
    private ArrayList<GameRunnable> gameRunnables;
    private MovementRunnable movementRunnable;
    private CollisionRunnable collisionRunnable;
    private ShooterMovementRunnable shooterRunnable;
    private EntityGeneratorRunnable entityGeneratorRunnable;
    private boolean outOfEntities; // flags that the entity generator runnable has run out of entities to drop

    // Threads
    private Thread movementThread;
    private Thread collisionThread;
    private Thread shooterThread;
    private Thread entityGeneratorThread;

    // Blender
    private Blender blender;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener, SessionLoader.SessionLoadListener sessionLoadListener, SaveSessionListener saveSessionListener) {
        autonomousEntities = new CopyOnWriteArrayList<>();

        Configuration config = Configuration.getInstance();

        runningState = new RunningState(this);
        pausedState = new PausedState(this);
        currentState = runningState;

        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;
        this.sessionLoadListener = sessionLoadListener;
        this.saveSessionListener = saveSessionListener;


        this.projectileContainer = new ProjectileContainer(
                this,
                config.getNumAlphaAtoms(),
                config.getNumBetaAtoms(),
                config.getNumSigmaAtoms(),
                config.getNumGammaAtoms());

        this.blender = new Blender(this.projectileContainer);
        this.shooter = new Shooter(projectileContainer);
        this.shieldHandler = new ShieldHandler(this, shooter);
        this.shooter.setOnShotListener(shieldHandler);
        initialize();
    }

    /**
     * instantiates the threads and runnables. fills the list. sets "initialized" to true
     */
    private void initialize() {
        gameRunnables = new ArrayList<>();

        movementRunnable = new MovementRunnable(this.autonomousEntities);
        gameRunnables.add(movementRunnable);

        CollisionHandler collisionHandler = new CollisionHandler(this);
        collisionRunnable = new CollisionRunnable(this, collisionHandler);
        collisionHandler.setCollisionRunnable(collisionRunnable); // TODO: Find better implementation. (refine coupling task)
        gameRunnables.add(collisionRunnable);

        shooterRunnable = new ShooterMovementRunnable(this.shooter);
        gameRunnables.add(shooterRunnable);
        entityGeneratorRunnable = new EntityGeneratorRunnable(this);
        gameRunnables.add(entityGeneratorRunnable);

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
     * Pauses/Resumes/Stops all runnables.
     */
    public void setRunningState(int state) {
        // set the state of game model threads
        gameRunnables.forEach(runnable -> runnable.setRunnableState(state));

        // set the state of the UI
        runningStateListener.onRunningStateChanged(state);

        // set current state of game model controller.
        if (state == GameConstants.GAME_STATE_PAUSED)
            currentState = pausedState;
        else if (state == GameConstants.GAME_STATE_RESUMED)
            currentState = runningState;
    }

    public void moveShooter(int direction) {
        shooterRunnable.setMovementState(direction);
    }

    public void rotateShooter(int direction) {
        currentState.rotateShooter(direction);
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
        return sessionLoadListener;
    }

    public SaveSessionListener getSaveSessionListener() {
        return saveSessionListener;
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
        // projectileContainer.decreaseAtoms(shotEntity.getEntityType().getValue(), 1);
        addEntity(shotEntity);
    }

    public boolean noAtomsOnScreen() {
        for (Entity entity : autonomousEntities)
            if (entity.getSuperType() == SuperType.ATOM)
                return false;
        return true;
    }

    public boolean noEntitiesOnScreen() {
        for (Entity entity : autonomousEntities)
            if (entity.getSuperType() != SuperType.ATOM && entity.getSuperType() != SuperType.SHOOTER)
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateStatisticsProjectileCount() {
        if (player != null)
            player.updateOwnedProjectiles();
    }

    public void updateStatisticsShieldCount() {
        if (player != null)
            player.changeShieldCount();
    }

    public void updateHealth(double damageAmount) {
        if (player != null)
            if (player.loseHealth(damageAmount))
                endGame();
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

    public void setStatisticsController(GameStatistics gameStatistics) {
        this.statistics = gameStatistics;
    }

    public ShieldHandler getShieldHandler() {
        return this.shieldHandler;
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

    public Player getPlayer() {
        return player;
    }

    public void increaseScore(double score) {
        if (player != null) player.incrementScore(score);
    }

    public void collectPowerUp(Powerup powerup) {
        projectileContainer.addPowerUp(powerup);
    }

    public boolean isGameFinished() {
        if (shooter.getCurrentProjectile() == null)
            return noAtomsOnScreen();
        if (outOfEntities)
            return noEntitiesOnScreen();
        return false;
    }


    public void showSavedSessions() {
        currentState.showSavedSessions();
    }

    public void loadGameSession(GameBundle session) {

        // update the game configuration
        Configuration.getInstance().reset(session.getConfigBundle());
        // update the entities in the game view
        this.autonomousEntities.clear();
        this.autonomousEntities.addAll(session.getAtoms());
        this.autonomousEntities.addAll(session.getBlockers());
        this.autonomousEntities.addAll(session.getMolecules());
        this.autonomousEntities.addAll(session.getPowerUps());

        // update the projectile containers
        this.projectileContainer = session.getProjectileContainer();
        this.projectileContainer.setRunningMode(this);

        // update the shieldHandler
        this.shieldHandler = session.getShieldHandler();
        System.out.println(this.shieldHandler);
        System.out.println(this.shieldHandler.getShields());

        // update the shooter state
        this.shooter = session.getShooter();
        this.shooter.setContainer(this.projectileContainer);
        this.shooter.setOnShotListener(shieldHandler);

        // update the player state and statistics listener
        GameStatistics.GameStatisticsListener listener = this.player.getStatisticsListener();
        this.player = session.getPlayer();
        this.player.setStatisticsListener(listener);

        // update runnables
        this.blender.setProjectileContainer(projectileContainer);
        this.shooterRunnable.setShooter(this.shooter);
        this.entityGeneratorRunnable.initializeMaps();

        // reflect the changes in the UI
        gameEntitiesListener.onGameReset();
        for (AutonomousEntity entity : this.autonomousEntities) {
            gameEntitiesListener.onEntityAdd(entity);
        }

    }

    public void saveGameRequest() {
        this.currentState.saveGameSession();
    }

    public void saveWithAdapter(IDatabase database) {
        GameBundle.Builder builder = new GameBundle.Builder();
        builder.setPlayer(getPlayer()).
                setShooter(getShooter()).
                setProjectileContainer(getProjectileContainer()).
                setConfigBundle(Configuration.getInstance().getConfigBundle()).
                setShieldHandler(this.shieldHandler);

        getAutonomousEntities().forEach(entity -> entity.saveState(builder));

        GameBundle bundle = builder.build();
        try {
            database.save(GameConstants.SESSION_COLLECTION_TITLE, "Session", bundle); //
        } catch (IOException e) {
            logger.error("Could not save the game session", e);
        }
    }

    public void setOutOfEntities() {
        outOfEntities = true;
    }

    public void applyShield(ShieldType shieldType) {
        currentState.applyShield(shieldType);
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

    public interface SaveSessionListener {
        void showSaveMethodSelector();
    }
}
