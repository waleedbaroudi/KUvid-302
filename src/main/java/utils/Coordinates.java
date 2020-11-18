package utils;

public class Coordinates {

    private double X, Y;

    public Coordinates(double x, double y) {
        X = x;
        Y = y;
    }

    public double getX() {
        return X;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates){
            return ((Coordinates) obj).getX() == this.getX() && ((Coordinates) obj).getY() == this.getY();
        }
        return super.equals(obj);
    }


    public double getY() {
        return Y;
    }

}
