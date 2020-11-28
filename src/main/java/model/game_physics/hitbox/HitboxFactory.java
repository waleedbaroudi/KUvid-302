package model.game_physics.hitbox;

import model.game_entities.enums.HitboxType;
import utils.Coordinates;
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
        if (type == HitboxType.CircularHitbox) {
            Vector arcVector = new Vector(vector.getOriginCoordinate(), new Coordinates(vector.getOriginCoordinate().getY(), vector.getPositionCoordinate().getY()));
            return new CircularHitbox(arcVector);
        }
        return new RectangularHitbox(vector);
    }
}
