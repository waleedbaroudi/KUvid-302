package model.game_running.states;

import model.game_building.GameBundle;
import model.game_running.RunningMode;
import org.apache.log4j.Logger;
import utils.IOHandler;
import utils.database.MongoDBAdapter;

import java.io.IOException;

public class PausedState implements GameState {

    private final RunningMode runningMode;
    private MongoDBAdapter dbAdapter;
    private static Logger logger;

    public PausedState(RunningMode runningMode) {
        this.runningMode = runningMode;
        this.dbAdapter = new MongoDBAdapter();
        logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public void saveGameSession() {
        GameBundle.Builder builder = new GameBundle.Builder();
        builder.setShooter(runningMode.getShooter());
        runningMode.getAutonomousEntities().forEach(entity -> entity.saveState(builder));

        GameBundle bundle = builder.build();
        String fileName = IOHandler.formatFileNameWithDate("Session1",""); // TODO: Take name from user
        try {
            dbAdapter.save(fileName, fileName, bundle); // TODO: Fix unique ID issue
        } catch (IOException e) {
            logger.error("Could not save the game session", e);
        }
    }

    @Override
    public void retrieveGameSession() {
        GameBundle loadedBundle = null;
        try {
            loadedBundle = dbAdapter.load("filename", "unique_id", GameBundle.class);
        } catch (IOException e) {
            logger.error("Could not load the game session", e);
        }
        runningMode.loadGameSession(loadedBundle);
    }
}
