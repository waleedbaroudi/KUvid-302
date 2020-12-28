package model.game_entities.factories;

import model.game_entities.enums.EntityType;

public class NeutronFactory {

    private static NeutronFactory factory = new NeutronFactory();

    private NeutronFactory(){
    }

    public static NeutronFactory getInstance(){
        if (factory == null){
            factory = new NeutronFactory();
        }
        return factory;
    }

    public int getNeutrons(EntityType type){
        switch (type){
            case ALPHA:
                return 0;
            case BETA:
                return 1;
            case GAMMA:
                return 2;
            case SIGMA:
                return 3;
            default:
                return 4;
        }
    }
}
