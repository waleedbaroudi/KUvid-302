package model.game_physics.path_patterns;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import services.utils.Coordinates;
import services.utils.Vector;
import services.utils.Velocity;

/**
 * Straight path pattern with a constant speed.
 */
@JsonTypeName("straight-pattern")
@JsonIdentityReference(alwaysAsId = true)
public class StraightPattern extends PathPattern{
    private Velocity initialVelocity;
    private boolean reflected;

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

    public boolean isReflected() {
        return reflected;
    }

    @Override
    public Coordinates nextPosition() {
        setCurrentCoords(new Coordinates(getInitialVelocity().getXv() + getCurrentCoords().getX(),
                getInitialVelocity().getYv() + getCurrentCoords().getY()));
        this.reflected = false;
        return getCurrentCoords();
    }

    @Override
    public void reflect(Vector n) {
        if(!isReflected()) {
            this.initialVelocity = initialVelocity.reflect(n);
            this.reflected = true;
            PathPattern.logger.debug("[StraightPattern] pattern reflected");
        }
        else {
            PathPattern.logger.debug("[StraightPattern] pattern was already reflected");
        }
    }

    @Override
    public void setVelocity(double velocity) {
        initialVelocity = new Velocity(initialVelocity.getXv() * velocity, initialVelocity.getYv() * velocity);
    }
}
