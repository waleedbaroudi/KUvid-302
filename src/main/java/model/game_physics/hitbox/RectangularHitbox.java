package model.game_physics.hitbox;

import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

public class RectangularHitbox extends Hitbox {

    private Vector cornerVector;

    private double height, width;
    private double angle;

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

    public Coordinates[] getCorners(){
        Vector invertedCornerVector = MathUtils.inverseVector(cornerVector);
        Coordinates cornerCoordinates = cornerVector.getPositionCoordinate();
        Coordinates invertedCornerCoordinates = invertedCornerVector.getPositionCoordinate();

        double x1 = cornerCoordinates.getX();
        double y1 = cornerCoordinates.getY();
        double x2 = invertedCornerCoordinates.getX();
        double y2 = invertedCornerCoordinates.getY();

        Coordinates c1 = new Coordinates(x1,y1);
        Coordinates c2 = new Coordinates(x2,y2);
        Coordinates c3 = new Coordinates(x1,y2);
        Coordinates c4 = new Coordinates(x2,y1);

        return new Coordinates[]{c1, c2, c3, c4};
    }

    public void print(){
        for(Coordinates c : getCorners()) {
            System.out.println(c);
        }
    }

    @Override
    public boolean isInside(Coordinates ownerCoordinates, Coordinates objectCoordinates) {
        objectCoordinates = MathUtils.applyRotation(this.angle, cornerVector.getOriginCoordinate(), objectCoordinates);
        Coordinates translationAmount = MathUtils.translationAmount(ownerCoordinates, this.cornerVector.getOriginCoordinate());
        objectCoordinates = MathUtils.translate(objectCoordinates, translationAmount);
        return MathUtils.isWithinRectangle(cornerVector, objectCoordinates);
    }

    @Override
    public boolean isHitboxInside(Coordinates ownerCoordinates, Coordinates[] coordinates) {
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
