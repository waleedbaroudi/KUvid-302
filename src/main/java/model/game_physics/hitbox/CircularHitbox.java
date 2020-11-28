package model.game_physics.hitbox;

import model.game_entities.enums.Direction;
import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

public class CircularHitbox extends Hitbox {

    private Vector arcVector;

    private double angle;
    private final int NUMBER_OF_POINTS = 8;

    public CircularHitbox(Vector arcVector){
        Coordinates positionCoordinates = new Coordinates(arcVector.getOriginCoordinate().getX() +
                arcVector.getPositionCoordinate().getX(), arcVector.getOriginCoordinate().getY() +
                arcVector.getPositionCoordinate().getY());
        this.arcVector = new Vector(arcVector.getOriginCoordinate(), positionCoordinates);
        this.angle = 0;
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
        objectCoordinates = MathUtils.applyRotation(-this.angle, ownerCoordinates, objectCoordinates);
        Coordinates translationAmount = MathUtils.translationAmount(ownerCoordinates, this.arcVector.getOriginCoordinate());
        objectCoordinates = MathUtils.translate(objectCoordinates, translationAmount);
        return MathUtils.isWithinCircle(MathUtils.vectorMagnitude(this.arcVector), this.arcVector.getOriginCoordinate(), objectCoordinates);
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
    public Coordinates[] getBoundaryCoordinates(){
    return MathUtils.coordinatesAroundCircle(arcVector, NUMBER_OF_POINTS);
    }
    @Override
    public String toString() {
        return "CircularHitbox: radius = " + MathUtils.vectorMagnitude(this.arcVector) +", angle = " + angle;
    }
}
