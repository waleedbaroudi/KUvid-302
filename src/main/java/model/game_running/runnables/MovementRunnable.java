package model.game_running.runnables;

import model.game_entities.Atom;
import model.game_entities.AutonomousEntity;
import model.game_running.GameConstants;
import model.game_running.runnables.GameRunnable;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * This runnable handles moving objets in the background
 */
public class MovementRunnable extends GameRunnable {

    ArrayList<AutonomousEntity> entities; // A reference to the list of autonomous game entities in the space.

    public MovementRunnable(ArrayList<AutonomousEntity> entities) {
        super();
        this.entities = entities;
    }

    @Override
    public void run() {
        running = true; //this will be set to false from somewhere else (when the game ends).
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                for (AutonomousEntity entity : entities)
                    entity.move(); //TODO: fix: ConcurrentModificationException because items are being added during the loop

                Thread.sleep(GameConstants.GAME_THREAD_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
