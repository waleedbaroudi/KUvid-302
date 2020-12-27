package model.game_entities;

import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.Spinnable;
import model.game_running.CollisionVisitor;
import model.game_running.runnables.MovementRunnable;
import utils.Coordinates;

/**
 * Molecule: Handles the Molecule game object.
 */
public class Molecule extends AutonomousEntity implements Spinnable {

    private MoleculeStructure structure;

    public Molecule(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, EntityType type, MoleculeStructure structure) {
        super(coordinates, hitbox, pathPattern, type);
        superType = SuperType.MOLECULE;
        this.structure = structure;
    }

    public void setStructure(MoleculeStructure structure) {
        this.structure = structure;
    }

    public MoleculeStructure getStructure() {
        return structure;
    }

    @Override
    public double getRotationDegree() {
        return this.getHitbox().getRotationDegree();
    }

    @Override
    public void spin() {
        this.getHitbox().rotate(GameConstants.SPINNING_SPEED);
    }

    public boolean isSpinnable() {
        return structure == MoleculeStructure.LINEAR_SPINNING;
    }

    @Override
    public String toString() {
        return "Molecule{" +
                "type=" + getType() +
                ", structure=" + structure +
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
