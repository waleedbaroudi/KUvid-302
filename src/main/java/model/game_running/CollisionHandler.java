package model.game_running;

import model.game_entities.*;

public class CollisionHandler implements CollisionVisitor {

    RunningMode controller;
    int score = 0;
    CollisionHandler(RunningMode controller) {
        this.controller = controller;
    }

    /**
     * this method calls the removeEntity method of the runningMode object to remove the entities from the game view
     * @param entity1
     * @param entity2
     */
    private void defaultCollision(AutonomousEntity entity1, AutonomousEntity entity2) {
        controller.removeEntity(entity1);
        controller.removeEntity(entity2);
    }

    // this is a temporary method to calculate to increment the score of the player.
    // it will be changed a little when we connect this class to the statistics information.
    private int incrementScore(int score){ return score++;}

    /**
     * this method handles the collision of atom entity with a molecule entity. it removes them from the game view.
     * also, it increment the score of the player by 1
     * @param atom
     * @param  molecule
     */
    @Override
    public void handleCollision(Atom atom, Molecule molecule) {
        defaultCollision(atom, molecule);
        incrementScore(score);
    }

    /**
     * this method handles the collision of an atom entity with a molecule entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove both entities from the game view.
     * @param atom
     * @param  blocker
     */
    @Override
    public void handleCollision(Atom atom, Blocker blocker) {
        defaultCollision(atom, blocker);
    }

    /**
     * this method handles the collision of powerup entity with a molecule entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove both entities from the game view.
     * @param powerup
     * @param  blocker
     */
    @Override
    public void handleCollision(Powerup powerup, Blocker blocker) {
        defaultCollision(powerup, blocker);
    }

    /**
     * this method handles the collision of powerup entity with a shooter entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove the powerup from the game view.
     * and the RunningMode object's method addEntity called to add the powerup object into the entity container.
     * @param powerup
     * @param  shooter
     */
    @Override
    public void handleCollision(Powerup powerup, Shooter shooter) {
        controller.addEntity(powerup);
        controller.removeEntity(powerup);
    }


    /**
     * this method handles the collision of molecule entity with a blocker entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove both entities from the game view.
     * @param molecule
     * @param  blocker
     */
    @Override
    public void handleCollision(Molecule molecule, Blocker blocker) {
        defaultCollision(molecule, blocker);
    }

    /**
     * this method handles the collision of blocker entity with a shooter entity.
     * @param blocker
     * @param  shooter
     */
    @Override
    public void handleCollision(Blocker blocker, Shooter shooter) {
        // decrease the health of the player.
        // check for close atom and molecules and destroy them.
    }


}
