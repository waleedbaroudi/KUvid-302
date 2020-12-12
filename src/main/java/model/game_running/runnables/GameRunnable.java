package model.game_running.runnables;

import model.game_building.GameConstants;

import java.util.concurrent.CountDownLatch;

public abstract class GameRunnable implements Runnable {
    protected boolean running;
    protected CountDownLatch latch; //this latch will clog all runnables when the game is paused.

    public GameRunnable() {
        latch = new CountDownLatch(0);
    }

    public void setRunnableState(int state) {
        switch (state) {
            case GameConstants.GAME_STATE_PAUSED:
                if (latch.getCount() == 0) //check that the game is not already paused
                    this.latch = new CountDownLatch(1); //pausing the game will create a latch.
                break;
            case GameConstants.GAME_STATE_RESUMED:
                if (latch.getCount() > 0)
                    latch.countDown(); //check if the game is not already resumed, then countdown the latch to make it open
                break;
            case GameConstants.GAME_STATE_STOP:
                this.running = false;
                break;
        }
    }
}
