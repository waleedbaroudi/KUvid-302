package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;

public class ZetaShield extends ShieldDecorator {

    private boolean canImprove;

    public ZetaShield(Atom atom) {
        super(atom);
        canImprove = this.getAtom().getNeutrons() == this.getAtom().getProtonsNumber();
    }

    @Override
    public double getEfficiency() {
        if (canImprove)
        return (1 - this.getAtom().getEfficiency()) * GameConstants.ZETA_EFFICIENCY_BOOST;
        return this.getAtom().getEfficiency();
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - GameConstants.ZETA_SPEED_REDUCTION_PERCENTAGE;
    }

    public boolean canImprove(){
        return this.canImprove;
    }
}
