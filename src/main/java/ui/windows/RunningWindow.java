package ui.windows;

import model.game_entities.AutonomousEntity;
import model.game_running.RunningMode;

/**
 * This class draws the game running window.
 * all game animations and actions will happen in this window
 */
public class RunningWindow {
    RunningMode mode;
    private boolean running;

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
        while(running) {
            draw();
        }
        //stop other threads after drawing stops.
        mode.stop();
    }

    /**
     * starts the loop that draws game elements.
     */
    private void draw() {
        // todo: draw all elements here and trigger entity state update from 'mode'
    }
}
