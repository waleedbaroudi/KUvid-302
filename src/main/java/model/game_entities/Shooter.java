package model.game_entities;

import model.game_entities.enums.AtomType;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;

public class Shooter extends AutonomousEntity{
    Coordinates coordinate;
    Hitbox hitbox;
    Projectile currentProjectile;
    AtomType previousAtom;

    public Shooter(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern) {
        super(coordinates, hitbox, pathPattern, EntityType.SHOOTER); // TODO: THIS IS NOT AUTONOMOUS ENTITY
    }


    public Projectile shoot() {
        return null;
    }

    public boolean switchAtom() {
        return false;
    }


    public void mountPowerup(PowerupType powerupType) {

    }

    public boolean reload() {
        return false;
    }

    public Atom nextAtom() {
        return null;
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public Projectile getCurrentProjectile() {
        return currentProjectile;
    }

    public void setCurrentProjectile(Projectile currentProjectile) {
        this.currentProjectile = currentProjectile;
    }

    public AtomType getPreviousAtom() {
        return previousAtom;
    }

    public void setPreviousAtom(AtomType previousAtom) {
        this.previousAtom = previousAtom;
    }

    @Override
    public String toString() {
        return "Shooter{" +
                "coordinate=" + coordinate +
                ", hitbox=" + hitbox +
                ", currentProjectile=" + currentProjectile +
                ", previousAtom=" + previousAtom +
                '}';
    }
}
