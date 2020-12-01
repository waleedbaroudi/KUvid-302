package model.game_physics.hitbox;

import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

import java.util.ArrayList;

public class CircularHitbox extends Hitbox {
    
    private double angle;
    private double radius;
    private final int NUMBER_OF_POINTS = 8;

    public CircularHitbox(double radius){
        this.radius = radius;
        this.angle = 0;
    }
    
    /**
     * rotates the hitbox of the owner object
     */
    @Override
    public void rotate(double angle) {
        this.angle += angle;
    }

    @Override
    public boolean isInside(Coordinates entityCoords, Coordinates checkCoords) {
        checkCoords = MathUtils.applyRotation(-this.angle, entityCoords, checkCoords);
        return MathUtils.isWithinCircle(entityCoords, this.radius, checkCoords);
    }

    @Override
    public ArrayList<Coordinates> getBoarderPoints(Coordinates entityCoords){
        Vector arcVector = new Vector(entityCoords, new Coordinates(entityCoords.getX()+this.radius, entityCoords.getY()));
        return MathUtils.coordinatesAroundCircle(arcVector, NUMBER_OF_POINTS);
    }

    @Override
    public String toString() {
        return "CircularHitbox: radius = " + this.radius +", angle = " + angle;
    }
}
