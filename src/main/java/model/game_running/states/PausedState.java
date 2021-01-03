package model.game_running.states;

import model.game_building.Configuration;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_running.RunningMode;
import org.apache.log4j.Logger;
import services.utils.IOHandler;
import utils.database.MongoDBAdapter;

import java.io.IOException;

public class PausedState implements GameState {

    private final RunningMode runningMode;
    private final MongoDBAdapter dbAdapter;
    private static Logger logger;

    public PausedState(RunningMode runningMode) {
        this.runningMode = runningMode;
        this.dbAdapter = MongoDBAdapter.getInstance();
        logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public void saveGameSession() {
        GameBundle.Builder builder = new GameBundle.Builder();
        builder.setPlayer(runningMode.getPlayer()).
                setShooter(runningMode.getShooter()).
                setProjectileContainer(runningMode.getProjectileContainer()).
                setConfig(Configuration.getInstance());

        runningMode.getAutonomousEntities().forEach(entity -> entity.saveState(builder));

        GameBundle bundle = builder.build();
        String fileName = IOHandler.formatFileNameWithDate("Session1", ""); // TODO: Take name from user
        try {
            dbAdapter.save(GameConstants.SESSION_COLLECTION_TITLE, fileName, bundle); //
        } catch (IOException e) {
            logger.error("Could not save the game session", e);
        }
    }

    @Override
    public void showSavedSessions() {
        runningMode.getSessionLoadListener().getSavedSessions();
    }
}
