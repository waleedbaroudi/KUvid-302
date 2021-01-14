package model.game_running;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.*;
import services.utils.MathUtils;

public class CollisionHandler implements CollisionVisitor {

    RunningMode controller;

    CollisionHandler(RunningMode controller) {
        this.controller = controller;
    }

    /**
     * this method calls the removeEntity method of the runningMode object to remove the entities from the game view
     *
     * @param entity1 first autonomousEntity
     * @param entity2 second autonomousEntity
     */
    private void defaultCollision(AutonomousEntity entity1, AutonomousEntity entity2) {
        controller.removeEntity(entity1);
        controller.removeEntity(entity2);
    }


    @Override
    public void handleCollision(Atom atom, Molecule molecule) {
        if (atom.getEntityType().getValue() == molecule.getEntityType().getValue()) {
            controller.increaseScore(atom.getEfficiency());
            defaultCollision(atom, molecule);
        }
    }

    @Override
    public void handleCollision(Atom atom, Blocker blocker) {
        // this only breaks the atom if enters the AOE of a corresponding type blocker.

        if (blocker.isExploded()){
            controller.removeEntity(atom);
        }else{
             if (atom.getEntityType().getValue() == blocker.getEntityType().getValue())
              controller.removeEntity(atom);
        }
    }

    @Override
    public void handleCollision(Powerup powerup, Blocker blocker) {
        if ((blocker.getEntityType().getValue() == powerup.getEntityType().getValue()) && !powerup.isFalling())
            defaultCollision(powerup, blocker);
    }

    @Override
    public void handleCollision(Shooter shooter, Powerup powerup) {
        if(powerup.isFalling()) {
            controller.collectPowerUp(powerup);
            controller.removeEntity(powerup);
        }
    }

    @Override
    public void handleCollision(Molecule molecule, Blocker blocker) {
//        defaultCollision(molecule, blocker);
        //nothing for now. this collision will be conditional: only when the blocker is exploding.
        if (blocker.isExploded()){
            controller.removeEntity(molecule);
        }
    }

    @Override
    public void handleCollision(Shooter shooter, Blocker blocker) {
        // decrease the health of the player.
        // check for close atom and molecules and destroy them.
        double distance = MathUtils.distanceBetween(shooter.getCoordinates(), blocker.getCoordinates());
        double damageDone;
        if (blocker.isExploded()) {
            damageDone = blocker.getExplosionDamage(shooter);
            System.out.println("Damage done: " + damageDone);
            controller.updateHealth(damageDone);
            controller.removeEntity(blocker);
        }

        if (distance <= GameConstants.BLOCKER_RADIUS * Configuration.getInstance().getUnitL()
                && !blocker.isExploded()) {
            //TODO: Make blocker explode when it hits the shooter.
            damageDone = blocker.getExplosionDamage(shooter);
            controller.updateHealth(damageDone);
            controller.removeEntity(blocker);
        }
    }
}
