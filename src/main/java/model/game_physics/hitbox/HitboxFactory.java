package model.game_physics.hitbox;

import model.game_building.Configuration;
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
        return  new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_SIZE/2.0);
    }
    public Hitbox getAtomHitbox(){
        return new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.ATOM_SIZE/2.0);
    }
    public Hitbox getMoleculeHitbox(){
        return new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.MOLECULE_SIZE/2.0);
    }
    public Hitbox getPowerUpHitbox(){
        return new RectangularHitbox(Configuration.getInstance().getUnitL() * GameConstants.POWERUP_SIZE * 2,
                Configuration.getInstance().getUnitL() * GameConstants.POWERUP_SIZE * 2);
    }
}
