package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.ArrayList;

import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.runnables.ShooterMovementRunnable;
import org.apache.log4j.Logger;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    Logger logger;
    private static ArrayList<AutonomousEntity> autonomousEntities;

    boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    RunningStateListener listener;

    // Runnables
    MovementRunnable movementRunnable;
    CollisionRunnable collisionRunnable;
    ShooterMovementRunnable shooterRunnable;

    // Threads
    Thread movementThread;
    Thread collisionThread;
    Thread shooterThread;

    public RunningMode(RunningStateListener listener) {
        autonomousEntities = new ArrayList<>();
        this.listener = listener;
        logger = Logger.getLogger(this.getClass().getName());
        initialize();
    }

    /**
     * instantiates the threads and runnables. fills the list. sets "initialized" to true
     */
    private void initialize() {
        //todo: fill autonomous entity list and containers here

        movementRunnable = new MovementRunnable();
        collisionRunnable = new CollisionRunnable();
        shooterRunnable = new ShooterMovementRunnable(null); //TODO: PASS THE SHOOTER OBJECT HERE

        movementThread = new Thread(this.movementRunnable);
        collisionThread = new Thread(this.collisionRunnable);
        shooterThread = new Thread(this.shooterRunnable);

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
    }

    /**
     * @param entity the entity to be added to the list of entities
     * @return a boolean indicating whether the entity was added successfully
     */
    public boolean addEntity(AutonomousEntity entity) {
        return autonomousEntities.add(entity);
    }

    /**
     * @param entity the entity to be removed to the list of entities
     * @return a boolean indicating whether the entity was removed successfully
     */
    public boolean removeEntity(AutonomousEntity entity) {
        return autonomousEntities.remove(entity);
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
        listener.onRunningStateChanged(state);
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
        this.collisionRunnable.queueEntityCollision(entity);
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
    public static ArrayList<AutonomousEntity> getAutonomousEntities() {
        return autonomousEntities;
    }

    /**
     * @param removedEntity autonomous entities to be removed from the list of elements in the space
     * @return a boolean indicating whether the entities were removed successfully
     */
    public static boolean removeAutonomousEntity(AutonomousEntity removedEntity) {
        return autonomousEntities.remove(removedEntity);
    }

    public interface RunningStateListener {
        void onRunningStateChanged(int state);
    }

}
