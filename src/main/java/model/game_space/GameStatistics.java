package model.game_space;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;

public class GameStatistics {
    GameStatisticsListener statisticsListener;

    public GameStatistics(GameStatisticsListener statisticsListener) {
        this.statisticsListener = statisticsListener;
    }

    public GameStatisticsListener getStatisticsListener() {
        return statisticsListener;
    }

    public void setStatisticsListener(GameStatisticsListener listener) {
        this.statisticsListener = listener;
    }

    /**
     * updates the panel with the new health
     *
     * @param health the value of the player's health after the update
     */
    public void updateHealth(int health) {
        statisticsListener.onHealthChanged(health);
    }

    /**
     * update the panel with the new timer value.
     *
     * @param currentTime the current time to be displayed in the statistics panel
     */
    public void updateTimer(String currentTime) {
        statisticsListener.onTimerChanged(currentTime);
    }


    public void updateScore(int score) {
        statisticsListener.onScoreChanged(score);
    }

    /**
     * called when the number of a any projectile is changed. (by shooting, blending, catching, etc.)
     *
     * @param atoms     the count array of atom types
     * @param powerUps  the count array of power-up types
     */
    public void changeProjectileCount(int[] atoms, int[] powerUps) {// todo: this is called from projectile container via the controller
        statisticsListener.onProjectileCountChange(atoms, powerUps);
    }

    /**
     * a listener to notify the statistics window UI of any change to the statistics
     */
    public interface GameStatisticsListener {
        void onHealthChanged(int health);

        void onTimerChanged(String currentTime);

        void onScoreChanged(int score);

        void onProjectileCountChange(int[] atoms, int[] powerUps);
    }


}
