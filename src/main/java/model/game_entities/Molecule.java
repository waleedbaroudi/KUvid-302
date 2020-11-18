package model.game_entities;
/**
 * Molecule: Handles the Molecule game object.
 */
public class Molecule extends AutonomousEntity{

    private MoleculeType type;
    private MoleculeStructure structure;

    public Molecule(Coordinates coordinates, Hitbox hitbox, Path path, MoleculeType type, MoleculeStructure structure) {
        super(coordinates, hitbox, path);
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
    public void move() {
        setCoordinates(this.getPath().move());
    }
}
