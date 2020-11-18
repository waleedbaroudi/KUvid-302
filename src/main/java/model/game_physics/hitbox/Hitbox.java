package model.game_physics.hitbox;

import model.game_entities.enums.Direction;

public abstract class Hitbox {
    Coordinate centerCoordinates;

    public Hitbox(Coordinate centerCoordinates){
        this.centerCoordinates = centerCoordinates;
    }

    /**
     * Rotates the Hitbox in the specified direction
     * @param direction indicating the direction of the rotation
     */
    public abstract void rotate(Direction direction);

    /**
     * moves the Hitbox of the object according to the given coordinates
     * @param coordinates indicating the next position of the Hitbox
     */
    public abstract void move(Coordinates coordinates);

    /**
     * checks if coordinates is inside the Hitbox
     * @param coordinates
     * @return true if coordinates is inside the Hitbo, otherwise returns false
     */
    public abstract Boolean isInside(Coordinates coordinates);

}
