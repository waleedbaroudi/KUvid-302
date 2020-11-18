package model.game_physics.path_patterns;

import utils.Coordinates;
import utils.Velocity;

/**
 * Straight path pattern with a constant speed.
 */

public class StraightPattern extends PathPattern{
    private Velocity initialVelocity;

    public StraightPattern(Coordinates initialCoords, Velocity initialVelocity) {
        super(initialCoords);
        this.initialVelocity = initialVelocity;
    }

    @Override
    public Coordinates move() {
        this.nextStep();
        return initialVelocity.getDisplacement(this.getInitialCoords(), this.getStep());
    }


}
