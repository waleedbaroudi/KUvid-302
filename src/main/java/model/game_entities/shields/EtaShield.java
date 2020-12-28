package model.game_entities.shields;

import model.game_entities.Atom;
import model.game_entities.Entity;

public class EtaShield extends ShieldDecorator{

    private final double ETA_EFFICIENCY_BOOST = 0.05;
    private final double ETA_SPEED_REDUCTION_PERCENTAGE = 0.05;

    public EtaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        return (1 - super.getAtom().getEfficiency()) * ETA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - ETA_SPEED_REDUCTION_PERCENTAGE;
    }
}
