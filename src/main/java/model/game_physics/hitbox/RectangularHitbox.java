package model.game_physics.hitbox;

import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

import java.util.ArrayList;

public class RectangularHitbox extends Hitbox {

    private double width, height;
    private double angle;
    private final int NUMBER_OF_POINTS = 8;

    public RectangularHitbox(double width, double height) {
        this.width = width;
        this.height = height;
        this.angle = 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void rotate(double angle) {
        this.angle += angle;
    }

    @Override
    public ArrayList<Coordinates> getBoundaryPoints(Coordinates entityCoords) {
        Coordinates cornerCoords = new Coordinates(entityCoords.getX() + getWidth()/2, entityCoords.getY() + getHeight()/2);
        Vector cornerVector = new Vector(entityCoords, cornerCoords);
        return MathUtils.getRectangularBoundaryCoordinates(cornerVector, NUMBER_OF_POINTS);
    }

    @Override
    public boolean isInside(Coordinates entityCoords, Coordinates checkCoords) {
        checkCoords = MathUtils.applyRotation(-this.angle, entityCoords, checkCoords);
        Coordinates cornerCoords = new Coordinates(entityCoords.getX() - getWidth()/2, entityCoords.getY() - getHeight()/2);
        Vector cornerVector = new Vector(entityCoords, cornerCoords);
        return MathUtils.isWithinRectangle(cornerVector, checkCoords);
    }

    @Override
    public String toString() {
        return "RectangularHitbox: height = " + this.getHeight() + ", width = " + this.getWidth() + ", angle = " + angle;
    }
}
