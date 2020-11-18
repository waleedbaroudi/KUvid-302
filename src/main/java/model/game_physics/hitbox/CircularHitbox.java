package model.game_physics.hitbox;

import model.game_entities.enums.Direction;

public class CircularHitbox extends Hitbox {

    Vector arcVector;

    public CircularHitbox(int height, int width, Coordinate centerCoordinates){
        super(centerCoordinates);
    }

    /**
     * rotates the hitbox of the owner object
     */
    @Override
    public void rotate(Direction direction) {

    }

    @Override
    public void move(Coordinates coordinates) {

    }

    @Override
    public Boolean isInside(Coordinates coordinates) {
        return null;
    }
}
