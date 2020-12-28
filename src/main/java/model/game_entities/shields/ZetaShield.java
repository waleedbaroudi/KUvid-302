package model.game_entities.shields;

import model.game_entities.Atom;

import java.util.Random;

public class ZetaShield extends ShieldDecorator {

    private final double ZETA_EFFICIENCY_BOOST = 0.2;
    private final double ZETA_SPEED_REDUCTION_PERCENTAGE = 0.11;
    private boolean canImprove;

    public ZetaShield(Atom atom) {
        super(atom);
        canImprove = this.getAtom().getNeutrons() == this.getAtom().getEfficiency();
    }

    @Override
    public double getEfficiency() {

        return (1 - super.getAtom().getEfficiency()) * ZETA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - ZETA_SPEED_REDUCTION_PERCENTAGE;
    }

    public boolean cancanImprove(){
        return this.canImprove;
    }
}
