package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.ArrayList;

/**
 * This runnable handles collisions between entities.
 */
public class CollisionRunnable implements Runnable {

    private boolean running; // to control the running loop
    private ArrayList<AutonomousEntity> collidedEntities;
    @Override
    public void run() {
        running = true;
        while (running) {
            for (AutonomousEntity entity : RunningMode.getAutonomousEntities()) {
                // AutonomousEntity collidedEntity = entity.checkCollision();
                // if(entity != null) {
                //      collidedEntities.add(collidedEntity);
                //      collidedEntities.add(entity);
                //  }
            }
            // RunningMode.removeAutonomousEntities(this.collidedEntities);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
