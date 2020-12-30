package model.game_running.states;

import model.game_building.GameBundle;
import model.game_running.RunningMode;
import utils.IOHandler;

public class PausedState implements GameState {

    private final RunningMode runningMode;

    public PausedState(RunningMode runningMode) {
        this.runningMode = runningMode;
    }

    @Override
    public void saveGameSession() {
        GameBundle.Builder builder = new GameBundle.Builder();
        builder.setShooter(runningMode.getShooter());
        runningMode.getAutonomousEntities().forEach(entity -> entity.saveState(builder));

        GameBundle bundle = builder.build();
        IOHandler.writeToJSON(bundle, "session", "sessions");
    }

    @Override
    public void LoadGameSession() {
        // todo: load game here
    }
}
