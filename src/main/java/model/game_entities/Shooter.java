package model.game_entities;

import model.game_building.Configuration;
import model.game_entities.enums.AtomType;
import model.game_entities.enums.Direction;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.CircularHitbox;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.hitbox.RectangularHitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_physics.path_patterns.PathPatternFactory;
import model.game_physics.path_patterns.StraightPattern;
import model.game_physics.path_patterns.ZigzagPatten;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.MathUtils;
import utils.Velocity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Shooter extends Entity {
    private Projectile currentProjectile;
    private AtomType previousAtom;
    private final double DEFAULT_ANGLE = 10;
    private double angle = 0;
    private final double MOVEMENT = 15;
    public static Logger logger = Logger.getLogger(Shooter.class.getName());

    public Shooter(Coordinates coordinates, Hitbox hitbox) {
        super(coordinates, hitbox);
        super.setSuperType(EntityType.SHOOTER);

        // Turn off logger
        logger.setLevel(Level.OFF);
    }


    public Projectile shoot() {
        this.reload();
        Projectile tmpProjectile = this.getCurrentProjectile();
        // set the coordinates of the projectile the same as the coordinates of hte shooter
        tmpProjectile.setCoordinates(this.getCoordinates());
        return tmpProjectile;
    }

    public boolean switchAtom() {
        this.setCurrentProjectile(this.nextAtom());
        return true;
    }

    public void mountPowerup(PowerupType powerupType) {

    }

    public boolean reload() {
        this.setCurrentProjectile(this.nextAtom());
        return true;
    }

    public Atom nextAtom() {
        // TODO: change the atom types to random
        return new Atom(this.getCoordinates(), HitboxFactory.getInstance().getAtomHitbox(), PathPatternFactory.getInstance().getAtomPathPattern(), AtomType.GAMMA);
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

    public boolean rotate(int direction) {
        if(!checkLegalMovement(this.getCoordinates(), this.getAngle() + DEFAULT_ANGLE * direction))
            return false;
        this.angle += DEFAULT_ANGLE * direction;
        this.getHitbox().rotate( DEFAULT_ANGLE * direction);
        return true;
    }

    public boolean move(int direction) {
        Coordinates newCoords = new Coordinates(getCoordinates().getX() + direction *  MOVEMENT, getCoordinates().getY());
        if(!checkLegalMovement(newCoords, this.getAngle())) {
            logger.info("[Shooter] shooter cannot move to the new coordinates" + this.getCoordinates());
            return false;
        }
        this.setCoordinates(newCoords);
        logger.info("[Shooter] shooter moved to a new coordinates" + this.getCoordinates());
        return true;
    }

    public boolean checkLegalMovement(Coordinates c, double angle){
        if(c.getX() > Configuration.getInstance().getGameWidth())
            return false;
        else if(c.getX() < 0)
            return false;
        return true;
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
