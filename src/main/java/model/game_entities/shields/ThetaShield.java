package model.game_entities.shields;
import model.game_entities.Atom;

import java.util.Random;

public class ThetaShield extends ShieldDecorator{

    private final double THETA_EFFICIENCY_BOOST;
    private final double THETA_SPEED_REDUCTION_PERCENTAGE = 0.09;

    public ThetaShield(Atom atom) {
        super(atom);
        THETA_EFFICIENCY_BOOST = getThetaEfficiency();
    }

    /**
     * @return returns either 0.05 or 0.15 (Which resemble the values that could be Theta's efficiency) randomly.
     */
    private double getThetaEfficiency(){
        Random rand = new Random();
        double[] thetaEfficiencyValues = {0.05, 0.15};
        return thetaEfficiencyValues[rand.nextInt(thetaEfficiencyValues.length)];
    }

    @Override
    public double getEfficiency() {
        return (1 - super.getAtom().getEfficiency()) * THETA_EFFICIENCY_BOOST;
    }

    @Override
    public double getAtomSpeedPercentage() {
        return super.getAtom().getAtomSpeedPercentage() - THETA_SPEED_REDUCTION_PERCENTAGE;
    }
}
