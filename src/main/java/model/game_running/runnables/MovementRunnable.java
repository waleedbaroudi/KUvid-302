package model.game_running.runnables;

import model.game_entities.AutonomousEntity;
import model.game_running.runnables.GameRunnable;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * This runnable handles moving objets in the background
 */
public class MovementRunnable extends GameRunnable {

    LinkedBlockingQueue<AutonomousEntity> movementQueue; //a queue to hold the elements we want to move

    public MovementRunnable() {
        movementQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        System.out.println("IM IN RUN METHOD IN MOVEMENT");
        running = true;
        while (running) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(movementQueue.peek());
            if (!movementQueue.isEmpty()) {
                movementQueue.poll().move();
            }
        }
    }

    /**
     * adds an entity to the movement queue where it will be moved
     *
     * @param entity the entity to be queue for movement
     */
    public void queueEntityMovement(AutonomousEntity entity) {
        try {
            movementQueue.put(entity);
        } catch (InterruptedException e) {
            System.out.println("THREAD IS INTERRUPTED: COULD NOT PUT INTO QUEUE");
        }
    }


}
