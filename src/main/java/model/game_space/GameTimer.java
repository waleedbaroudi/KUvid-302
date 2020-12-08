package model.game_space;

public class GameTimer {
    private int remainingTimeMillis;
    private final int MIN_TO_MILLS_CONVERSION_CONSTANT = 60000;

    public GameTimer(int timeInMinutes) {
        this.remainingTimeMillis = timeInMinutes * MIN_TO_MILLS_CONVERSION_CONSTANT;
    }

    public void decrease(int timeInMillis) {
        remainingTimeMillis -= timeInMillis;
        if (remainingTimeMillis % 1000 == 0)
            System.out.println(getCurrentTimer());
    }

    public String getCurrentTimer() {
        return getMinutesCounter() + " : " + getSecondsCounter();
    }

    private int getMinutesCounter() {
        return remainingTimeMillis / MIN_TO_MILLS_CONVERSION_CONSTANT;
    }

    private int getSecondsCounter() {
        int secondsInMillis = remainingTimeMillis % MIN_TO_MILLS_CONVERSION_CONSTANT;
        return secondsInMillis / 1000;
    }
}
