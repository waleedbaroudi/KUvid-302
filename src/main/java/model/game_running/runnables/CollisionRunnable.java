package model.game_running.runnables;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_entities.Entity;
import model.game_running.CollisionVisitor;
import model.game_building.GameConstants;
import model.game_running.RunningMode;
import utils.Coordinates;
import utils.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * This runnable handles collisions between entities.
 */
public class CollisionRunnable extends GameRunnable {

    private RunningMode runningMode;
    private CollisionVisitor collisionHandler;
    private CountDownLatch latch;

    public CollisionRunnable(RunningMode runningMode, CollisionVisitor collisionHandler) {
        super();
        this.runningMode = runningMode;
        this.latch = new CountDownLatch(0);
        this.collisionHandler = collisionHandler;
    }


    @Override
    public void run() {
        running = true;
        Set<AutonomousEntity> toRemoveEntities = new HashSet<>();
        Configuration config = Configuration.getInstance();
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                for (AutonomousEntity sourceEntity : runningMode.getAutonomousEntities()) {
                    if (sourceEntity.isCollidedWith(runningMode.getShooter()))
                        sourceEntity.acceptCollision(collisionHandler, runningMode.getShooter());
                    for (AutonomousEntity targetEntity : runningMode.getAutonomousEntities()) {
                        if (sourceEntity == targetEntity) //don't collision check an entity with itself
                            continue;
                        if (sourceEntity.isCollidedWith(targetEntity)) {
                            /*TODO: THIS REMOVES ANY TWO COLLIDING OBJECTS.
                                handle collision of atoms with blockers and increasing score when
                                collecting molecules.
                             */
                            sourceEntity.acceptCollision(collisionHandler, targetEntity);
                        }
                    }
                    // check if the entity left the game view from the top or bottom boarder
                    if (sourceEntity.getCoordinates().getY() < 0 ||
                            sourceEntity.getCoordinates().getY() > config.getGamePanelDimensions().height) {
                        toRemoveEntities.add(sourceEntity);
                    }

                    ArrayList<Coordinates> coords = sourceEntity.getBoundaryPoints();
                    for (Coordinates coord : coords) {
                        if (coord.getX() > config.getGamePanelDimensions().width) {
                            sourceEntity.getPathPattern().reflect(
                                    new Vector(new Coordinates(1, 0)));
                            sourceEntity.move();
                            GameRunnable.logger.debug("[CollisionRunnable] entity collided with the left boarder");
                        }
                        if (coord.getX() < 0) {
                            sourceEntity.getPathPattern().reflect(
                                    new Vector(new Coordinates(-1, 0)));
                            sourceEntity.move();
                            GameRunnable.logger.debug("[CollisionRunnable] entity collided with the right boarder");
                        }
                    }
                }
                runningMode.removeAutonomousEntities(toRemoveEntities);
                // TODO make the collision delay more than the movement delay
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
