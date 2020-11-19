package utils;

public class Vector {

    private Coordinates originCoordinate, positionCoordinate;

    public Vector(Coordinates originalCoordinate, Coordinates positionCoordinate) {
        this.originCoordinate = originalCoordinate;
        this.positionCoordinate = positionCoordinate;
    }

    /**
     * Constructor for vectors with default original coordinates of 0, 0
     * @param positionCoordinate
     */
    public Vector(Coordinates positionCoordinate) {
        this(new Coordinates(0, 0), positionCoordinate);
    }


    public void setPositionCoordinate(Coordinates positionCoordinate) {
        this.positionCoordinate = positionCoordinate;
    }


    public Coordinates getPositionCoordinate() {
        return positionCoordinate;
    }
}
