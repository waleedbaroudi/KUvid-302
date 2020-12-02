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
        this.shooter = shooter;
        this.movementState = SHOOTER_MOVEMENT_STILL;
        this.rotationState = SHOOTER_ROTATION_STILL;
    }

    @Override
    public void run() {
        this.running = true;
        while (running) {
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

            try {
                Thread.sleep(50);
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
