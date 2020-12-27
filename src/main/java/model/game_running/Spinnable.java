package model.game_physics.spinning;

import model.game_running.runnables.MovementRunnable;

/**
 * All spinning entities should implement this interface. it handles the spinning rotation both in the UI and domain
 */
public interface Spinnable{
    /**
     * get current object rotation in the space.
     * @return rotation degree
     */
    double getRotationDegree();

    /**
     * rotate the entity degree amount of degrees. This method should handle updating the rotation dgree in the hitbox
     * @param degree
     */
    void rotate(double degree);

    /**
     * Register the entity instance to the spinningController
     * @param spinningController
     */
    default void registerSpinningController(MovementRunnable spinningController){
        spinningController.registerSpinnable(this);
    }
}
