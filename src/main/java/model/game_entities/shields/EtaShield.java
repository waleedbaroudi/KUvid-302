package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;
import model.game_entities.Entity;
import model.game_space.GameStatistics;

public class EtaShield extends ShieldDecorator{

    public EtaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        return (1 - super.getAtom().getEfficiency()) * GameConstants.ETA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - GameStatistics.ETA_SPEED_REDUCTION_PERCENTAGE;
    }
}
