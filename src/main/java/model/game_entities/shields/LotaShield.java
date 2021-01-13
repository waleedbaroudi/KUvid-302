package model.game_entities.shields;

import model.game_entities.Atom;

import static model.game_building.GameConstants.LOTA_EFFICIENCY_BOOST;
import static model.game_building.GameConstants.LOTA_SPEED_REDUCTION_PERCENTAGE;

public class LotaShield extends ShieldDecorator {

    public LotaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        //MODIFIES: the efficiency of the original atom, or possibly the shielded atom
        //EFFECTS: the efficiency of the original atom/shielded atom will be reduced depending on some criteria
        double oldEfficiency = this.getAtom().getEfficiency();
        double efficiencyFactor = (1 - oldEfficiency) * LOTA_EFFICIENCY_BOOST;
        return oldEfficiency * (1 + efficiencyFactor);
    }

    @Override
    public double getAtomSpeedPercentage() {
        //MODIFIES: the speed of the original atom, or possibly the shielded atom
        //EFFECTS: the speed of the original atom/shielded atom will be reduced by a 7%
        return this.getAtom().getAtomSpeedPercentage() * (1 - LOTA_SPEED_REDUCTION_PERCENTAGE);
    }
}
