package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_running.Collidable;
import model.game_running.CollisionVisitor;
import utils.Coordinates;

/**
 * Entity: a Superclass for all the game objects
 */
public abstract class Entity implements Collidable {
    protected SuperType superType;
    private Coordinates coordinates;
    private Hitbox hitbox;

    public Entity(Coordinates coordinates, Hitbox hitbox) {
        this.coordinates = coordinates;
        this.hitbox = hitbox;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isCollidedWith(AutonomousEntity entity) {
        return this.getHitbox().isInside(getCoordinates(), entity.getHitbox().getBoundaryPoints(entity.getCoordinates()));
    }

    public SuperType getSuperType() {
        return superType;
    }
}
