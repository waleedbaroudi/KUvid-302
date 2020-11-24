package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class RunningMode {
    Logger logger;
    ArrayList<AutonomousEntity> autonomousEntities;
    boolean initialized = false;
    MovementHandler movementHandler;
    Thread movementThread;

    public RunningMode() {
        autonomousEntities = new ArrayList<>();
        logger = Logger.getLogger(this.getClass().getName());
        initialize();
    }

    private void initialize() throws IllegalThreadStateException { //todo: catch this exception and skip the drawing loop
        //todo: fill autonomous entity list and containers
        movementHandler = new MovementHandler(this.autonomousEntities);
        movementThread = new Thread(this.movementHandler);
    }

    public void beginHandlers() {
        if (!initialized) {
            logger.error("Game is not yet initialized");
            return;
        }
            movementThread.start();
        //todo: start threads
    }

    public boolean addEntity(AutonomousEntity entity) {
        boolean added = this.autonomousEntities.add(entity);
        movementHandler.setEntities(this.autonomousEntities);
        return added;
    }

    public boolean removeEntity(AutonomousEntity entity) {
        boolean removed = this.autonomousEntities.remove(entity);
        movementHandler.setEntities(this.autonomousEntities);
        return removed;
    }
}
