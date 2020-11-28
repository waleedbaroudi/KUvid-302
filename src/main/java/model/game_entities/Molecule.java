package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.MoleculeType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;

/**
 * Molecule: Handles the Molecule game object.
 */
public class Molecule extends AutonomousEntity{

    private MoleculeType type;
    private MoleculeStructure structure;


    public Molecule(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, MoleculeType type, MoleculeStructure structure) {
        super(coordinates, hitbox, pathPattern, EntityType.MOLECULE);
        this.type = type;
        this.structure = structure;
    }


    public void setType(MoleculeType type) {
        this.type = type;
    }

    public void setStructure(MoleculeStructure structure) {
        this.structure = structure;
    }

    public MoleculeStructure getStructure() {
        return structure;
    }

    public MoleculeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Molecule{" +
                "type=" + type +
                ", structure=" + structure +
                '}';
    }

}
