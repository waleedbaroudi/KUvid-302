package model.game_running;

import model.game_entities.AutonomousEntity;

/**
 * This runnable handles moving objets in the background
 */
public class MovementRunnable implements Runnable {

    private boolean running; //to control the running loop

    @Override
    public void run() {
        running = true;
        while (running) {
            for (AutonomousEntity entity : RunningMode.getAutonomousEntities()) {
                entity.move();
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
