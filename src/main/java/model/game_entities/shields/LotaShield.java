package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;

public class LotaShield extends ShieldDecorator{

    public LotaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        double oldEfficiency = this.getAtom().getEfficiency();
        double efficiencyFactor = (1 - oldEfficiency) * GameConstants.LOTA_EFFICIENCY_BOOST;
        return oldEfficiency + oldEfficiency * efficiencyFactor;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - GameConstants.LOTA_SPEED_REDUCTION_PERCENTAGE;
    }
}
