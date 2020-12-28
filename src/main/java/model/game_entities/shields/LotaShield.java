package model.game_entities.shields;

import model.game_entities.Atom;

public class LotaShield extends ShieldDecorator{

    private final double LOTA_EFFICIENCY_BOOST = 0.1;
    private final double LOTA_SPEED_REDUCTION_PERCENTAGE = 0.07;

    public LotaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        return (1 - super.getAtom().getEfficiency()) * LOTA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - LOTA_SPEED_REDUCTION_PERCENTAGE;
    }

}
