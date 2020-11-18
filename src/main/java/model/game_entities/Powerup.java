package model.game_entities;
/**
 * Powerup: Handles the Powerup game object
 */
public class Powerup extends Projectile{

    private PowerupType type;

    public Powerup(Coordinates coordinate, Hitbox hitbox, Path path, PowerupType type) {
        super(coordinates, hitbox, path);
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
        setCoordinates(this.getPath().move());
    }
}
