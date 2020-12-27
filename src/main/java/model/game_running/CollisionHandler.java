package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.*;
import utils.MathUtils;

public class CollisionHandler implements CollisionVisitor {

    RunningMode controller;

    CollisionHandler(RunningMode controller) {
        this.controller = controller;
    }

    /**
     * this method calls the removeEntity method of the runningMode object to remove the entities from the game view
     *
     * @param entity1
     * @param entity2
     */
    private void defaultCollision(AutonomousEntity entity1, AutonomousEntity entity2) {
        controller.removeEntity(entity1);
        controller.removeEntity(entity2);
    }


    /**
     * this method handles the collision of atom entity with a molecule entity. it removes them from the game view.
     * also, it increment the score of the player by 1
     *
     * @param atom
     * @param molecule
     */
    @Override
    public void handleCollision(Atom atom, Molecule molecule) {
        if (atom.getType().getValue() == molecule.getType().getValue()) {
            controller.increaseScore();
            defaultCollision(atom, molecule);
        }
    }

    /**
     * this method handles the collision of an atom entity with a molecule entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove both entities from the game view.
     *
     * @param atom
     * @param blocker
     */
    @Override
    public void handleCollision(Atom atom, Blocker blocker) {
        // this only breaks the atom if enters the AOE of a corresponding type blocker.

        if (blocker.isExploded()){
            controller.removeEntity(atom);
        }else{
             if (atom.getType().getValue() == blocker.getType().getValue())
              controller.removeEntity(atom);
        }
    }

    /**
     * this method handles the collision of powerup entity with a molecule entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove both entities from the game view.
     *
     * @param powerup
     * @param blocker
     */
    @Override
    public void handleCollision(Powerup powerup, Blocker blocker) {
        if (blocker.getType().getValue() == powerup.getType().getValue())
            defaultCollision(powerup, blocker);
    }

    /**
     * this method handles the collision of powerup entity with a shooter entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove the powerup from the game view.
     * and the RunningMode object's method addEntity called to add the powerup object into the entity container.
     *
     * @param powerup
     * @param shooter
     */
    @Override
    public void handleCollision(Shooter shooter, Powerup powerup) {
        controller.collectPowerUp(powerup);
        controller.removeEntity(powerup);
    }


    /**
     * this method handles the collision of molecule entity with a blocker entity.
     * it calls the defaultCollision method that call removeEntity method of the RunningMode object to remove both entities from the game view.
     *
     * @param molecule
     * @param blocker
     */
    @Override
    public void handleCollision(Molecule molecule, Blocker blocker) {
//        defaultCollision(molecule, blocker);
        //nothing for now. this collision will be conditional: only when the blocker is exploding.
        if (blocker.isExploded()){
            controller.removeEntity(molecule);
        }
    }

    /**
     * this method handles the collision of blocker entity with a shooter entity.
     *
     * @param blocker
     * @param shooter
     */
    @Override
    public void handleCollision(Shooter shooter, Blocker blocker) {
        // decrease the health of the player.
        // check for close atom and molecules and destroy them.
        double distance = MathUtils.distanceBetween(shooter.getCoordinates(), blocker.getCoordinates());

        if(distance <= GameConstants.BLOCKER_EXPLOSION_RADIUS * Configuration.getInstance().getUnitL()) {
            int damageDone = (int) Math.round(blocker.getExplosionDamage(shooter));
            controller.getStatistics().decreaseHealth(damageDone);
            
        }
    }
}
