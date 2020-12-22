package model.game_entities;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import utils.Coordinates;

/**
 * Blocker: Handles the Blocker game object.
 */
public class Blocker extends AutonomousEntity {

    private double blockingRadius;
    private double explosionRadius;
    private Hitbox explodingHitbox;
    public Blocker(Coordinates coordinates, Hitbox blockingHitbox, Hitbox explodingHitbox, PathPattern pathPattern, EntityType type) {
        super(coordinates, blockingHitbox, pathPattern, type);
        this.superType = SuperType.BLOCKER;

        this.blockingRadius = Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_BLOCKING_RADIUS;
        this.explosionRadius = Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_EXPLODING_RADIUS;

        this.explodingHitbox = explodingHitbox;
    }

    public double getBlockingRadius() {
        return blockingRadius;
    }

    public void setExplosionRadius(double explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    public void setBlockingRadius(double blockingRadius) {
        this.blockingRadius = blockingRadius;
    }

    public double getExplosionRadius() {
        return explosionRadius;
    }

    public Hitbox getExplodingHitbox(){
        return this.explodingHitbox;
    }

    public boolean isCollidedWithExplodingHitbox(AutonomousEntity entity) {
        return this.getExplodingHitbox().isInside(getCoordinates(), entity.getHitbox().getBoundaryPoints(entity.getCoordinates()));
    }

    @Override
    public String toString() {
        return "Blocker{" +
                "blockingRadius=" + blockingRadius +
                ", explosionRadius=" + explosionRadius +
                ", type=" + getType() +
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
