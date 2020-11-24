package model.game_running;

import model.game_entities.AutonomousEntity;
import ui.windows.RunningWindow;

import java.util.ArrayList;

public class MovementRunnable implements Runnable {

    private boolean running;

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

    public void pause() {
        this.running = false;
    }

    public void resume() {
        this.run();
    }
}
