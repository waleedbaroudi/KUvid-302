package model.game_physics.hitbox;

import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

public class RectangularHitbox extends Hitbox {

    private Vector cornerVector;

    private double height, width;
    private double angle;
    private final int NUMBER_OF_POINTS = 8;

    public RectangularHitbox(Coordinates centerCoordinates, double width, double height){
        this.height = height;
        this.width = width;
        this.cornerVector = new Vector(centerCoordinates, new Coordinates((width / 2) + centerCoordinates.getX(), (height / 2) + centerCoordinates.getY()));
        this.angle = 0;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public Vector getCornerVector() {
        return this.cornerVector;
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

    public Coordinates[] getBoundaryCoordinates(){
    return MathUtils.getRectangularBoundaryCoordinates(cornerVector, NUMBER_OF_POINTS);
    }

    @Override
    public boolean isInside(Coordinates ownerCoordinates, Coordinates objectCoordinates) {
        objectCoordinates = MathUtils.applyRotation(this.angle, cornerVector.getOriginCoordinate(), objectCoordinates);
        Coordinates translationAmount = MathUtils.translationAmount(ownerCoordinates, this.cornerVector.getOriginCoordinate());
        objectCoordinates = MathUtils.translate(objectCoordinates, translationAmount);
        return MathUtils.isWithinRectangle(cornerVector, objectCoordinates);
    }

    @Override
    public boolean isInside(Coordinates ownerCoordinates, Coordinates[] coordinates) {
        for (Coordinates c : coordinates){
            if (this.isInside(ownerCoordinates, c))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "RectangularHitbox: height = " + height + ", width = " + width + ", angle = " + angle;
    }
}
