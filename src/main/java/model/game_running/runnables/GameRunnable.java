package model.game_running.runnables;

import model.game_running.GameConstants;

public abstract class GameRunnable implements Runnable {
    protected boolean running;
    protected boolean paused;

    public void setRunnableState(int state) {
//        paused = state == GameConstants.GAME_STATE_PAUSED;
        switch (state) {
            case GameConstants.GAME_STATE_PAUSED:
            case GameConstants.GAME_STATE_RESUMED:
                this.paused = (state == GameConstants.GAME_STATE_PAUSED);
            case GameConstants.GAME_STATE_STOP:
                this.running = false;
        }

        System.out.println("PAUSED in runnable? " + this.paused);
    }
}
