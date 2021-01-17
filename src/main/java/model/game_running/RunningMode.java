package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_entities.*;
import model.game_entities.enums.ShieldType;
import model.game_entities.enums.SuperType;
import model.game_running.listeners.*;
import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.EntityGeneratorRunnable;
import model.game_running.runnables.GameRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.states.GameState;
import model.game_running.states.PausedState;
import model.game_running.states.ResumedState;
import model.game_space.Blender;
import model.game_space.Player;
import org.apache.log4j.Logger;
import services.database.IDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The main control unit behind the game UI
 * Facade Controller
 */
public class RunningMode {
    public Logger logger = Logger.getLogger(this.getClass().getName());
    private Player player;

    // game state
    GameState currentState;
    GameState pausedState;
    GameState resumedState;

    //space objects
    private final CopyOnWriteArrayList<AutonomousEntity> autonomousEntities;
    private ProjectileContainer projectileContainer;
    private Shooter shooter;

    //Listeners
    private final RunningStateListener runningStateListener;
    private final GameEntitiesListener gameEntitiesListener;
    private final SessionLoadListener sessionLoadListener;
    private final SessionSaveListener sessionSaveListener;

    // Runnables
    private ArrayList<GameRunnable> gameRunnables;
    private EntityGeneratorRunnable entityGeneratorRunnable;
    private boolean outOfEntities; // flags that the entity generator runnable has run out of entities to drop

    // Threads
    private Thread movementThread;
    private Thread collisionThread;
    private Thread entityGeneratorThread;

    // Blender
    private final Blender blender;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener, SessionLoadListener sessionLoadListener, SessionSaveListener sessionSaveListener) {
        autonomousEntities = new CopyOnWriteArrayList<>();
        // Config
        Configuration config = Configuration.getInstance();
        // States
        resumedState = new ResumedState(this);
        pausedState = new PausedState(this);
        currentState = resumedState;

        // Listeners
        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;
        this.sessionLoadListener = sessionLoadListener;
        this.sessionSaveListener = sessionSaveListener;

        // Shooter and Projectile Container
        this.projectileContainer = new ProjectileContainer(
                this,
                config.getNumAlphaAtoms(),
                config.getNumBetaAtoms(),
                config.getNumSigmaAtoms(),
                config.getNumGammaAtoms());
        this.blender = new Blender(this.projectileContainer);
        this.shooter = new Shooter(this);
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
    }

    /**
     * starts the Movement, collision, shooter, and EntityGenerator threads
     */
    public void startThreads() {
        movementThread.start();
        collisionThread.start();
        entityGeneratorThread.start();
    }

    // Shooter ////
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

    public void collectPowerUp(Powerup powerup) {
        projectileContainer.addPowerUp(powerup);
    }

    public void applyShield(ShieldType shieldType) {
        currentState.applyShield(shieldType);
    }

    // Space Entities ////

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
     */
    public void addEntity(AutonomousEntity entity) {
        gameEntitiesListener.onEntityAdd(entity);
        autonomousEntities.add(entity);
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

    public void setOutOfEntities() {
        outOfEntities = true;
    }

    // Player ////
    public void setPlayer(Player player) {
        this.player = player;
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

    public void increaseScore(double score) {
        if (player != null) player.incrementScore(score);
    }

    // Save-Load ////
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

        // update the shooter state
        shooter = session.getShooter();
        shooter.setRunningMode(this);
        shooter.initializeShieldHandler();

        // update the player state and statistics listener
        GameStatisticsListener listener = this.player.getStatisticsListener();
        this.player = session.getPlayer();
        this.player.setStatisticsListener(listener);

        // update runnables
        this.blender.setProjectileContainer(projectileContainer);
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
        builder.setPlayer(player).
                setShooter(getShooter()).
                setProjectileContainer(getProjectileContainer()).
                setConfigBundle(Configuration.getInstance().getConfigBundle());

        getAutonomousEntities().forEach(entity -> entity.saveState(builder));

        GameBundle bundle = builder.build();
        try {
            database.save(GameConstants.SESSION_COLLECTION_TITLE, "Session", bundle); //
        } catch (IOException e) {
            logger.error("Could not save the game session", e);
        }
    }

    public SessionLoadListener getSessionLoadListener() {
        return sessionLoadListener;
    }

    public SessionSaveListener getSessionSaveListener() {
        return sessionSaveListener;
    }

    // Game State ////
    public void resume() {
        currentState.resume();
    }

    public void pause() {
        currentState.pause();
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    /**
     * Pauses/Resumes/Stops all runnables.
     */
    public void applyRunningState(int state) {
        // set the state of game model threads
        gameRunnables.forEach(runnable -> runnable.setRunnableState(state));
        // set the state of the UI
        runningStateListener.onRunningStateChanged(state);
    }

    public boolean isGameFinished() {
        if (shooter.getCurrentProjectile() == null)
            return noAtomsOnScreen();
        if (outOfEntities)
            return noEntitiesOnScreen();
        return false;
    }

    public void endGame() {
        applyRunningState(GameConstants.GAME_STATE_STOP);
        runningStateListener.onGameOver();
    }

    // Getters ////
    public ProjectileContainer getProjectileContainer() {
        return this.projectileContainer;
    }

    public Blender getBlender() {
        return this.blender;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public GameState getPausedState() {
        return pausedState;
    }

    public GameState getResumedState() {
        return resumedState;
    }

    public ShieldHandler getShieldHandler() {
        return shooter.getShieldHandler();
    }

}
