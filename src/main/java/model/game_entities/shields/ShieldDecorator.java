package model.game_entities.shields;

import model.game_entities.Atom;


public abstract class ShieldDecorator extends Atom {
    protected Atom atom;

    public ShieldDecorator(Atom atom) {
        super(atom.getCoordinates(), atom.getHitbox(), atom.getPathPattern(), atom.getType());
        this.atom = atom;
    }

    Atom getAtom(){
        return this.atom;
    }
}
