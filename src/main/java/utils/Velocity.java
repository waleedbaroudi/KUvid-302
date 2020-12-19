package utils;

/**
 * Immutable representation for velocity
 */
public class Velocity {

    private final Vector velocityVector;

    public Velocity(double xv, double yv) {
        this.velocityVector = new Vector(new Coordinates(xv, yv));
    }

    public Velocity(Vector velocityVector) {
        this.velocityVector = velocityVector;
    }

    public double getXv() {
        return velocityVector.getX();
    }

    public double getYv() {
        return velocityVector.getY();
    }

    public Vector getVelocityVector() {
        return velocityVector;
    }

    /**
     * displacement of the objects based on the velocity vector
     * @param C_0 initial displacement
     * @param t time
     */
    public Coordinates getDisplacement(Coordinates C_0, double t){
        double x = C_0.getX() + t * getXv();
        double y = C_0.getY() + t * getYv();
        return new Coordinates(x, y);
    }

    public Velocity reflect(Vector n){
        return new Velocity(n.scale(n.dot(getVelocityVector()) * 2).subtract(getVelocityVector()));
    }
}
