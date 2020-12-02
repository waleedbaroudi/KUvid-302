package model.game_running.runnables;

import model.game_entities.Shooter;
import model.game_running.GameConstants;
import model.game_running.runnables.GameRunnable;

import static model.game_running.GameConstants.*;

public class ShooterMovementRunnable extends GameRunnable {

    Shooter shooter;

    private int movementState;
    private int rotationState;

    public ShooterMovementRunnable(Shooter shooter) {
        super();
        this.shooter = shooter;
        this.movementState = SHOOTER_MOVEMENT_STILL;
        this.rotationState = SHOOTER_ROTATION_STILL;
    }

    @Override
    public void run() {
        this.running = true;
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                if (this.movementState != SHOOTER_MOVEMENT_STILL) {
                    //this.shooter.move(int movementState); //TODO: @ABDUL: UNCOMMENT
                }
                if (this.rotationState != SHOOTER_ROTATION_STILL) {
//                this.shooter.rotate(int rotationState); //TODO: @ABDUL: UNCOMMENT
                    this.rotationState = SHOOTER_ROTATION_STILL;
                }

                Thread.sleep(GAME_THREAD_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void setMovementState(int state) {
        this.movementState = state;
    }

    public void setRotationState(int state) {
        this.rotationState = state;
    }
}
