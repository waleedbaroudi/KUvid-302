package model.game_running.runnables;

import model.game_running.GameConstants;

public abstract class GameRunnable implements Runnable {
    protected boolean running;

    public void setRunnableState(int state) {
        if (state == GameConstants.GAME_STATE_PAUSED)
            this.pause();
        else
            this.resume();
    }

    /**
     * sets running to false, stopping the runnable loop
     */
    private void pause() {
        this.running = false;
    }

    /**
     * calls run, starting the runnable loop
     */
    private void resume() {
        this.run();
    }
}
