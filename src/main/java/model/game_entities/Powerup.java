package model.game_entities;

import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import utils.Coordinates;

/**
 * Powerup: Handles the Powerup game object
 */
public class Powerup extends Projectile {
    private final boolean falling; // This variable indicates whether the powerup is falling or being shot.
    public Powerup(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, EntityType type, boolean falling) {
        super(coordinates, hitbox, pathPattern, type);
        superType = SuperType.POWERUP;
        this.falling = falling;
    }

    @Override
    public String toString() {
        return "Powerup{" +
                "type=" + getType() +
                '}';
    }

    public boolean isFalling(){
        return this.falling;
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

    @Override
    public double getSpeedPercentage() {
        return GameConstants.DEFAULT_POWERUP_SPEED_PERCENTAGE;
    }
}
