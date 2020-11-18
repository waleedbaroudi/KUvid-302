package model.game_physics.hitbox;

import model.game_entities.enums.Direction;

public class RectangularHitbox extends Hitbox {

    Vector cornerVector;

    public Vector getCornerVector() {
        return cornerVector;
    }

    public void setCornerVector(Vector cornerVector) {
        this.cornerVector = cornerVector;
    }

    public RectangularHitbox(int height, int width){
        super(centerCoordinates);
    }

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

    public Coordinate getCenterCoordinates() {
        return centerCoordinates;
    }

    public void setCenterCoordinates(Coordinate centerCoordinates) {
        this.centerCoordinates = centerCoordinates;
    }
}
