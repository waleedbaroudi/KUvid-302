package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_entities.factories.NeutronFactory;
import model.game_entities.factories.ProtonFactory;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import utils.Coordinates;

/**
 * Atom: Handles the Atom game object.
 */
public class Atom extends Projectile {

    private double width;
    private double height;

    private double stabilityConstant;
    private double efficiency;
    private int numberOfProtons;
    private int numberOfNeutrons;


    private final double ATOM_SPEED_PERCENTAGE = 1;

    public Atom(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, EntityType type, double stabilityConstant, double efficiency, int numberOfProtons, int numberOfNeutrons) {
        super(coordinates, hitbox, pathPattern, type);
        superType = SuperType.ATOM;

        this.stabilityConstant = stabilityConstant;
        this.efficiency = efficiency;

        this.numberOfProtons = numberOfProtons;
        this.numberOfNeutrons = numberOfNeutrons;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "type=" + getType() +
                '}';
    }

    public double getEfficiency() {
        return this.efficiency;
    }

    public double getStabilityConstant(){
        return this.stabilityConstant;
    }

    public int getNumberOfNeutrons() {
        return numberOfNeutrons;
    }

    public int getNumberOfProtons(){
        return this.numberOfProtons;
    }

    public double getAtomSpeedPercentage() {
        return ATOM_SPEED_PERCENTAGE;
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
