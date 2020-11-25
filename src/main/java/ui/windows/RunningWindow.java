package ui.windows;

import model.game_entities.AutonomousEntity;
import model.game_running.RunningMode;

/**
 * This class draws the game running window.
 * all game animations and actions will happen in this window
 */
public class RunningWindow {
    RunningMode mode;

    public RunningWindow(RunningMode mode) {
        this.mode = mode;
        start();
    }

    /**
     * starts the the game loop (drawing, movement, and collision checks)
     */
    private void start() {
        int x = 0;
        mode.startThreads();
        while (x < 100) {
            for (AutonomousEntity entity : RunningMode.getAutonomousEntities()) {
                draw(entity);
                this.mode.queueEntityMovement(entity);
            }
            System.out.println();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x++;
        }

        mode.stop();
    }

    private void draw(AutonomousEntity entity) {
        //FOR TESTING: print a string representation of the object.
    }
}
