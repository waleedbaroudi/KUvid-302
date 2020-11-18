package model.game_entities;
/**
 * AutonomousEntity: a Superclass for all the game objects that move autonomously
 */
abstract public class AutonomousEntity {

    private Coordinates coordinates;
    private Hitbox hitbox;
    private Path path;

    public AutonomousEntity(Coordinates coordinates, Hitbox hitbox, Path path){
        this.coordinates = coordinates;
        this.hitbox = hitbox;
        this.path = path;
    }

    public void setCoordinates(Coordinates coordinate){
        this.coordinates = coordinates;
    }

    public void setHitbox(Hitbox hitbox){
        this.hitbox = hitbox;
    }

    public void setPath(Path path){
        this.path = path;
    }

    public Coordinates getCoordinate() {
        return this.coordinates;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public Path getPath() {
        return this.path;
    }
    /**
     * move: moves the corresponding object according to the path variable.
     */
    public abstract void move();

}
