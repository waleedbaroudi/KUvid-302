package model.game_entities;

import model.game_entities.enums.BlockerType;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;
/**
 * Blocker: Handles the Blocker game object.
 */
public class Blocker extends AutonomousEntity {

    private double blockingRadius;
    private double explosionRadius;
    private BlockerType type;

    public Blocker(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, BlockerType type, double blockingRadius, double explosionRadius) {
        super(coordinates, hitbox, pathPattern, EntityType.BLOCKER);
        this.type = type;
        this.blockingRadius = blockingRadius;
        this.explosionRadius = explosionRadius;
    }

    public BlockerType getType() {
        return type;
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
    public String toString() {
        return "Blocker{" +
                "blockingRadius=" + blockingRadius +
                ", explosionRadius=" + explosionRadius +
                ", type=" + type +
                '}';
    }
}
