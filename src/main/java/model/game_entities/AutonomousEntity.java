package model.game_entities;

import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;
/**
 * AutonomousEntity: a Superclass for all the game objects that move autonomously
 */
abstract public class AutonomousEntity {

    private Coordinates coordinates;
    private Hitbox hitbox;
    private PathPattern pathPattern;
    private EntityType superType;

    public AutonomousEntity(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, EntityType superType){
        this.coordinates = coordinates;
        this.hitbox = hitbox;
        this.pathPattern = pathPattern;
        this.superType = superType;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public void setHitbox(Hitbox hitbox){
        this.hitbox = hitbox;
    }

    public void setPathPattern(PathPattern path){
        this.pathPattern = pathPattern;
    }

    public Coordinates getCoordinate() {
        return this.coordinates;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public PathPattern getPathPattern() {
        return this.pathPattern;
    }
    /**
     * move: moves the corresponding object according to the path variable.
     */
    public void move(){
        setCoordinates(this.getPathPattern().nextPosition());
    };

    public EntityType getSuperType() {
        return superType;
    }

    public boolean isCollidedWith(AutonomousEntity entity){
        return this.getHitbox().isInside(this.getCoordinate(), entity.getHitbox().getBoundaryCoordinates());
    }
}
