package model.game_physics.path_patterns;

import services.utils.Coordinates;
import services.utils.Vector;
import services.utils.Velocity;

/**
 * Straight path pattern with a constant speed.
 */

public class StraightPattern extends PathPattern{
    private Velocity initialVelocity;


    public StraightPattern(Coordinates initialCoords, Velocity initialVelocity) {
        super(initialCoords);
        this.initialVelocity = initialVelocity;
    }

    public StraightPattern(Velocity initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public Velocity getInitialVelocity() {
        return initialVelocity;
    }

    @Override
    public Coordinates nextPosition() {
        setCurrentCoords(new Coordinates(getInitialVelocity().getXv() + getCurrentCoords().getX(),
                getInitialVelocity().getYv() + getCurrentCoords().getY()));
        return getCurrentCoords();
    }

    @Override
    public void reflect(Vector n) {
        this.initialVelocity = initialVelocity.reflect(n);
        PathPattern.logger.debug("[StraightPattern] pattern reflected");
    }
}
