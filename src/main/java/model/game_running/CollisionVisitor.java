package model.game_running;

import model.game_entities.*;
public interface CollisionVisitor {



    /** this methods is supposed to handle the collision of atom entity with powerup entity.
     * since no action is done in this type of collision, the method is default.
     * @param atom
     * @param powerup
     */
    default void handleCollision(Atom atom, Powerup powerup){};
    /** this method is to handle atom collision with a molecule entity.
     * it will be overridden in collisionHandler class from the side of atom entity.
     * @param atom
     * @param  molecule
     */
    void handleCollision(Atom atom, Molecule molecule);
    /** this method is to handle atom collision with a blocker entity.
     * it will be overridden in collisionHandler class from the side of atom entity.
     * @param atom
     * @param  blocker
     */
    void handleCollision(Atom atom, Blocker blocker);
    /**this methods is supposed to handle the collision of atom entity with a shooter entity.
     * since no action is done in this type of collision, the method is default.
     * @param atom
     * @param  molecule
     */
    default void handleCollision(Atom atom, Shooter shooter){};
    /**this methods is supposed to handle the collision of atom entity with another atom entity.
     * since no action is done in this type of collision, the method is default.
     * @param atom
     * @param  molecule
     */
    default void handleCollision(Atom atom1, Atom atom2){};

    // Powerup collision

    /**
     * this methods is supposed to handle the collision of powerup entity with another a powerup entity.
     * since no action is done in this type of collision, the method is default.
     * @param powerup1
     * @param powerup2
     */
    default void handleCollision(Powerup powerup1, Powerup powerup2){};

    /** this methods is supposed to handle the collision of powerup entity with a molecule entity.
     *  since no action is done in this type of collision, the method is default.
     * @param powerup
     * @param molecule
     */
    default void handleCollision(Powerup powerup, Molecule molecule){};

    /**this methods is to handle the collision of powerup entity with a blocker entity.
     * it will be overridden in collisionHandler class from the side of powerup entity.
     * @param powerup
     * @param blocker
     */
    void handleCollision(Powerup powerup, Blocker blocker);

    /**this methods is to handle the collision of powerup entity with a shooter entity.
     * it will be overridden in collisionHandler class from the side of powerup entity.
     * @param powerup
     * @param shooter
     */
    void handleCollision(Powerup powerup, Shooter shooter);

    /** this methods is supposed to handle the collision of powerup entity with an atom entity.
     *  since no action is done in this type of collision, the method is default.
     * @param powerup
     * @param atom
     */
    default void handleCollision(Powerup powerup, Atom atom){};


    // Molecule collision

    /** this methods is supposed to handle the collision of molecule entity with a powerup entity.
     *  since no action is done in this type of collision, the method is default.
     * @param molecule
     * @param powerup
     */
    default void handleCollision(Molecule molecule, Powerup powerup){};

    /** this methods is supposed to handle the collision of molecule entity with a molecule entity.
     *  since no action is done in this type of collision, the method is default.
     * @param molecule1
     * @param molecule2
     */
    default void handleCollision(Molecule molecule1, Molecule molecule2){};

    /** this methods is to handle the collision of molecule entity with a blocker entity.
     *  it will be overridden in collisionHandler class from the side of molecule entity.
     * @param molecule
     * @param blocker
     */
    void handleCollision(Molecule molecule, Blocker blocker);

    /** this methods is supposed to handle the collision of molecule entity with a shooter entity.
     *  since no action is done in this type of collision, the method is default.
     * @param molecule
     * @param shooter
     */
    default void handleCollision(Molecule molecule, Shooter shooter){};

    /**this methods is supposed to handle the collision of molecule entity with an atom entity.
     *  since no action is done in this type of collision, the method is default.
     * @param molecule
     * @param atom
     */
    default void handleCollision(Molecule molecule, Atom atom){};


    // Blocker collision

    /** this methods is supposed to handle the collision of blocker entity with a powerup entity.
     *  since the collision action of blocker with powerup is already implemented from the powerup side,
     *  the method is default.
     * @param blocker
     * @param powerup
     */
    default void handleCollision(Blocker blocker, Powerup powerup){};

    /**this methods is supposed to handle the collision of blocker entity with a powerup entity.
     *  since the collision action of blocker with molecule is already implemented from the molecule side,
     *  the method is default.
     * @param blocker
     * @param molecule
     */
    default void handleCollision(Blocker blocker, Molecule molecule){};

    /**this methods is supposed to handle the collision of blocker entity with a blocker entity.
     *  since no action is done in this type of collision, the method is default.
     * @param blocker1
     * @param blocker2
     */
    default void handleCollision(Blocker blocker1, Blocker blocker2){};

    /**this methods is to handle the collision of blocker entity with a shooter entity.
     * it will be overridden in collisionHandler class from the side of blocker entity.
     * @param blocker
     * @param shooter
     */
    void handleCollision(Blocker blocker, Shooter shooter);

    /** this methods is supposed to handle the collision of blocker entity with an atom entity.
     *  since the collision action of blocker with an atom is already implemented from the atom side,
     *  the method is default.
     * @param blocker
     * @param atom2
     */
    default void handleCollision(Blocker blocker, Atom atom2){};


    // Shooter collision
    /* the methods of shooter collision with any entity has no action.
     * there is either no action done when a shooter collide with an entity, or the collision action
     * is already implemented from the side of the other entity
     *
     */
    default void handleCollision(Shooter shooter, Powerup powerup){};
    default void handleCollision(Shooter shooter, Molecule molecule){};
    default void handleCollision(Shooter shooter, Blocker blocker){};
    default void handleCollision(Shooter shooter1, Shooter shooter2){};
    default void handleCollision(Shooter shooter, Atom atom2){};


}
