package ui.windows;

import model.game_running.RunningMode;

import javax.swing.*;

/**
 * This class draws the game running window.
 * all game animations and actions will happen in this window
 */
public class RunningWindow implements RunningMode.RunningStateListener {
    RunningMode runningMode;
    private boolean running;
    private boolean paused;

    public RunningWindow() {
        this.runningMode = new RunningMode(this);
        start();
    }

    /**
     * starts the the game loop (drawing, movement, and collision checks)
     */
    private void start() {
        int x = 0;
        runningMode.startThreads();
        while(running) {
            draw();
        }
        //stop other threads after drawing stops.
        runningMode.stop();
    }

    /**
     * starts the loop that draws game elements.
     */
    private void draw() {
        if(paused)
            return;
        // todo: draw all elements here and trigger entity state update from 'runningMode'
    }

    @Override
    public void onRunningStateChanged(boolean paused) {
        this.paused = paused;
    }
}
