package model.game_physics.hitbox;

import model.game_entities.enums.HitboxType;
import utils.Vector;

public class HitboxFactory {

    private static HitboxFactory factory = new HitboxFactory();

    private HitboxFactory(){

    }
    public static HitboxFactory getInstance(){
    if(factory == null){
        factory = new HitboxFactory();
    }
        return factory;
    }

    public Hitbox getHitbox(HitboxType type, Vector vector){
        switch (type){
            case RectangularHitbox:
                return new RectangularHitbox(vector);
            case CircularHitbox:
                return new CircularHitbox(vector);
            default: return new RectangularHitbox(vector);
        }

    }

}
