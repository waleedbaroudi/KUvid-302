package utils;

public class Vector {

    private Coordinates originCoordinate, positionCoordinate;

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

    public void rotateVector(double angle){
        this.setPositionCoordinate(MathUtils.applyRotation(angle, this.originCoordinate, this.positionCoordinate));
    }

    public void setPositionCoordinate(Coordinates positionCoordinate) {
        this.positionCoordinate = positionCoordinate;
    }

    public Coordinates getPositionCoordinate() {
        return positionCoordinate;
    }

    public void setOriginCoordinate(Coordinates originCoordinate){
        this.originCoordinate = originCoordinate;
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
        return 0;
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
