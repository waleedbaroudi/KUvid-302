package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;

public class LotaShield extends ShieldDecorator{

    public LotaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        return (1 - super.getAtom().getEfficiency()) * GameConstants.LOTA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - GameConstants.LOTA_SPEED_REDUCTION_PERCENTAGE;
    }

}
