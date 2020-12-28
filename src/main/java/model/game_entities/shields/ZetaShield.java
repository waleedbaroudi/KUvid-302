package model.game_entities.shields;

import model.game_entities.Atom;

import java.util.Random;

public class ZetaShield extends ShieldDecorator{

    private final double ZETA_EFFICIENCY_BOOST;
    private final double ZETA_SPEED_REDUCTION_PERCENTAGE = 0.2;

    public ZetaShield(Atom atom) {
        super(atom);
        Random rand = new Random();
        ZETA_EFFICIENCY_BOOST = 2;
    }

    @Override
    public double getEfficiency() {
        return (1 - super.getAtom().getEfficiency()) * ZETA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - ZETA_SPEED_REDUCTION_PERCENTAGE;
    }

}
