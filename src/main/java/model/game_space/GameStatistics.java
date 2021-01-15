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
    public void updateHealth(double health) {
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

    public void updateScore(double score) { //assuming the score will only be incremented by 1.
        statisticsListener.onScoreChanged(score);
    }

    /**
     * called when the number of a any projectile is changed. (by shooting, blending, catching, etc.)
     *
     */
    public void changeProjectileCount() {// todo: this is called from projectile container via the controller
        statisticsListener.onProjectileCountChange();
    }

    public void changeShieldCount() {// todo: this is called from projectile container via the controller
        statisticsListener.onShieldsCountChange();
    }

    /**
     * a listener to notify the statistics window UI of any change to the statistics
     */
    public interface GameStatisticsListener {
        void onHealthChanged(double health);

        void onTimerChanged(String currentTime);

        void onScoreChanged(double score);

        void onProjectileCountChange();
        void onShieldsCountChange();
    }


}
