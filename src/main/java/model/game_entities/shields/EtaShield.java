package model.game_entities.shields;

import model.game_building.GameConstants;
import model.game_entities.Atom;

public class EtaShield extends ShieldDecorator{

    public EtaShield(Atom atom) {
        super(atom);
    }

    @Override
    public double getEfficiency() {
        double oldEfficiency = this.getAtom().getEfficiency();
        int numberOfNeutrons = this.getAtom().getNumberOfNeutrons();
        int numberOfProtons = this.getAtom().getNumberOfProtons();

        double efficiencyFactor = 0;

        if (numberOfProtons != numberOfNeutrons){
            efficiencyFactor = (1 - oldEfficiency) * Math.abs(numberOfNeutrons - numberOfProtons) /
                    (double) numberOfProtons;
            return oldEfficiency * (1 + efficiencyFactor);
        }
        efficiencyFactor = (1 - oldEfficiency) * GameConstants.ETA_EFFICIENCY_BOOST;
        return oldEfficiency * (1 + efficiencyFactor);
    }

    @Override
    public double getAtomSpeedPercentage() {
        return this.getAtom().getAtomSpeedPercentage() * (1 - GameConstants.ETA_SPEED_REDUCTION_PERCENTAGE);
    }
}
