package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This runnable handles collisions between entities.
 */
public class CollisionRunnable implements Runnable {

    private boolean running; // to control the running loop
    LinkedBlockingQueue<AutonomousEntity> collisionQueue; //a queue to hold the elements that have collided

    public CollisionRunnable(){
        collisionQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            if(!collisionQueue.isEmpty())
                RunningMode.removeAutonomousEntity(collisionQueue.poll());
        }
    }

    public void queueEntityMovement(AutonomousEntity entity){
        try {
            this.collisionQueue.put(entity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * sets running to false, stops the collision checking loop.
     */
    public void pause() {
        this.running = false;
    }

    /**
     * calls run, starts the collision checking loop.
     */
    public void resume() {
        this.run();
    }
}
