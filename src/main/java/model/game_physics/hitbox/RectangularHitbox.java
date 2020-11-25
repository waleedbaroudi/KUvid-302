package model.game_physics.hitbox;

import model.game_entities.enums.Direction;
import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

public class RectangularHitbox extends Hitbox {

    private Vector cornerVector;

    private int height, width;
    private double angle;

    public RectangularHitbox(Coordinates centerCoordinates, int width, int height){
        this.height = height;
        this.width = width;
        this.cornerVector = new Vector(centerCoordinates, new Coordinates(width, height));
        this.angle = 0;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return width;
    }

    public Vector getCornerVector() {
        return cornerVector;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setCornerVector(Vector cornerVector) {
        this.cornerVector = cornerVector;
    }

    @Override
    public void rotate(double angle) {
        Coordinates newPositionCoordinates = MathUtils.applyRotation(angle, cornerVector.getOriginCoordinate(), cornerVector.getPositionCoordinate());
        cornerVector.setPositionCoordinate(newPositionCoordinates);
        this.angle += angle;
    }

    @Override
    public boolean isInside(Coordinates ownerCoordinates, Coordinates objectCoordinates) {
        objectCoordinates = MathUtils.applyRotation(this.angle, cornerVector.getOriginCoordinate(), objectCoordinates);
        //objectCoordinates = MathUtils.translate(objectCoordinates, cornerVector.getOriginCoordinate());
        return MathUtils.isWithinRectangle(cornerVector, objectCoordinates);
    }

    @Override
    public String toString() {
        return "RectangularHitbox: height = " + height + ", width = " + width + ", angle = " + angle;
    }
}
