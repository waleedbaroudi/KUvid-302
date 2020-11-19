package model.game_entities;

import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;
/**
 * Powerup: Handles the Powerup game object
 */
public class Powerup extends Projectile{

    private PowerupType type;

    public Powerup(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, PowerupType type) {
        super(coordinates, hitbox, pathPattern);
        this.type = type;
    }

    public PowerupType getType() {
        return type;
    }

    public void setType(PowerupType type) {
        this.type = type;
    }

    @Override
    public void move() {
        setCoordinates(this.getPathPattern().move());
    }
}
