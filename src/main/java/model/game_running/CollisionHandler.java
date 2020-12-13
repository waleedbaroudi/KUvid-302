package model.game_running;

import model.game_entities.*;

public class CollisionHandler implements CollisionVisitor {

    RunningMode controller;
    int score = 0;
    CollisionHandler(RunningMode controller) {
        this.controller = controller;
    }

    private void defaultCollision(AutonomousEntity entity1, AutonomousEntity entity2) {
        controller.removeEntity(entity1);
        controller.removeEntity(entity2);
    }

    private int incrementScore(int score){ return score++;}


    @Override
    public void handleCollision(Atom atom, Molecule molecule) {
        defaultCollision(atom, molecule);
        incrementScore(score);
    }

    @Override
    public void handleCollision(Atom atom, Blocker blocker) {
        defaultCollision(atom, blocker);
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
    public void handleCollision(Blocker blocker, Shooter shooter) {
    }


}
