package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import services.utils.Coordinates;
/**
 * Projectile: a superclass for all the game objects that can be shot by the Shooter.
 */
public abstract class Projectile extends AutonomousEntity{
    public Projectile(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, EntityType type) {
        super(coordinates, hitbox, pathPattern, type);
    }
}
