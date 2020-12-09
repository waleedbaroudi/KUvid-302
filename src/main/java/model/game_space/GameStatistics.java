package model.game_space;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;

public class GameStatistics {
    int health, score; //might be doubles?
    GameTimer timer;
    GameStatisticsListener statisticsListener;

    public GameStatistics(GameStatisticsListener statisticsListener) { //listener should be given by the statistics window (possibly JPanel)
        this.health = 100;
        this.timer = new GameTimer(10);
        this.score = 0;
        this.statisticsListener = statisticsListener;
    }

    /**
     * decreases the health and indicates whether the health is over.
     *
     * @param damage damage to be deducted from the health
     * @return returns true if the the health reaches 0, false otherwise.
     */
    public boolean decreaseHealth(int damage) {
        this.health -= damage;
        if (this.health < 0)
            this.health = 0;
        statisticsListener.onHealthChanged(this.health);
        return this.health <= 0;
    }

    /**
     * decreases the game timer and checks whether the time is over.
     *
     * @param amountInMillis the amount of time to be decreased from the game timer.
     * @return returns true if the time is over, false otherwise.
     */
    public boolean updateTimer(int amountInMillis) {
        timer.decrease(amountInMillis);
        statisticsListener.onTimerChanged(timer.getCurrentTimer());
        return timer.getRemainingTimeMillis() <= 0;
    }

    public void incrementScore() { //assuming the score will only be incremented by 1.
        this.score++;
        statisticsListener.onScoreChanged(score);
    }

    /**
     * called when the number of a certain projectile is changed. (by shooting, blending, catching, etc.)
     *
     * @param superType     the type of the projectile; atom or power-up
     * @param type          the kind of the projectile; alpha, beta, gamma, or sigma.
     * @param currentNumber the updated count of the specified projectile
     */
    public void changeProjectileCount(SuperType superType, EntityType type, int currentNumber) {// todo: this is called from projectile container via the controller
        statisticsListener.onProjectileCountChange(superType, type, currentNumber);
    }

    /**
     * a listener to notify the statistics window UI of any change to the statistics
     */
    public interface GameStatisticsListener {
        void onHealthChanged(int health);

        void onTimerChanged(String currentTime);

        void onScoreChanged(int score);

        void onProjectileCountChange(SuperType superType, EntityType type, int newCount);
    }


}
