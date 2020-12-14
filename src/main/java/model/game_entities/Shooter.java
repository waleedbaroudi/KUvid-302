package model.game_entities;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.StraightPattern;
import model.game_running.CollisionVisitor;
import model.game_running.ProjectileContainer;
import utils.Coordinates;
import utils.MathUtils;
import utils.Velocity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Shooter extends Entity {
    private Projectile currentProjectile;
    private ProjectileContainer container;

    Configuration config;

    private EntityType previousAtom;
    private final double DEFAULT_ANGLE = 10;
    private double angle = 0;
    private final double MOVEMENT;
    public static Logger logger = Logger.getLogger(Shooter.class.getName());

    public Shooter(Coordinates coordinates, Hitbox hitbox, ProjectileContainer container) {
        super(coordinates, hitbox);
        config = Configuration.getInstance();

        MOVEMENT = config.getShooterSpeed();
        this.superType = SuperType.SHOOTER;

        this.container = container;
        // Turn off logger
        logger.setLevel(Level.OFF);
        reload();
    }

    public Projectile shoot() {
        if (getCurrentProjectile() == null) //get atom from the container returned null. (no more of the selected type)
            return null;

        //TODO: currently we are building the atom on shooting, we need to change that (Maybe)
        Projectile tmpProjectile = this.getCurrentProjectile();
        // rotate the path direction according to the shooter
        Coordinates rotatedCoords = new Coordinates(this.getCoordinates().getX(),
                this.getCoordinates().getY() - config.getUnitL() * GameConstants.SHOOTER_HEIGHT);
        rotatedCoords = MathUtils.applyRotation(angle, this.getCoordinates(), rotatedCoords);
        rotatedCoords = new Coordinates(rotatedCoords.getX() - this.getCoordinates().getX(), rotatedCoords.getY() - this.getCoordinates().getY());
        // set the coordinates of the projectile the same as the coordinates of hte shooter
        double len = Math.sqrt(rotatedCoords.getX() * rotatedCoords.getX() + rotatedCoords.getY() * rotatedCoords.getY());
        tmpProjectile.setPathPattern(new StraightPattern(new Velocity(10 * rotatedCoords.getX() / len, 10 * rotatedCoords.getY() / len)));
        tmpProjectile.setCoordinates(getShootingCoords(getCoordinates(), tmpProjectile));
        this.reload();
        return tmpProjectile;
    }


    //todo: see if we can make these constants as attributes for the shooter
    /**
     *
     * @param coordinates the coordinates of the shotoer
     * @param projectile the projectile that is on the tip of the shooter
     * @return the coordinate of the projectile where it will start moving
     */
    private Coordinates getShootingCoords(Coordinates coordinates, Projectile projectile) {
        int height = (int) (Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_HEIGHT);
        int width = (int) (Configuration.getInstance().getUnitL() * GameConstants.SHOOTER_WIDTH);
        int atomRadius = (int) (Configuration.getInstance().getUnitL() * GameConstants.ATOM_RADIUS);
        int powerupRadius = (int) (Configuration.getInstance().getUnitL() * GameConstants.POWERUP_RADIUS);

        int r = projectile.superType == SuperType.ATOM ? atomRadius : powerupRadius;
        double theta = Math.toRadians(90 - Math.abs(getAngle()));
        int h = (int) ((r + height/2) * Math.sin(theta));
        int w = (int) ((r + width) * Math.cos(theta));
        return new Coordinates(coordinates.getX() + (getAngle() < 0 ? -1*w : w), coordinates.getY() - h);
    }

    public boolean switchAtom() {
        this.setCurrentProjectile(this.nextAtom());
        return true;
    }

    public void mountPowerup(EntityType EntityType) { //todo: what's this for?

    }

    public boolean reload() {
        this.setCurrentProjectile(this.nextAtom());
        return true;
    }

    public Atom nextAtom() {
        // TODO: change the atom types to random
        return container.getRandomAtom(this.getCoordinates());
//        return new Atom(this.getCoordinates(), HitboxFactory.getInstance().getAtomHitbox(), PathPatternFactory.getInstance().getAtomPathPattern(), EntityType.BETA);
    }

    public Projectile getCurrentProjectile() {

        return currentProjectile;
    }

    public void setCurrentProjectile(Projectile currentProjectile) {
        this.currentProjectile = currentProjectile;
    }

    public EntityType getPreviousAtom() {
        return previousAtom;
    } // todo: what dis?

    public double getAngle() {
        return this.angle;
    }

    public void setPreviousAtom(EntityType previousAtom) {
        this.previousAtom = previousAtom;
    } // todo: what dis too?

    public boolean rotate(int direction) {
        if (!checkLegalMovement(this.getCoordinates(), this.getAngle() + DEFAULT_ANGLE * direction))
            return false;
        this.angle += DEFAULT_ANGLE * direction;
        this.getHitbox().rotate(DEFAULT_ANGLE * direction);
        return true;
    }

    public boolean move(int direction) {
        Coordinates newCoords = new Coordinates(getCoordinates().getX() + direction * MOVEMENT, getCoordinates().getY());
        if (!checkLegalMovement(newCoords, this.getAngle())) {
            logger.info("[Shooter] shooter cannot move to the new coordinates" + this.getCoordinates());
            return false;
        }
        this.setCoordinates(newCoords);
        logger.info("[Shooter] shooter moved to a new coordinates" + this.getCoordinates());
        return true;
    }

    public boolean checkLegalMovement(Coordinates c, double angle) {
        double gunWidth = config.getUnitL() * GameConstants.SHOOTER_WIDTH;
        if (c.getX() + gunWidth / 2 > config.getGamePanelDimensions().getWidth())
            return false;
        else if (c.getX() - gunWidth / 2 < 0)
            return false;
        return !(angle > 90) && !(angle < -90);
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

    // visitor pattern. Double delegation
    @Override
    public void collideWith(CollisionVisitor visitor, Atom atom) {
        visitor.handleCollision(this, atom);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Blocker blocker) {
        visitor.handleCollision(this, blocker);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Molecule molecule) {
        visitor.handleCollision(this, molecule);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Powerup powerup) {
        visitor.handleCollision(this, powerup);
    }

    @Override
    public void collideWith(CollisionVisitor visitor, Shooter shooter) {
        visitor.handleCollision(this, shooter);
    }

    @Override
    public void acceptCollision(CollisionVisitor visitor, Entity entity) {
        entity.collideWith(visitor, this);
    }
}
