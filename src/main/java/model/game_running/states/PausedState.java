package model.game_running.states;

import model.game_building.Configuration;
import model.game_building.GameBundle;
import model.game_building.GameConstants;
import model.game_running.RunningMode;
import org.apache.log4j.Logger;
import utils.IOHandler;
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
    public void showSavedSessions() {
        runningMode.getSessionLoadListener().getSavedSessions();
    }

    @Override
    public void saveGameSession() {
        runningMode.saveGameSession();
    }
}
