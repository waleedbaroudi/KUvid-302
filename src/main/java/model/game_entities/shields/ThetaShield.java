package model.game_entities.shields;
import model.game_entities.Atom;

import java.util.Random;

public class ThetaShield extends ShieldDecorator{

    private final double THETA_EFFICIENCY_BOOST;
    private final double THETA_SPEED_REDUCTION_PERCENTAGE = 0.09;

    public ThetaShield(Atom atom) {
        super(atom);
        Random rand = new Random();
        THETA_EFFICIENCY_BOOST = new double[]{0.05, 0.15}[rand.nextInt(2)];
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
