package model.game_space;

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
     */
    public void changeProjectileCount() {// todo: this is called from projectile container via the controller
        statisticsListener.onProjectileCountChange();
    }

    /**
     * a listener to notify the statistics window UI of any change to the statistics
     */
    public interface GameStatisticsListener {
        void onHealthChanged(int health);

        void onTimerChanged(String currentTime);

        void onScoreChanged(int score);

        void onProjectileCountChange();
    }


}
