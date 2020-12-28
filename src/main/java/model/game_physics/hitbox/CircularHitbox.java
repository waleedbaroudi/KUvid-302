package model.game_physics.hitbox;

import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

import java.util.ArrayList;

public class CircularHitbox extends Hitbox {
    
    private final double radius;

    public CircularHitbox(double radius){
        this.radius = radius;
    }

    @Override
    public boolean isInside(Coordinates entityCoords, Coordinates checkCoords) {
        checkCoords = MathUtils.applyRotation(-getRotationDegree(), entityCoords, checkCoords);
        return MathUtils.isWithinCircle(entityCoords, this.radius   , checkCoords);
    }

    @Override
    public ArrayList<Coordinates> getBoundaryPoints(Coordinates entityCoords){
        Vector arcVector = new Vector(entityCoords, new Coordinates(entityCoords.getX()+this.radius, entityCoords.getY()));
        return MathUtils.coordinatesAroundCircle(arcVector, NUMBER_OF_POINTS);
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override
    public double getHeight() {
        return this.radius * 2;
    }

    @Override
    public String toString() {
        return "CircularHitbox: radius = " + this.radius +", angle = " + getRotationDegree();
    }
}
