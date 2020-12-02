package model.game_physics.path_patterns;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.Coordinates;

/**
 * This class serves as a super class for path patterns.
 */
public abstract class PathPattern {
    // step represent time stamp in the path. By default it starts from zero.
    private Coordinates currentCoords;
    private static Logger logger = Logger.getLogger(PathPattern.class.getName());

    protected PathPattern (){}
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

    public Coordinates getNextNthPosition(Coordinates currentCoords, int N){
        for(int i = 0;i<N;i++){
            currentCoords = nextPosition();
        }
        return currentCoords;
    }

    /**
     * get the next position in the path based on the given coordinates
     * @return the new coordinates
     */
    public abstract Coordinates nextPosition();

}
