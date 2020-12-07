package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.Collidable;
import model.game_running.CollisionVisitor;
import utils.Coordinates;

/**
 * Powerup: Handles the Powerup game object
 */
public class Powerup extends Projectile {

    private PowerupType type;

    public Powerup(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, PowerupType type) {
        super(coordinates, hitbox, pathPattern);
        this.type = type;
        setSuperType(EntityType.POWERUP);
    }

    public PowerupType getType() {
        return type;
    }

    public void setType(PowerupType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Powerup{" +
                "type=" + type +
                '}';
    }



    // visitor pattern. Double delegation
    @Override
    public void collideWith(CollisionVisitor visitor, Atom atom) {
        visitor.handleCollision(this, atom);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Blocker blocker) {
        visitor.handleCollision(this, blocker);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Molecule molecule) {
        visitor.handleCollision(this, molecule);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Powerup powerup) {
        visitor.handleCollision(this, powerup);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Shooter shooter) {
        visitor.handleCollision(this, shooter);
    }

    @Override
    public void acceptCollision(CollisionVisitor visitor, Entity entity) {
        entity.collideWith(visitor, this);
    }
}
