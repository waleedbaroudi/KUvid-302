package model.game_running;

import model.game_entities.AutonomousEntity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode implements KeyListener {
    Logger logger;
    private static ArrayList<AutonomousEntity> autonomousEntities;

    boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    RunningStateListener listener;

    // Runnables
    MovementRunnable movementRunnable;
    CollisionRunnable collisionRunnable;

    // Threads
    Thread movementThread;
    Thread collisionThread;

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
        this.pause();
        movementThread.interrupt();
        collisionThread.interrupt();
    }

    /**
     * Pauses all runnables.
     */
    public void pause() {
        listener.onRunningStateChanged(true);
        movementRunnable.pause();
        collisionRunnable.pause();
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

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P)
            this.pause();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public interface RunningStateListener {
        void onRunningStateChanged(boolean paused);
    }

}
