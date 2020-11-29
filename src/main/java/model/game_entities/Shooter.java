package model.game_entities;

import model.game_entities.enums.AtomType;
import model.game_entities.enums.Direction;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.Hitbox;
import utils.Coordinates;
import utils.MathUtils;

public class Shooter extends Entity {
    private Projectile currentProjectile;
    private AtomType previousAtom;
    private final double DEFAULT_ANGLE = 10;
    private double angle = 0;
    private final double MOVEMENT = 2;

    public Shooter(Coordinates coordinates, Hitbox hitbox) {
        super(coordinates, hitbox);
        super.setSuperType(EntityType.SHOOTER);
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

    public Projectile getCurrentProjectile() {
        return currentProjectile;
    }

    public void setCurrentProjectile(Projectile currentProjectile) {
        this.currentProjectile = currentProjectile;
    }

    public AtomType getPreviousAtom() {
        return previousAtom;
    }

    public double getAngle() {
        return this.angle;
    }

    public void setPreviousAtom(AtomType previousAtom) {
        this.previousAtom = previousAtom;
    }

    public boolean rotate(Direction direction) {
        if (Math.abs(this.angle) >= 90)
            return false;
        if (direction == Direction.LEFT) {
            this.angle -= DEFAULT_ANGLE;
            setCoordinates(MathUtils.applyRotation(-DEFAULT_ANGLE, getCoordinates(), getCoordinates()));
            return true;
        }
        this.angle += DEFAULT_ANGLE;
        setCoordinates(MathUtils.applyRotation(DEFAULT_ANGLE, getCoordinates(), getCoordinates()));
        return true;
    }

    public boolean move(Direction direction) {
        if (direction == Direction.LEFT) {
            getCoordinates().setX(getCoordinates().getX() - MOVEMENT);
            return true;
        } else if (direction == Direction.RIGHT) {
            getCoordinates().setX(getCoordinates().getX() + MOVEMENT);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Shooter{" +
                "coordinate=" + getCoordinates() +
                ", hitbox=" + getHitbox() +
                ", currentProjectile=" + currentProjectile +
                ", previousAtom=" + previousAtom +
                '}';
    }
}
