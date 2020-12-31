package model.game_running.states;

import model.game_running.RunningMode;
import org.apache.log4j.Logger;

public class RunningState implements GameState {
    private static Logger logger;
    private RunningMode runningMode;

    public RunningState(RunningMode runningMode) {
        logger = Logger.getLogger(RunningState.class.getName());
        this.runningMode = runningMode;
    }

    @Override
    public void saveGameSession() {
        logger.warn("Cannot save session while the game is running. pause the game first.");
    }

    @Override
    public void retrieveGameSession() {
        logger.warn("Cannot load session while the game is running. pause the game first.");
    }
}
