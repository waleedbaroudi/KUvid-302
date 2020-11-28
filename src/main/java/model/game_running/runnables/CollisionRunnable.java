package model.game_running.runnables;

import model.game_entities.AutonomousEntity;
import model.game_running.RunningMode;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * This runnable handles collisions between entities.
 */
public class CollisionRunnable extends GameRunnable {

    LinkedBlockingQueue<AutonomousEntity> collisionQueue; //a queue to hold the elements that have collided

    public CollisionRunnable() {
        collisionQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            if (!collisionQueue.isEmpty()) {
                AutonomousEntity entity = collisionQueue.poll();
                //if(entity.collided)
                RunningMode.removeAutonomousEntity(entity);
            }
        }
    }

    public void queueEntityCollision(AutonomousEntity entity) {
        try {
            this.collisionQueue.put(entity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
