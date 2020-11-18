package model.game_physics.hitbox;

import model.game_entities.enums.Direction;
import utils.Coordinates;
import utils.Vector;

public class RectangularHitbox extends Hitbox {

    Vector cornerVector;

    public Vector getCornerVector() {
        return cornerVector;
    }

    public void setCornerVector(Vector cornerVector) {
        this.cornerVector = cornerVector;
    }

    public RectangularHitbox(int height, int width,Coordinates centerCoordinates){
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

    public Coordinates getCenterCoordinates() {
        return centerCoordinates;
    }

    public void setCenterCoordinates(Coordinates centerCoordinates) {
        this.centerCoordinates = centerCoordinates;
    }
}
