package utils;
import model.game_physics.hitbox.Hitbox;

import java.text.DecimalFormat;

import static java.lang.Math.*;
public class MathUtils {
    /**
     *
     * Takes a point and rotates it by a given angle.
     * @param angle the rotation angle.
     * @param rotationCenter the rotation center
     * @param coordinates the coordinates of the object to rotate.
     * @return a new Coordinates object with the rotated x and y.
     */
    public static Coordinates applyRotation(double angle, Coordinates rotationCenter, Coordinates coordinates){
    angle = Math.toRadians(angle);
    Vector vector = new Vector(new Coordinates((coordinates.getX() - rotationCenter.getX()) , (coordinates.getY() - rotationCenter.getY())));

    double rotatedX = (cos(angle) * vector.getX() - sin(angle) * vector.getY()) + rotationCenter.getX();
    double rotatedY = (sin(angle) * vector.getX() + cos(angle) * vector.getY()) + rotationCenter.getY();

    return new Coordinates(rotatedX, rotatedY);
    }

    /**
     * Takes a vector and gives an inverted version of this vector.
     * @param originalVector a Vector to be inverted.
     * @return an inverted Vector based on the original input Vector.
     */
    public static Vector inverseVector(Vector originalVector){
        double x_0 = originalVector.getPositionCoordinate().getX();
        double y_0 = originalVector.getPositionCoordinate().getY();
        double x_1 = originalVector.getOriginCoordinate().getX();
        double y_1 = originalVector.getOriginCoordinate().getY();

        double newX = (2 * x_1 - x_0);
        double newY = (2 * y_1 - y_0);

        Coordinates invertedCoordinates = new Coordinates(newX, newY);
        return new Vector(originalVector.getOriginCoordinate() , invertedCoordinates);
    }

    /**
     * Checks if a point with coordinates is inside a rectangle.
     * @param cornerVector A vector from the center of the rectangle to one of its corners.
     * @param point The coordinates of a point to be tested.
     * @return true if the coordinates are inside the rectangle defined by the given vector. False otherwise.
     */
    public static boolean isWithinRectangle(Vector cornerVector , Coordinates point){
        Vector invertedCornerVector = inverseVector(cornerVector);
        Coordinates cornerCoordinates = cornerVector.getPositionCoordinate();
        Coordinates invertedCornerCoordinates = invertedCornerVector.getPositionCoordinate();

        double x1 = cornerCoordinates.getX();
        double y1 = cornerCoordinates.getY();
        double x2 = invertedCornerCoordinates.getX();
        double y2 = invertedCornerCoordinates.getY();

        return (point.getX() <= x1 && point.getX() >= x2) && (point.getY() <= y1 && point.getY() >= y2);
    }

    /**
     * Checks if a point with coordinates is inside a circle.
     * @param radius the radius of a circle.
     * @param point The coordinates of a point to be tested.
     * @return True if the coordinates are inside the circle defined by the given vector. False otherwise.
     */
    public static boolean isWithinCircle(double radius, Coordinates centerCoordinates , Coordinates point){
        double x = point.getX();
        double y = point.getY();

        double x_0 = centerCoordinates.getX();
        double y_0 = centerCoordinates.getY();

        return pow((x - x_0), 2) + pow((y - y_0), 2) <= pow(radius, 2);
    }
    /**
     * Given a Vector object, returns its magnitude.
     * @param vector A Vector object.
     * @return Magnitude of the Vector object.
     */
    public static double vectorMagnitude(Vector vector){
    double x_1 = vector.getOriginCoordinate().getX();
    double x_2 = vector.getPositionCoordinate().getX();

    double y_1 = vector.getOriginCoordinate().getY();
    double y_2 = vector.getPositionCoordinate().getY();

    return sqrt(pow((x_1 - x_2), 2) + pow((y_1 - y_2), 2));
    }

    /**
     * Given a point and origin coordinates, returns the amount of translation required to translate a point to a given origin.
     * @param point a given point.
     * @param origin The origin coordinates.
     * @return The required amount of translation.
     */
    public static Coordinates translationAmount(Coordinates point, Coordinates origin){
        double translatedAmountX = point.getX() - origin.getX();
        double translatedAmountY = point.getY() - origin.getY();
        return new Coordinates(translatedAmountX, translatedAmountY);
    }

    /**
     * Translates a point according to given amount.
     * @param point a given point.
     * @param translation the translation amount.
     * @return returns the coordinates of the translated point.
     */
    public static Coordinates translate(Coordinates point, Coordinates translation){
        double translatedAmountX = point.getX() + translation.getX();
        double translatedAmountY = point.getY() + translation.getY();
        return new Coordinates(translatedAmountX, translatedAmountY);
    }

}
