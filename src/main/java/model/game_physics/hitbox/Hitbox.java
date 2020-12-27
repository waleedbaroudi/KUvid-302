package model.game_physics.hitbox;

import utils.Coordinates;

import java.util.ArrayList;

public abstract class Hitbox {

    protected double rotationDegree = 0;

    /**
     * Rotates the Hitbox in the specified direction
     * @param angle indicating the direction of the rotation
     */
    public void rotate(double angle){
        this.rotationDegree += angle;
    }

    public double getRotationDegree(){
        return this.rotationDegree;
    }

    /**
     * checks if coordinates is inside the Hitbox
     * @param entityCoords
     * @param checkCoords
     * @return true if coordinates is inside the Hitbo, otherwise returns false
     */
    public abstract boolean isInside(Coordinates entityCoords, Coordinates checkCoords);

    public abstract ArrayList<Coordinates> getBoundaryPoints(Coordinates entityCoords);

    /**
     * Checks if a Hitbox is collided with a another Hitbox.
     * @param entityCoords The owner object coordinates.
     * @param checkList the target list of coordinates.
     * @return True if the owner Hitbox is collided with the target Hitbox.
     */
    public boolean isInside(Coordinates entityCoords, ArrayList<Coordinates> checkList){
        for(Coordinates checkPoint : checkList){
            if(this.isInside(entityCoords, checkPoint)){
                return true;
            }
        }
        return false;
    }

}
