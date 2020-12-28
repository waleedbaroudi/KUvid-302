package model.game_entities.factories;

import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import utils.MathUtils;

public class NeutronFactory {

    private static NeutronFactory factory = new NeutronFactory();

    private NeutronFactory() {
    }

    public static NeutronFactory getInstance() {
        if (factory == null) {
            factory = new NeutronFactory();
        }
        return factory;
    }

    public int getNeutrons(EntityType type) {
        switch (type) {
            case ALPHA:
                return MathUtils.chooseFrom(GameConstants.ALPHA_NEUTRON_VALUES);
            case BETA:
                return MathUtils.chooseFrom(GameConstants.BETA_NEUTRON_VALUES);
            case GAMMA:
                return MathUtils.chooseFrom(GameConstants.GAMMA_NEUTRON_VALUES);
            case SIGMA:
                return MathUtils.chooseFrom(GameConstants.SIGMA_NEUTRON_VALUES);
            default:
                return 0;
        }
    }
}
