package model.game_physics.path_patterns;

import com.fasterxml.jackson.annotation.JsonTypeName;
import utils.Coordinates;
import utils.Vector;
import utils.Velocity;

/**
 * Straight path pattern with a constant speed.
 */
@JsonTypeName("straight-pattern")
public class StraightPattern extends PathPattern{
    private Velocity initialVelocity;

    public StraightPattern(){
    }
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
