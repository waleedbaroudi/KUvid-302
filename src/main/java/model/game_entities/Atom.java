package model.game_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.game_building.GameBundle;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import utils.Coordinates;

/**
 * Atom: Handles the Atom game object.
 */
public class Atom extends Projectile {
    public Atom(@JsonProperty("coordinates")Coordinates coordinates,
                @JsonProperty("hitbox")Hitbox hitbox,
                @JsonProperty("pathPattern")PathPattern pathPattern,
                @JsonProperty("entityType")EntityType type) {
        super(coordinates, hitbox, pathPattern, type);
        superType = SuperType.ATOM;
    }

//    public Atom() {
//
//    }

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
    public void saveState(GameBundle.Builder builder) {
        builder.addEntity(this);
    }

    @Override
    public String toString() {
        return "Atom{" +
                "type=" + getEntityType() +
                '}';
    }
}
