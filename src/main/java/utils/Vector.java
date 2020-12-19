package utils;

/**
 * Immutable representation of a vecotr
 */
public class Vector {

    private final Coordinates originCoordinate, positionCoordinate;

    public Vector(Coordinates originCoordinate, Coordinates positionCoordinate) {
        this.originCoordinate = originCoordinate;
        this.positionCoordinate = positionCoordinate;
    }

    /**
     * Constructor for vectors with default original coordinates of 0, 0
     * @param positionCoordinate
     */
    public Vector(Coordinates positionCoordinate) {
        this(new Coordinates(0, 0), positionCoordinate);
    }

    public Vector rotateVector(double angle){
        return new Vector(originCoordinate, MathUtils.applyRotation(angle, this.originCoordinate, this.positionCoordinate));
    }

    public Coordinates getPositionCoordinate() {
        return positionCoordinate;
    }

    public Coordinates getOriginCoordinate(){
        return originCoordinate;
    }

    public double getX(){
    return positionCoordinate.getX() - originCoordinate.getX();
    }

    public double getY(){
    return positionCoordinate.getY() - originCoordinate.getY();
    }

    public double getMagnitude(){
        return MathUtils.vectorMagnitude(this);
    }

    /**
     * Get the dot product with Vector B
     * @param B
     * @return the dot product
     */
    public double dot(Vector B){
        return B.getX() * this.getX() + B.getY() * this.getY();
    }

    /**
     * Scale the vector by m
     * @param m
     * @return
     */
    public Vector scale(double m){
        return new Vector(originCoordinate, new Coordinates(originCoordinate.getX() + getX() * m,
                getOriginCoordinate().getY() + getY() * m));
    }

    /**
     * Take the reverse of the vector
     * @return
     */
    public Vector reverse(){
        return new Vector(this.originCoordinate, new Coordinates(getOriginCoordinate().getX() - getX(),
                getOriginCoordinate().getY() - getY()));
    }
    /**
     * Subtract B from the vector (i.e A-B)
     * @param B
     * @return
     */
    public Vector subtract(Vector B){
        return new Vector(originCoordinate, B.reverse().getPositionCoordinate());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector){
        return this.getPositionCoordinate().equals(((Vector) obj).getPositionCoordinate()) &&
                this.getOriginCoordinate().equals(((Vector) obj).getOriginCoordinate());}
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Vector(" +
                "originCoordinate=" + originCoordinate +
                ", positionCoordinate=" + positionCoordinate +
                ')';
    }
}
