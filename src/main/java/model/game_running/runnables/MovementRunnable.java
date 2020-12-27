package model.game_running.runnables;

import model.game_building.GameConstants;
import model.game_entities.AutonomousEntity;
import model.game_running.Spinnable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * This runnable handles moving objets in the background
 */
public class MovementRunnable extends GameRunnable {

    CopyOnWriteArrayList<AutonomousEntity> entities; // A reference to the list of autonomous game entities in the space.
    final private HashSet<Spinnable> spinnables;

    public MovementRunnable(CopyOnWriteArrayList<AutonomousEntity> entities) {
        super();
        this.entities = entities;
        this.spinnables = new HashSet<>();
    }

    @Override
    public void run() {
        running = true; //this will be set to false from somewhere else (when the game ends).
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                // move all entities in the space
                for (AutonomousEntity entity : entities)
                    entity.move(); //TODO: fix: ConcurrentModificationException because items are being added during the loop

                // spin all spinnables in the space
                for (Spinnable entity: spinnables){
                    entity.spin();
                }

                // unregister all spinnables that left the game view
                spinnables.removeIf(c -> !entities.contains(c));

                Thread.sleep(GameConstants.GAME_THREAD_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** register entity to spin while moving in the space
     *
     * @param entity a Spinnable entity
     */
    public void registerSpinnable(Spinnable entity){
        this.spinnables.add(entity);
    }
}
