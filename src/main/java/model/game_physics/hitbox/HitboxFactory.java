package model.game_physics.hitbox;

import model.game_building.Configuration;
import model.game_building.GameConstants;

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


    //todo ask Moayad about these
    public Hitbox getBlockerHitbox(){
        return  new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_DIAMETER);
    }
    public Hitbox getBlockingHitbox(){
        return  new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_BLOCKING_RADIUS);
    }

    public Hitbox getExplosionHitbox(){
        return  new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.BLOCKER_EXPLOSION_RADIUS);
    }

    public Hitbox getAtomHitbox(){
        return new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.ATOM_RADIUS);
    }
    public Hitbox getMoleculeHitbox(){
        return new CircularHitbox(Configuration.getInstance().getUnitL() * GameConstants.MOLECULE_RADIUS);
    }
    public Hitbox getPowerUpHitbox(){
        return new RectangularHitbox(Configuration.getInstance().getUnitL() * GameConstants.POWERUP_RADIUS * 2,
                Configuration.getInstance().getUnitL() * GameConstants.POWERUP_RADIUS * 2);
    }
}
