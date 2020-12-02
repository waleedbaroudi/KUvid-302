package model.game_physics.hitbox;

import model.game_entities.enums.HitboxType;
import model.game_physics.path_patterns.PathPattern;
import model.game_physics.path_patterns.StraightPattern;
import model.game_physics.path_patterns.ZigzagPatten;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.Vector;
import utils.Velocity;

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

    public Hitbox getBlockerHitbox(){
        return  new CircularHitbox(GameConstants.BlockerDimensions.width/2.0);
    }
    public Hitbox getAtomHitbox(){
        return new CircularHitbox(GameConstants.AtomDimensions.width/2.0);
    }
    public Hitbox getMoleculeHitbox(){
        return new CircularHitbox(GameConstants.MoleculeDimensions.width/2.0);
    }
    public Hitbox getPowerUpHitbox(){
        return new RectangularHitbox(GameConstants.PowerupDimensions.width, GameConstants.PowerupDimensions.height);
    }
}
