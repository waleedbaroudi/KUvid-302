package model.game_physics.hitbox;

import model.game_entities.enums.Direction;
import utils.Coordinates;
import utils.Vector;

public abstract class Hitbox {

    /**
     * Rotates the Hitbox in the specified direction
     * @param angle indicating the direction of the rotation
     */
    public abstract void rotate(double angle);

    /**
     * checks if coordinates is inside the Hitbox
     * @param coordinates
     * @return true if coordinates is inside the Hitbo, otherwise returns false
     */
    public abstract boolean isInside(Coordinates ownerCoordinates, Coordinates coordinates);

}
