package model.game_running;

import model.game_entities.*;
public interface CollisionVisitor {

    // Atom collision
    default void handleCollision(Atom atom, Powerup powerup){};
    void handleCollision(Atom atom, Molecule molecule);
    void handleCollision(Atom atom, Blocker blocker);
    default void handleCollision(Atom atom, Shooter shooter){};
    default void handleCollision(Atom atom1, Atom atom2){};

    // Powerup collision
    default void handleCollision(Powerup powerup1, Powerup powerup2){};
    default void handleCollision(Powerup powerup, Molecule molecule){};
    void handleCollision(Powerup powerup, Blocker blocker);
    void handleCollision(Powerup powerup, Shooter shooter);
    default void handleCollision(Powerup powerup, Atom atom2){};


    // Molecule collision
    default void handleCollision(Molecule molecule, Powerup powerup){};
    default void handleCollision(Molecule molecule1, Molecule molecule2){};
    void handleCollision(Molecule molecule, Blocker blocker);
    default void handleCollision(Molecule molecule, Shooter shooter){};
    void handleCollision(Molecule molecule, Atom atom2);


    // Blocker collision
    void handleCollision(Blocker blocker, Powerup powerup);
    void handleCollision(Blocker blocker, Molecule molecule);
    default void handleCollision(Blocker blocker1, Blocker blocker2){};
    void handleCollision(Blocker blocker, Shooter shooter);
    void handleCollision(Blocker blocker, Atom atom2);


    // Shooter collision
    void handleCollision(Shooter shooter, Powerup powerup);
    default void handleCollision(Shooter shooter, Molecule molecule){};
    void handleCollision(Shooter shooter, Blocker blocker);
    default void handleCollision(Shooter shooter1, Shooter shooter2){};
    default void handleCollision(Shooter shooter, Atom atom2){};


}
