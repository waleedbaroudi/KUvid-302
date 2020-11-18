package model.game_entities;

import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;
/**
 * Projectile: a superclass for all the game objects that can be shot by the Shooter.
 */
abstract public class Projectile extends AutonomousEntity{
    public Projectile(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern) {
        super(coordinates, hitbox, pathPattern);
    }
}
