package model.game_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.ShieldType;
import model.game_entities.enums.SuperType;
import model.game_entities.factories.NeutronFactory;
import model.game_entities.factories.ProtonFactory;
import model.game_entities.shields.ShieldTuple;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_running.CollisionVisitor;
import services.utils.Coordinates;
/**
 * Atom: Handles the Atom game object.
 */
@JsonTypeName("atom")
public class Atom extends Projectile {

    private final double stabilityConstant;
    private final double efficiency;
    private final int numberOfProtons;
    private final int numberOfNeutrons;
    private ShieldTuple shieldTuple;

    private final double ATOM_SPEED_PERCENTAGE = 1;

    public Atom(@JsonProperty("coordinates")Coordinates coordinates,
                @JsonProperty("hitbox")Hitbox hitbox,
                @JsonProperty("pathPattern")PathPattern pathPattern,
                @JsonProperty("entityType")EntityType type,
                @JsonProperty("stabilityConstant") Double stabilityConstant,
                @JsonProperty("efficiency") Double efficiency,
                @JsonProperty("stabilityConstant") Integer numberOfProtons,
                @JsonProperty("stabilityConstant") Integer numberOfNeutrons
                ) {
        super(coordinates, hitbox, pathPattern, type);
        superType = SuperType.ATOM;

        this.stabilityConstant = stabilityConstant;
        this.efficiency = efficiency;

        this.numberOfProtons = numberOfProtons;
        this.numberOfNeutrons = numberOfNeutrons;

        this.shieldTuple = new ShieldTuple();
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

    @Override
    public void saveState(GameBundle.Builder builder) {
        builder.addEntity(this);
    }

    @Override
    public String toString() {
        return "Atom{" +
                "type=" + getEntityType() +
                '}';}

    public void addShield(ShieldType type) {
        shieldTuple.addShield(type);
    }

    public void setShieldTuple(ShieldTuple shieldTuple) {
        this.shieldTuple = shieldTuple;
    }

    public ShieldTuple getShieldTuple() {
        return this.shieldTuple;
    }

    public double getEfficiency() {
        return this.efficiency;
    }

    public double getStabilityConstant() {
        return this.stabilityConstant;
    }

    public int getNumberOfNeutrons() {
        return this.numberOfNeutrons;
    }

    public int getNumberOfProtons() {
        return this.numberOfProtons;
    }

    public double getAtomSpeedPercentage() {
        return ATOM_SPEED_PERCENTAGE;
    }

    @Override
    public double getSpeedPercentage() {
        //MODIFIES:
        //EFFECTS: Since this is an unshilded atom, the percentage of the speed is 100%
        return GameConstants.DEFAULT_ATOM_SPEED_PERCENTAGE;
    }
}
