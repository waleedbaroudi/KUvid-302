package model.game_running.runnables;

import model.game_entities.Shooter;

import static model.game_building.GameConstants.*;

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
                  if (this.movementState == SHOOTER_MOVEMENT_RIGHT)
                      this.shooter.move(1);
                  else
                      this.shooter.move(-1);
                }
                if (this.rotationState != SHOOTER_ROTATION_STILL) {
                  if(this.rotationState == SHOOTER_ROTATION_RIGHT)
                      this.shooter.rotate(1);
                  else
                      this.shooter.rotate(-1);
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
