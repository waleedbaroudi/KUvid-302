package model.game_entities.shields;

import model.game_entities.Atom;


public abstract class ShieldDecorator extends Atom {
    protected Atom atom;

    public ShieldDecorator(Atom atom) {
        super(atom.getCoordinates(), atom.getHitbox(), atom.getPathPattern(), atom.getType(), atom.getStabilityConstant(), atom.getEfficiency(), atom.getNumberOfProtons(), atom.getNumberOfNeutrons());
        this.atom = atom;
    }

    Atom getAtom(){
        return this.atom;
    }
}
