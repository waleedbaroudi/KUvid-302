package model.game_entities;

import model.game_entities.enums.AtomType;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import utils.Coordinates;
/**
 * Atom: Handles the Atom game object.
 */
public class Atom extends Projectile{

    private AtomType type;
    private double width;
    private double height;

    public Atom(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, AtomType type) {
        super(coordinates, hitbox, pathPattern);
        this.type = type;
        setSuperType(EntityType.ATOM);
    }

    public AtomType getType() {
        return type;
    }

    public void setType(AtomType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Atom{" +
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
