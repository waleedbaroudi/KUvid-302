package model.game_running.states;

import model.game_building.GameBundle;
import model.game_entities.AutonomousEntity;
import model.game_running.RunningMode;

public class PausedState implements GameState {

    private RunningMode runningMode;

    public PausedState(RunningMode runningMode) {
        this.runningMode = runningMode;
    }

    @Override
    public void SaveGameSession() {
        GameBundle.Builder builder = new GameBundle.Builder();
        
    }

    @Override
    public void LoadGameSession() {
        // todo: load game here
    }
}
