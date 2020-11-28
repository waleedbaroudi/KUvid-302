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
                //this.shooter.move(movementState);
                System.out.println("gun moving");
            }
            if (this.rotationState != SHOOTER_ROTATION_STILL) {
                //this.shooter.rotate(rotationState);
                this.rotationState = SHOOTER_ROTATION_STILL;
                System.out.println("gun rotating");
            }

            try {
                Thread.sleep(300);
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
