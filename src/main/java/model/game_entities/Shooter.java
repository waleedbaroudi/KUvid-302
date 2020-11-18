package model.game_entities;

import model.game_entities.enums.AtomType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.Hitbox;
import utils.Coordinates;

public class Shooter {
    Coordinates coordinate;
    Hitbox hitbox;
    Projectile currentProjectile;
    AtomType previousAtom;


    public Shooter() {
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

}
