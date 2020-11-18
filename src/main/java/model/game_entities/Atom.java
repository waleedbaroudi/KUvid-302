package model.game_entities;
/**
 * Atom: Handles the Atom game object.
 */
public class Atom extends Projectile{

    private AtomType type;

    public Atom(Coordinates coordinate, Hitbox hitbox, Path path, AtomType type) {
        super(coordinates, hitbox, path);
        this.type = type;
    }

    public AtomType getType() {
        return type;
    }

    public void setType(AtomType type) {
        this.type = type;
    }

    @Override
    public void move() {
        setCoordinates(this.getPath().move());
    }
}
