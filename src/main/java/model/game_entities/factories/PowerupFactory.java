package model.game_entities.factories;


import model.game_entities.Powerup;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPatternFactory;
import utils.Coordinates;

public class PowerupFactory {

    private static PowerupFactory powerupFactory = new PowerupFactory();

    private PowerupFactory(){
    }

    public static PowerupFactory getInstance(){
        if(powerupFactory == null){
            powerupFactory = new PowerupFactory();
        }
        return powerupFactory;
    }

    public Powerup getPowerup(int i){// TODO : modify implementation.
        Coordinates defaultCoordinates = new Coordinates(0,0);

        switch (i){
            case 0:
                return new Powerup(defaultCoordinates, HitboxFactory.getInstance().getPowerUpHitbox(),
                        PathPatternFactory.getInstance().getPowerUpPathPattern(), EntityType.ALPHA);
            case 1:
                return new Powerup(defaultCoordinates, HitboxFactory.getInstance().getPowerUpHitbox(),
                        PathPatternFactory.getInstance().getPowerUpPathPattern(), EntityType.BETA);
            case 2:
                return new Powerup(defaultCoordinates, HitboxFactory.getInstance().getPowerUpHitbox(),
                        PathPatternFactory.getInstance().getPowerUpPathPattern(), EntityType.GAMMA);
            case 3:
                return new Powerup(defaultCoordinates, HitboxFactory.getInstance().getPowerUpHitbox(),
                        PathPatternFactory.getInstance().getPowerUpPathPattern(), EntityType.SIGMA);
            default:
                return null;
        }
    }
}


