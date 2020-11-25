package model.game_entities;

import model.game_entities.enums.AtomType;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.path_patterns.PathPattern;
import utils.Coordinates;
/**
 * Atom: Handles the Atom game object.
 */
public class Atom extends Projectile{

    private AtomType type;

    public Atom(Coordinates coordinates, Hitbox hitbox, PathPattern pathPattern, AtomType type) {
        super(coordinates, hitbox, pathPattern);
        this.type = type;
    }

    public AtomType getType() {
        return type;
    }

    public void setType(AtomType type) {
        this.type = type;
    }

    }
