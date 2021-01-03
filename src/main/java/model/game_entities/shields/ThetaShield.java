package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;
import model.game_entities.enums.ShieldType;
import utils.MathUtils;

import java.util.Random;

public class ThetaShield extends ShieldDecorator {

    private final double THETA_EFFICIENCY_BOOST;

    public ThetaShield(Atom atom) {
        super(atom);
        addShield(ShieldType.THETA);
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

        return oldEfficiency * (1 + efficiencyFactor);
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() * (1 - GameConstants.THETA_SPEED_REDUCTION_PERCENTAGE);
    }
}
