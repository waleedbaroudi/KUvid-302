package model.game_entities;
/**
 *  Blocker: Handles the Blocker game object.
 */
public class Blocker extends AutonomousEntity{

    private double blockingRadius;
    private double explosionRadius;
    private BlockerType type;

    public Blocker(Coordinates coordinates, Hitbox hitbox, Path path, BlockerType type, double blockingRadius, double explosionRadius){
        super(coordinates, hitbox, path);
        this.type = type;
        this.blockingRadius = blockingRadius;
        this.explosionRadius = explosionRadius;
    }

    public double getBlockingRadius() {
        return blockingRadius;
    }

    public void setExplosionRadius(double explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    public void setBlockingRadius(double blockingRadius) {
        this.blockingRadius = blockingRadius;
    }

    public double getExplosionRadius() {
        return explosionRadius;
    }

    @Override
    public void move() {
        setCoordinates(this.getPath().move());
    }
}
