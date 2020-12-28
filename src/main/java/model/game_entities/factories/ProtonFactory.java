package model.game_entities.factories;

import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;

public class ProtonFactory {

    private static ProtonFactory factory = new ProtonFactory();

    private ProtonFactory() {
    }

    public static ProtonFactory getInstance() {
        if (factory == null) {
            factory = new ProtonFactory();
        }
        return factory;
    }

    public int getProtons(EntityType type) {
        switch (type) {
            case ALPHA:
                return GameConstants.ALPHA_PROTONS;
            case BETA:
                return GameConstants.BETA_PROTONS;
            case GAMMA:
                return GameConstants.GAMMA_PROTONS;
            case SIGMA:
                return GameConstants.SIGMA_PROTONS;
            default:
                return 0;
        }
    }

}
