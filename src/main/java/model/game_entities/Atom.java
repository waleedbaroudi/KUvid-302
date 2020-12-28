package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import utils.Coordinates;
/**
 * Atom: Handles the Atom game object.
 */
public class Atom extends Projectile{

    private double width;
    private double height;

    public Atom(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, EntityType type) {
        super(coordinates, hitbox, pathPattern, type);
        superType = SuperType.ATOM;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "type=" + getType() +
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
