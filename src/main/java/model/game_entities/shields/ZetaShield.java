package model.game_entities.shields;

import static model.game_building.GameConstants.*;
import model.game_entities.Atom;
import model.game_entities.enums.ShieldType;

public class ZetaShield extends ShieldDecorator {

    public ZetaShield(Atom atom) {
        super(atom);
        addShield(ShieldType.ZETA);
    }

    @Override
    public double getEfficiency() {
        double oldEfficiency = this.getAtom().getEfficiency();
        double efficiencyFactor = (1 - oldEfficiency) * ZETA_EFFICIENCY_BOOST;

        boolean canImprove = this.getAtom().getNumberOfNeutrons() == this.getAtom().getNumberOfProtons();

        if (canImprove)
            return oldEfficiency * (1 + efficiencyFactor);
        return oldEfficiency;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() * (1 - ZETA_SPEED_REDUCTION_PERCENTAGE);
    }
}
