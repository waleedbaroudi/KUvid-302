package model.game_running.runnables;

import model.game_running.GameConstants;

public abstract class GameRunnable implements Runnable {
    protected boolean running;
    protected boolean paused;

    public void setRunnableState(int state) {
        switch (state) {
            case GameConstants.GAME_STATE_PAUSED:
                this.paused = true;
                break;
            case GameConstants.GAME_STATE_RESUMED:
                this.paused = false;
                break;
            case GameConstants.GAME_STATE_STOP:
                this.running = false;
                break;
        }
    }
}
