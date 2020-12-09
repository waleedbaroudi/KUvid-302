package model.game_running;

import model.game_entities.*;

public class CollisionHandler implements CollisionVisitor{

    RunningMode controller;
    CollisionHandler(RunningMode controller){
        this.controller = controller;
    }

    private void defaultCollision(AutonomousEntity entity1, AutonomousEntity entity2){
        controller.removeEntity(entity1);
        controller.removeEntity(entity2);
    }



    @Override
    public void handleCollision(Atom atom, Molecule molecule) {
        defaultCollision(atom, molecule);
    }

    @Override
    public void handleCollision(Atom atom, Blocker blocker) {
        defaultCollision(atom, blocker);
    }

    @Override
    public void handleCollision(Atom atom, Shooter shooter) {
        controller.removeEntity(atom);
    }



    @Override
    public void handleCollision(Powerup powerup, Blocker blocker) {
        controller.removeEntity(blocker);
    }

    @Override
    public void handleCollision(Powerup powerup, Shooter shooter) {
        controller.addEntity(powerup);
    }



    @Override
    public void handleCollision(Molecule molecule, Blocker blocker) {
    }

    @Override
    public void handleCollision(Molecule molecule, Shooter shooter) {
    }

    @Override
    public void handleCollision(Molecule molecule, Atom atom2) {
    }

    @Override
    public void handleCollision(Blocker blocker, Powerup powerup) {
    }

    @Override
    public void handleCollision(Blocker blocker, Molecule molecule) {
    }

    @Override
    public void handleCollision(Blocker blocker1, Blocker blocker2) {
    }

    @Override
    public void handleCollision(Blocker blocker, Shooter shooter) {
    }

    @Override
    public void handleCollision(Blocker blocker, Atom atom2) {
    }

    @Override
    public void handleCollision(Shooter shooter, Powerup powerup) {
    }

    @Override
    public void handleCollision(Shooter shooter, Molecule molecule) {
    }

    @Override
    public void handleCollision(Shooter shooter, Blocker blocker) {
    }

    @Override
    public void handleCollision(Shooter shooter1, Shooter shooter2) {
    }

    @Override
    public void handleCollision(Shooter shooter, Atom atom2) {
    }
}
