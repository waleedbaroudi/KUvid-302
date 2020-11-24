package model.game_running;

import model.game_entities.AutonomousEntity;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    Logger logger;
    private static ArrayList<AutonomousEntity> autonomousEntities;

    boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    // Runnables
    MovementRunnable movementRunnable;
    CollisionRunnable collisionRunnable;

    // Threads
    Thread movementThread;
    Thread collisionThread;

    public RunningMode() {
        autonomousEntities = new ArrayList<>();
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

        movementThread = new Thread(this.movementRunnable);
        collisionThread = new Thread(this.collisionRunnable);

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
    }

    /**
     *
     * @param entity the entity to be added to the list of entities
     * @return a boolean indicating whether the entity was added successfully
     */
    public boolean addEntity(AutonomousEntity entity) {
        return autonomousEntities.add(entity);
    }

    /**
     *
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
        this.pause();
        movementThread.interrupt();
        collisionThread.interrupt();
    }

    /**
     * Pauses all runnables.
     */
    public void pause() {
        movementRunnable.pause();
        collisionRunnable.pause();
    }

    /**
     * static so that all classes
     * @return returns the list of autonomous entities
     */
    public static ArrayList<AutonomousEntity> getAutonomousEntities() {
        return autonomousEntities;
    }
    public static boolean removeAutonomousEntities(ArrayList<AutonomousEntity> removedEntities) {
        return autonomousEntities.removeAll(removedEntities);
    }

}
