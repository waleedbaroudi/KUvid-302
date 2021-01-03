package model.game_physics.hitbox;

import com.fasterxml.jackson.annotation.JsonTypeName;
import utils.Coordinates;
import utils.MathUtils;
import utils.Vector;

import java.util.ArrayList;
@JsonTypeName("rectangular-hitbox")
public class RectangularHitbox extends Hitbox {

    private final double width;
    private final double height;

    public RectangularHitbox(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public ArrayList<Coordinates> getBoundaryPoints(Coordinates entityCoords) {
        Coordinates cornerCoords = new Coordinates(entityCoords.getX() + getWidth()/2, entityCoords.getY() + getHeight()/2);
        Vector cornerVector = new Vector(entityCoords, cornerCoords);
        return MathUtils.getRectangularBoundaryCoordinates(cornerVector, NUMBER_OF_POINTS);
    }

    @Override
    public boolean isInside(Coordinates entityCoords, Coordinates checkCoords) {
        checkCoords = MathUtils.applyRotation(-getRotationDegree(), entityCoords, checkCoords);
        Coordinates cornerCoords = new Coordinates(entityCoords.getX() + getWidth()/2, entityCoords.getY() + getHeight()/2);
        Vector cornerVector = new Vector(entityCoords, cornerCoords);
        return MathUtils.isWithinRectangle(cornerVector, checkCoords);
    }

    @Override
    public String toString() {
        return "RectangularHitbox: height = " + this.getHeight() + ", width = " + this.getWidth() + ", angle = " + getRotationDegree();
    }
}
