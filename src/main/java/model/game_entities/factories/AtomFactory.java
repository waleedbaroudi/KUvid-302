package model.game_entities.factories;

import static model.game_building.GameConstants.*;
import model.game_entities.Atom;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPattern;
import model.game_physics.path_patterns.PathPatternFactory;
import services.utils.Coordinates;
import services.utils.MathUtils;

public class AtomFactory {

    private static AtomFactory factory = new AtomFactory();

    private AtomFactory(){
    }

    public static AtomFactory getInstance(){
        if (factory == null){
            factory = new AtomFactory();
        }
        return factory;
    }

    public Atom getAtom(EntityType type){
        Coordinates atomDefaultCoordinates = new Coordinates(0,0);

        Hitbox atomDefaultHitbox = HitboxFactory.getInstance().getAtomHitbox();
        PathPattern atomDefaultPathPattern = PathPatternFactory.getInstance().getAtomPathPattern();

        int numberOfNeutrons = 0;
        double efficiency = 0.0;

        switch (type){
            case ALPHA:
                numberOfNeutrons = MathUtils.chooseFrom(ALPHA_NEUTRON_VALUES);
                efficiency = alphaEfficiency(numberOfNeutrons);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.ALPHA,
                        ALPHA_STABILITY_CONSTANT, efficiency, ALPHA_PROTONS, numberOfNeutrons);

            case BETA:
                numberOfNeutrons = MathUtils.chooseFrom(BETA_NEUTRON_VALUES);
                efficiency = betaEfficiency(numberOfNeutrons);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.BETA,
                        BETA_STABILITY_CONSTANT, efficiency, BETA_PROTONS, numberOfNeutrons);

            case GAMMA:
                numberOfNeutrons = MathUtils.chooseFrom(GAMMA_NEUTRON_VALUES);
                efficiency = gammaEfficiency(numberOfNeutrons);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.GAMMA,
                        GAMMA_STABILITY_CONSTANT, efficiency, GAMMA_PROTONS, numberOfNeutrons);

            case SIGMA:
                numberOfNeutrons = MathUtils.chooseFrom(SIGMA_NEUTRON_VALUES);
                efficiency = sigmaEfficiency(numberOfNeutrons);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.SIGMA,
                        SIGMA_STABILITY_CONSTANT, efficiency, SIGMA_PROTONS, numberOfNeutrons);

            default:
                return null;
        }
    }

    private double alphaEfficiency(int numberOfNeutrons){
        return  1 -  (Math.abs(numberOfNeutrons - ALPHA_PROTONS) / (double) ALPHA_PROTONS) * ALPHA_STABILITY_CONSTANT;
    }

    private double betaEfficiency(int numberOfNeutrons){
        return BETA_STABILITY_CONSTANT -  (0.5 * Math.abs(numberOfNeutrons - BETA_PROTONS) / (double) BETA_PROTONS);
    }

    private double gammaEfficiency(int numberOfNeutrons){
        return GAMMA_STABILITY_CONSTANT + (float) Math.abs(numberOfNeutrons - GAMMA_PROTONS)  / (double) 2 * GAMMA_PROTONS;
    }

    private double sigmaEfficiency(int numberOfNeutrons){
        return (1 + (SIGMA_STABILITY_CONSTANT / 2) + (float)Math.abs(numberOfNeutrons - SIGMA_PROTONS) / (double) SIGMA_PROTONS);
    }
}
