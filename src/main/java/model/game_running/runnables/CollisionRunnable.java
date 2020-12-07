package model.game_running.runnables;

import model.game_entities.AutonomousEntity;
import model.game_running.CollisionVisitor;
import model.game_running.GameConstants;
import model.game_running.RunningMode;

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
        Set<AutonomousEntity> collidedEntities = new HashSet<>();
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                for (AutonomousEntity sourceEntity : runningMode.getAutonomousEntities()) {
                    for (AutonomousEntity targetEntity : runningMode.getAutonomousEntities()) {
                        if (sourceEntity == targetEntity) //don't collision check an entity with itself
                            continue;
                        if (sourceEntity.isCollidedWith(targetEntity)) {
                            /*TODO: THIS REMOVES ANY TWO COLLIDING OBJECTS.
                                handle collision of atoms with blockers and increasing score when
                                collecting molecules.
                             */
                            sourceEntity.acceptCollision(collisionHandler, targetEntity);
                            System.out.println("COLLIDED");
                        }
                    }
                }
                Thread.sleep(GameConstants.GAME_THREAD_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
