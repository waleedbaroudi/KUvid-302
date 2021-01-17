package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_entities.*;
import model.game_entities.enums.ShieldType;
import model.game_entities.enums.SuperType;
import model.game_running.listeners.*;
import model.game_running.runnables.*;
import model.game_running.states.GameState;
import model.game_running.states.PausedState;
import model.game_running.states.ResumedState;
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
    GameState currentState;
    GameState pausedState;
    GameState resumedState;

    //space objects
    private final CopyOnWriteArrayList<AutonomousEntity> autonomousEntities;
    private ProjectileContainer projectileContainer;
    private Shooter shooter;
    private ShieldHandler shieldHandler;
    private boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized //todo: this needed?

    //Listeners
    private final RunningStateListener runningStateListener;
    private final GameEntitiesListener gameEntitiesListener;
    private final SessionLoadListener sessionLoadListener;
    private final SaveSessionListener saveSessionListener;

    // Runnables
    private ArrayList<GameRunnable> gameRunnables;
    private EntityGeneratorRunnable entityGeneratorRunnable;
    private boolean outOfEntities; // flags that the entity generator runnable has run out of entities to drop

    // Threads
    private Thread movementThread;
    private Thread collisionThread;
    private Thread entityGeneratorThread;

    // Blender
    private Blender blender;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener, SessionLoadListener sessionLoadListener, SaveSessionListener saveSessionListener) {
        autonomousEntities = new CopyOnWriteArrayList<>();

        Configuration config = Configuration.getInstance();

        resumedState = new ResumedState(this);
        pausedState = new PausedState(this);
        currentState = resumedState;

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

        GameRunnable movementRunnable = new MovementRunnable(this);
        gameRunnables.add(movementRunnable);

        CollisionHandler collisionHandler = new CollisionHandler(this);
        GameRunnable collisionRunnable = new CollisionRunnable(this, collisionHandler);
        gameRunnables.add(collisionRunnable);

        entityGeneratorRunnable = new EntityGeneratorRunnable(this);
        gameRunnables.add(entityGeneratorRunnable);

        movementThread = new Thread(movementRunnable);
        collisionThread = new Thread(collisionRunnable);
        entityGeneratorThread = new Thread(this.entityGeneratorRunnable);

        this.isInitialized = true;
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
        entityGeneratorThread.start();
    }

    public void moveShooter(int direction) {
        currentState.moveShooter(direction);
    }

    public void rotateShooter(int direction) {
        currentState.rotateShooter(direction);
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

    public void switchAtom() {
        getShooter().switchAtom();
    }

    public boolean noAtomsOnScreen() {
        for (Entity entity : autonomousEntities)
            if (entity.getSuperType() == SuperType.ATOM)
                return false;
        return true;
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

    public boolean noEntitiesOnScreen() {
        for (Entity entity : autonomousEntities)
            if (entity.getSuperType() != SuperType.ATOM && entity.getSuperType() != SuperType.SHOOTER)
                return false;
        return true;
    }

    public SessionLoadListener getSessionLoadListener() {
        return sessionLoadListener;
    }

    public SaveSessionListener getSaveSessionListener() {
        return saveSessionListener;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStatisticsController(GameStatistics gameStatistics) {
        this.statistics = gameStatistics;
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

    public void updateStatisticsProjectileCount() {
        if (player != null)
            player.updateOwnedProjectiles();
    }

    public void updateStatisticsShieldCount() {
        if (player != null)
            player.changeShieldCount();
    }

    public void endGame() {
        setRunningState(GameConstants.GAME_STATE_STOP);
        runningStateListener.onGameOver();
    }

    /**
     * Pauses/Resumes/Stops all runnables.
     */
    public void setRunningState(int state) {
        // set the state of game model threads
        gameRunnables.forEach(runnable -> runnable.setRunnableState(state));
        // set the state of the UI
        runningStateListener.onRunningStateChanged(state);
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
        GameStatisticsListener listener = this.player.getStatisticsListener();
        this.player = session.getPlayer();
        this.player.setStatisticsListener(listener);

        // update runnables
        this.blender.setProjectileContainer(projectileContainer);
//        this.shooterRunnable.setShooter(this.shooter);
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

    public void resume() {
        currentState.resume();
    }

    public void pause() {
        currentState.pause();
    }


    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }


    // Getters
    public ProjectileContainer getProjectileContainer() {
        return this.projectileContainer;
    }

    public Blender getBlender() {
        return this.blender;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Player getPlayer() {
        return player;
    }

    public GameState getPausedState() {
        return pausedState;
    }

    public GameState getResumedState() {
        return resumedState;
    }

    public ShieldHandler getShieldHandler() {
        return this.shieldHandler;
    }

}
