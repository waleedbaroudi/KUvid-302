package model.game_physics.path_patterns;

import utils.Coordinates;

/**
 * This class serves as a super class for path patterns.
 */
public abstract class PathPattern {
    // step represent time stamp in the path. By default it starts from zero.
    private int step;
    private Coordinates initialCoords;

    /**
     * more the path patten one time step.
     * @return the new coordinates
     */
    public abstract Coordinates move();

    protected PathPattern(int step, Coordinates initialCoords) {
        this.step = step;
        this.initialCoords = initialCoords;
    }

    public PathPattern(Coordinates initialCoords) {
        this(0, initialCoords);
    }


    public int getStep() {
        return step;
    }

    public Coordinates getInitialCoords() {
        return initialCoords;
    }

    // move one time step in the path
    protected void nextStep(){
        this.step++;
    }
}
