package utils;

public class Velocity {

    private double Xv, Yv;

    public Velocity(double xv, double yv) {
        Xv = xv;
        Yv = yv;
    }

    public double getXv() {
        return Xv;
    }

    public double getYv() {
        return Yv;
    }

    /**
     * displacement of the objects based on the velocity vector
     * @param C_0 initial displacement
     * @param t time
     */
    public Coordinates getDisplacement(Coordinates C_0, double t){
        double x = C_0.getX() + t * Xv;
        double y = C_0.getY() + t * Yv;
        return new Coordinates(x, y);
    }
}
