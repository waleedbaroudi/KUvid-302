package model.game_entities;

import model.game_entities.enums.AtomType;
import model.game_entities.enums.Direction;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;
import utils.MathUtils;

public class Shooter {
    private Coordinates coordinates;
    private Hitbox hitbox;
    private Projectile currentProjectile;
    private AtomType previousAtom;
    private final double DEFAULT_ANGLE = 10;
    private double angle = 0;
    private final double MOVEMENT = 2;
    public Shooter(Coordinates coordinates, Hitbox hitbox) {
        this.coordinates = coordinates;
        this.hitbox = hitbox;
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
        return this.coordinates;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinates = coordinate;
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

    public double getAngle(){
        return this.angle;
    }

    public void setPreviousAtom(AtomType previousAtom) {
        this.previousAtom = previousAtom;
    }

    public boolean rotate(Direction direction){
        if (Math.abs(this.angle) >= 90)
            return false;
        if(direction == Direction.LEFT){
            this.angle -= DEFAULT_ANGLE;
            this.coordinates = MathUtils.applyRotation(-DEFAULT_ANGLE, this.coordinates, this.coordinates);
        return true;
        }
        this.angle += DEFAULT_ANGLE;
        this.coordinates = MathUtils.applyRotation(DEFAULT_ANGLE, this.coordinates, this.coordinates);
        return true;
    }

    public boolean move(Direction direction){
        if (direction == Direction.LEFT){
        this.coordinates.setX(this.coordinates.getX() - MOVEMENT);
        return true;
        }
        else if (direction == Direction.RIGHT){
        this.coordinates.setX(this.coordinates.getX() + MOVEMENT);
        return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Shooter{" +
                "coordinate=" + coordinates +
                ", hitbox=" + hitbox +
                ", currentProjectile=" + currentProjectile +
                ", previousAtom=" + previousAtom +
                '}';
    }
}
