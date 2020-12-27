package model.game_entities;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import model.game_running.runnables.CollisionRunnable;
import utils.Coordinates;

/**
 * Blocker: Handles the Blocker game object.
 */
public class Blocker extends AutonomousEntity {

    private double blockingRadius;
    private double explosionRadius;
    private Hitbox blockingHitbox;
    private final Hitbox explodingHitbox;

    private boolean isExploded; // Might change into different implementation.


    public Blocker(Coordinates coordinates, Hitbox hitbox, Hitbox blockingHitbox, Hitbox explodingHitbox, PathPattern pathPattern, EntityType type) {
        super(coordinates, hitbox, pathPattern, type);
        this.superType = SuperType.BLOCKER;

        this.blockingRadius = Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_BLOCKING_RADIUS;
        this.explosionRadius = Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_EXPLOSION_RADIUS;

        this.blockingHitbox = blockingHitbox;
        this.explodingHitbox = explodingHitbox;

        isExploded = false;

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
    public Hitbox getBlockingHitbox() {
        return this.blockingHitbox;
    }

    @Override
    public boolean isCollidedWith(Entity entity) {
        return this.getBlockingHitbox().isInside(getCoordinates(), entity.getHitbox().getBoundaryPoints(entity.getCoordinates()));
    }

    public boolean isCollidedWithExplodingHitbox(Entity entity) {
        return this.getExplodingHitbox().isInside(getCoordinates(), entity.getHitbox().getBoundaryPoints(entity.getCoordinates()));
    }

    @Override
    public void reachBoundary(CollisionRunnable collisionRunnable) {
        super.reachBoundary(collisionRunnable);
        collisionRunnable.BlockerBoundaryBehavior(this);
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
