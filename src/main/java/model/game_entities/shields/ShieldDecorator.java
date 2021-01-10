package model.game_entities.shields;

import model.game_entities.Atom;


public abstract class ShieldDecorator extends Atom {
    protected Atom atom;

    public ShieldDecorator(Atom atom) {
        super(atom.getCoordinates(), atom.getHitbox(), atom.getPathPattern(), atom.getType(), atom.getStabilityConstant(), atom.getEfficiency(), atom.getNumberOfProtons(), atom.getNumberOfNeutrons());
        this.atom = atom;
        setShieldTuple(atom.getShieldTuple());
    }

    Atom getAtom() {
        return this.atom;
    }


    @Override
    public double getSpeedPercentage() {
        //MODIFIES: indirectly, the speed of the original atom, or possibly the shielded atom
        //EFFECTS: the speed of the original atom/shielded atom will be reduced by a percentage depending on the
        //type of the shield

        return super.getSpeedPercentage() * getAtomSpeedPercentage();
    }
}
