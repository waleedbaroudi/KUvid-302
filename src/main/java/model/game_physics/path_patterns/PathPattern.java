package model.game_physics.path_patterns;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.Coordinates;
import utils.Vector;

/**
 * This class serves as a super class for path patterns.
 */
public abstract class PathPattern implements Cloneable {
    // step represent time stamp in the path. By default it starts from zero.
    private Coordinates currentCoords;
    public static Logger logger = Logger.getLogger(PathPattern.class.getName());

    protected PathPattern() {
    }

    protected PathPattern(Coordinates currentCoords) {
        // Turn off the logger
        logger.setLevel(Level.OFF);
        this.currentCoords = currentCoords;
    }

    public Coordinates getCurrentCoords() {
        return currentCoords;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCurrentCoords(Coordinates currentCoords) {
        this.currentCoords = currentCoords;
    }

    //TODO check if we need this? (Sarieh)
    public Coordinates getNextNthPosition(Coordinates currentCoords, int N) {
        for (int i = 0; i < N; i++)
            currentCoords = nextPosition();
        return currentCoords;
    }

    /**
     * get the next position in the path based on the given coordinates
     *
     * @return the new coordinates
     */
    public abstract Coordinates nextPosition();

    /**
     * Given a normalized normal vector of a wall that the path has collided with, reflect the path
     * pattern accordingly.
     *
     * @param n the vector to be reflected
     */
    public abstract void reflect(Vector n);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setVelocity(double velocity){};
}
