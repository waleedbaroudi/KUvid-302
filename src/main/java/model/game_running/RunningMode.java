package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class RunningMode {
    Logger logger;
    private static ArrayList<AutonomousEntity> autonomousEntities;
    boolean isInitialized = false;
    MovementRunnable movementRunnable;
    Thread movementThread;

    public RunningMode() {
        autonomousEntities = new ArrayList<>();
        logger = Logger.getLogger(this.getClass().getName());
        initialize();
    }

    private void initialize() throws IllegalThreadStateException { //todo: catch this exception and skip the drawing loop
        //todo: fill autonomous entity list and containers here
        movementRunnable = new MovementRunnable();
        movementThread = new Thread(this.movementRunnable);
        this.isInitialized = true;
    }

    public void startThreads() {
        if (!isInitialized) {
            logger.error("Game is not yet initialized");
            return;
        }
        movementThread.start();
        //todo: start threads
    }

    public boolean addEntity(AutonomousEntity entity) {
        return autonomousEntities.add(entity);
    }

    public boolean removeEntity(AutonomousEntity entity) {
        return autonomousEntities.remove(entity);
    }

    public void stop() {
        this.pause();
        movementThread.interrupt();
    }

    public void pause() {
        movementRunnable.pause();
    }

    public static ArrayList<AutonomousEntity> getAutonomousEntities() {
        return autonomousEntities;
    }
}
