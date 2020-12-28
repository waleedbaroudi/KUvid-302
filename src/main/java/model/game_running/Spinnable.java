package model.game_running;

import model.game_running.runnables.MovementRunnable;

/**
 * All spinning entities should implement this interface. it handles the spinning rotation both in the UI and domain
 */
public interface Spinnable {
    /**
     * get current object rotation in the space.
     *
     * @return rotation degree
     */
    double getRotationDegree();

    /**
     * spin the entity for once. This method should handle updating the rotation degree in the hitbox
     */
    void spin();

    /**
     * Register the entity instance to the spinningController
     *
     * @param spinningController in which the spinnable will be registered
     */
    default void registerSpinningController(MovementRunnable spinningController) {
        spinningController.registerSpinnable(this);
    }
}
