package model.game_entities.factories;

import static model.game_building.GameConstants.*;
import model.game_entities.Atom;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPattern;
import model.game_physics.path_patterns.PathPatternFactory;
import utils.Coordinates;
import utils.MathUtils;

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
                efficiency = (1 -
                        (float)(Math.abs(numberOfNeutrons - ALPHA_PROTONS)/ALPHA_PROTONS) * ALPHA_STABILITY_CONSTANT);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.ALPHA,
                        ALPHA_STABILITY_CONSTANT, efficiency, numberOfNeutrons, ALPHA_PROTONS);

            case BETA:
                numberOfNeutrons = MathUtils.chooseFrom(BETA_NEUTRON_VALUES);
                efficiency = BETA_STABILITY_CONSTANT -
                        (float) (0.5 * Math.abs(numberOfNeutrons - BETA_PROTONS)/BETA_PROTONS);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.BETA,
                        BETA_STABILITY_CONSTANT, efficiency, numberOfNeutrons, BETA_PROTONS);

            case GAMMA:
                numberOfNeutrons = MathUtils.chooseFrom(GAMMA_NEUTRON_VALUES);
                efficiency = GAMMA_STABILITY_CONSTANT +
                        (float) Math.abs(numberOfNeutrons - GAMMA_PROTONS)/ 2 * GAMMA_PROTONS;

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.GAMMA,
                        GAMMA_STABILITY_CONSTANT, efficiency, numberOfNeutrons, GAMMA_PROTONS);

            case SIGMA:
                numberOfNeutrons = MathUtils.chooseFrom(SIGMA_NEUTRON_VALUES);
                efficiency = (1 + (SIGMA_STABILITY_CONSTANT / 2)  +
                        (float)Math.abs(numberOfNeutrons - SIGMA_PROTONS) / SIGMA_PROTONS);

                return new Atom(atomDefaultCoordinates, atomDefaultHitbox, atomDefaultPathPattern, EntityType.SIGMA,
                        SIGMA_STABILITY_CONSTANT, efficiency, numberOfNeutrons, SIGMA_PROTONS);

            default:
                return null;
        }
    }
}
