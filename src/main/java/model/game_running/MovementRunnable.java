package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * This runnable handles moving objets in the background
 */
public class MovementRunnable implements Runnable {

    private boolean running; //to control the running loop
    LinkedBlockingQueue<AutonomousEntity> movementQueue; //a queue to hold the elements we want to move

    public MovementRunnable() {
        movementQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            if (movementQueue.isEmpty())
                continue;
            movementQueue.poll().move();
        }
    }

    /**
     * adds an entity to the movement queue where it will be moved
     * @param entity the entity to be queue for movement
     */
    public void queueEntityMovement(AutonomousEntity entity) {
        try {
            movementQueue.put(entity);
        } catch (InterruptedException e) {
            System.out.println("THREAD IS INTERRUPTED: COULD NOT PUT INTO QUEUE");
        }
    }

    /**
     * sets running to false, stopping the movement loop
     */
    public void pause() {
        this.running = false;
    }

    /**
     * calls run, starting the movement loop
     */
    public void resume() {
        this.run();
    }
}
