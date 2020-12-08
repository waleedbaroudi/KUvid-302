package model.game_running;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_entities.Entity;
import model.game_entities.Shooter;
import model.game_physics.hitbox.RectangularHitbox;
import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.runnables.ShooterMovementRunnable;
import model.game_running.runnables.EntityGeneratorRunnable;
import org.apache.log4j.Logger;
import utils.Coordinates;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    public Logger logger = Logger.getLogger(this.getClass().getName());

    //space objects
    private final CopyOnWriteArrayList<AutonomousEntity> autonomousEntities;

    private final Shooter atomShooter;

    boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    RunningStateListener runningStateListener;
    GameEntitiesListener gameEntitiesListener;
    Blender.BlenderListener blenderListener;

    // Runnables
    MovementRunnable movementRunnable;
    CollisionRunnable collisionRunnable;
    ShooterMovementRunnable shooterRunnable;
    EntityGeneratorRunnable entityGeneratorRunnable;

    // Threads
    Thread movementThread;
    Thread collisionThread;
    Thread shooterThread;
    Thread objectGeneratorThread;

    // Blender
    Blender blender;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener) {
        autonomousEntities = new CopyOnWriteArrayList<AutonomousEntity>();

        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;

        // Adding Blender
        // this.blender = new Blender(this);
        // TODO update the shooter inital coordinates from config
        // TODO fix the shooter position
        this.atomShooter = new Shooter(new Coordinates(Configuration.getInstance().getGameWidth() / 2.0,
                Configuration.getInstance().getGameHeight() -  Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_HEIGHT),
                new RectangularHitbox(Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_WIDTH, Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_HEIGHT));
        initialize();
    }

    public Shooter getAtomShooter() {
        return atomShooter;
    }

    /**
     * instantiates the threads and runnables. fills the list. sets "initialized" to true
     */
    private void initialize() {
        //todo: fill autonomous entity list and containers here

        movementRunnable = new MovementRunnable(this.autonomousEntities);
        collisionRunnable = new CollisionRunnable(this, new CollisionHandler(this)); // TODO: Pass the arraylist instead
        shooterRunnable = new ShooterMovementRunnable(this.atomShooter);
        entityGeneratorRunnable = new EntityGeneratorRunnable(this);


        movementThread = new Thread(this.movementRunnable);
        collisionThread = new Thread(this.collisionRunnable);
        shooterThread = new Thread(this.shooterRunnable);
        objectGeneratorThread = new Thread(this.entityGeneratorRunnable);

        this.isInitialized = true;
    }

    /**
     * starts the movement and collision threads
     */
    public void startThreads() {
        if (!isInitialized) {
            logger.error("Game is not yet initialized");
            return;
        }

        // Starting the threads.
        movementThread.start();
        collisionThread.start(); //TODO: fix then uncomment
        shooterThread.start();
        objectGeneratorThread.start();
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
        AutonomousEntity shotEntity = this.atomShooter.shoot();
        addEntity(shotEntity);
    }

    /**
     * @param removedEntities autonomous entities to be removed from the list of elements in the space
     * @return a boolean indicating whether the entities were removed successfully
     */
    public void removeAutonomousEntities(Collection<AutonomousEntity> removedEntities) {
        gameEntitiesListener.onEntitiesRemove(removedEntities);
        autonomousEntities.removeAll(removedEntities);
    }

    public void removeEntity(AutonomousEntity entity){
        // TODO: change the gamerListenier to removeEntity. Handle multiple entities by calling removeEntity on them one by one.
        ArrayList<AutonomousEntity> tmp = new ArrayList<>();
        tmp.add(entity);
        gameEntitiesListener.onEntitiesRemove(tmp);
        autonomousEntities.remove(entity);
    }


    public interface RunningStateListener {
        void onRunningStateChanged(int state);
    }

    public interface GameEntitiesListener {
        void onEntityAdd(AutonomousEntity entity);

        void onEntitiesRemove(Collection<AutonomousEntity> entities);
    }
}
