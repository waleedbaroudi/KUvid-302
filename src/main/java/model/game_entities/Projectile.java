package model.game_entities;
/**
 * Projectile: a superclass for all the game objects that can be shot by the Shooter.
 */
abstract public class Projectile extends AutonomousEntity{
    public Projectile(Coordinates coordinates, Hitbox hitbox, Path path) {
        super(coordinates, hitbox, path);
    }
}
