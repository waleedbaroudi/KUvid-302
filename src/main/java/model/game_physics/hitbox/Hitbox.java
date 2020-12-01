package model.game_physics.hitbox;

import model.game_entities.enums.Direction;
import utils.Coordinates;
import utils.Vector;

import java.util.ArrayList;

public abstract class Hitbox {

    /**
     * Rotates the Hitbox in the specified direction
     * @param angle indicating the direction of the rotation
     */
    public abstract void rotate(double angle);

    /**
     * checks if coordinates is inside the Hitbox
     * @param entityCoords
     * @param checkCoords
     * @return true if coordinates is inside the Hitbo, otherwise returns false
     */
    public abstract boolean isInside(Coordinates entityCoords, Coordinates checkCoords);

    public abstract ArrayList<Coordinates> getBoarderPoints(Coordinates entityCoords);

    /**
     * Checks if a Hitbox is collided with a another Hitbox.
     * @param entityCoords The owner object coordinates.
     * @param checkList the target list of coordinates.
     * @return True if the owner Hitbox is collided with the target Hitbox.
     */
    public boolean isInside(Coordinates entityCoords, ArrayList<Coordinates> checkList){
        for(Coordinates checkPoint : checkList){
            if(!this.isInside(entityCoords, checkPoint)){
                return false;
            }
        }
        return true;
    }

    public abstract Coordinates[] getBoundaryCoordinates();
}
