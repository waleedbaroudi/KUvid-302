package model.game_entities.shields;

import static model.game_building.GameConstants.*;
import model.game_entities.Atom;

public class LotaShield extends ShieldDecorator{

    public LotaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        double oldEfficiency = this.getAtom().getEfficiency();
        double efficiencyFactor = (1 - oldEfficiency) * LOTA_EFFICIENCY_BOOST;
        return oldEfficiency * (1 + efficiencyFactor);
    }

    @Override
    public double getAtomSpeedPercentage() {
        return this.getAtom().getAtomSpeedPercentage() * (1 - LOTA_SPEED_REDUCTION_PERCENTAGE);
    }
}
