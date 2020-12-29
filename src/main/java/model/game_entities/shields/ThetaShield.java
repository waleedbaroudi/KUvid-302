package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;
import utils.MathUtils;

import java.util.Random;

public class ThetaShield extends ShieldDecorator {

    private final double THETA_EFFICIENCY_BOOST;

    public ThetaShield(Atom atom) {
        super(atom);
        THETA_EFFICIENCY_BOOST = getThetaEfficiency();
    }

    /**
     * @return returns either 0.05 or 0.15 (Which resemble the values that could be Theta's efficiency) randomly.
     */
    private double getThetaEfficiency() {
        return MathUtils.chooseFrom(GameConstants.thetaEfficiencyValues);
    }

    @Override
    public double getEfficiency() {
        double oldEfficiency = this.getAtom().getEfficiency();
        double efficiencyFactor = (1 - oldEfficiency) * THETA_EFFICIENCY_BOOST;

        return oldEfficiency + oldEfficiency * efficiencyFactor;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - GameConstants.THETA_SPEED_REDUCTION_PERCENTAGE;
    }
}
