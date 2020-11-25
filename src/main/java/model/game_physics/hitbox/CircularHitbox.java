package model.game_physics.hitbox;

import model.game_entities.enums.Direction;
import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

public class CircularHitbox extends Hitbox {

    private Vector arcVector;
    private double radius;
    private double angle;

    public CircularHitbox(double radius, Coordinates centerCoordinates){
        this.radius = radius;
        this.arcVector = new Vector(centerCoordinates, new Coordinates(0,0));
        this.angle = 0;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius(){
        return this.radius;
    }

    public void setArcVector(Vector arcVector) {
        this.arcVector = arcVector;
    }

    public Vector getArcVector() {
        return this.arcVector;
    }

    /**
     * rotates the hitbox of the owner object
     */
    @Override
    public void rotate(double angle) {
        Coordinates newPositionCoordinates = MathUtils.applyRotation(angle, arcVector.getOriginCoordinate(), arcVector.getPositionCoordinate());
        arcVector.setPositionCoordinate(newPositionCoordinates);
        this.angle += angle;
    }
    @Override
    public boolean isInside(Coordinates ownerCoordinates, Coordinates objectCoordinates) {
        objectCoordinates = MathUtils.applyRotation(angle, this.arcVector.getOriginCoordinate(), objectCoordinates);
        return MathUtils.isWithinCircle(this.radius, this.arcVector.getOriginCoordinate(), objectCoordinates);
    }

    @Override
    public String toString() {
        return "CircularHitbox: radius = " + radius +", angle = " + angle;
    }
}
