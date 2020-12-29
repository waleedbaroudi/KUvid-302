package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;

public class EtaShield extends ShieldDecorator{

    public EtaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        if (this.getAtom().getNumberOfProtons() != this.getAtom().getNumberOfNeutrons()){
            return ((1 - this.getAtom().getEfficiency()) * Math.abs(this.getAtom().getNumberOfNeutrons() -
                    this.getAtom().getNumberOfProtons())) / this.getAtom().getNumberOfProtons();
        }
        return (1 - this.getAtom().getEfficiency()) * GameConstants.ETA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - GameConstants.ETA_SPEED_REDUCTION_PERCENTAGE;
    }
}
