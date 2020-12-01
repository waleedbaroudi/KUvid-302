package model.game_running;

import model.game_entities.AutonomousEntity;
import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.runnables.ShooterMovementRunnable;
import model.game_space.ObjectGenerator;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    Logger logger;
    private ArrayList<AutonomousEntity> autonomousEntities;

    boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    RunningStateListener runningStateListener;
    GameEntitiesListener gameEntitiesListener;

    // Runnables
    MovementRunnable movementRunnable;
    CollisionRunnable collisionRunnable;
    ShooterMovementRunnable shooterRunnable;
    ObjectGenerator objectGenerator;

    // Threads
    Thread movementThread;
    Thread collisionThread;
    Thread shooterThread;
    Thread objectGeneratorThread;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener) {
        autonomousEntities = new ArrayList<>();
        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;
        logger = Logger.getLogger(this.getClass().getName());
        initialize();
    }

    /**
     * instantiates the threads and runnables. fills the list. sets "initialized" to true
     */
    private void initialize() {
        //todo: fill autonomous entity list and containers here

        movementRunnable = new MovementRunnable();
        collisionRunnable = new CollisionRunnable(this); // TODO: Pass the arraylist instead
        shooterRunnable = new ShooterMovementRunnable(null); // TODO: PASS THE SHOOTER OBJECT HERE
        objectGenerator = new ObjectGenerator(this);


        movementThread = new Thread(this.movementRunnable);
        collisionThread = new Thread(this.collisionRunnable);
        shooterThread = new Thread(this.shooterRunnable);
        objectGeneratorThread = new Thread(this.objectGenerator);

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
        collisionThread.start();
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
     * Pauses all runnables.
     */

    public void setRunningState(int state) {
        runningStateListener.onRunningStateChanged(state);
        movementRunnable.setRunnableState(state);
        collisionRunnable.setRunnableState(state);
        shooterRunnable.setRunnableState(state);
    }

    /**
     * adds an entity to the movement queue where it will be moved
     * and queues the entity for collision check and remove it if collided with another
     * entity.
     *
     * @param entity the entity to be queued for movement and collision checking.
     */
    public void updateEntityState(AutonomousEntity entity) {
        this.movementRunnable.queueEntityMovement(entity);
       // this.collisionRunnable.queueEntityCollision(entity);
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
    public ArrayList<AutonomousEntity> getAutonomousEntities() {
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
     * @param removedEntity autonomous entities to be removed from the list of elements in the space
     * @return a boolean indicating whether the entities were removed successfully
     */
    public boolean removeAutonomousEntity(AutonomousEntity removedEntity) {
        gameEntitiesListener.onEntityRemove(removedEntity);
        return autonomousEntities.remove(removedEntity);
    }

    public interface RunningStateListener {
        void onRunningStateChanged(int state);
    }

    public interface GameEntitiesListener {
        void onEntityAdd(AutonomousEntity entity);
        void onEntityRemove(AutonomousEntity entity);
    }
}
