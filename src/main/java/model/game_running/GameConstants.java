package model.game_running;

import java.awt.*;

public final class GameConstants {

    private GameConstants(){}//this class should not be initialized.

    // Strings
    public static final String GAME_TITLE = "KUvid";
    // Values
    public static final int GAME_STATE_PAUSED = -1;
    public static final int GAME_STATE_RESUMED = 1;
    public static final int GAME_STATE_STOP = -10;
    public static final int SHOOTER_MOVEMENT_LEFT = 9;
    public static final int SHOOTER_MOVEMENT_RIGHT = 3;
    public static final int SHOOTER_MOVEMENT_STILL = 12;
    public static final int SHOOTER_ROTATION_LEFT = -5;
    public static final int SHOOTER_ROTATION_RIGHT = 5;
    public static final int SHOOTER_ROTATION_STILL = 0;
    public static final double GAME_SIZE_RATIO = (16.0 / 9.0);
    public static final int GAME_THREAD_DELAY = 15;
    public static final Dimension BUILDING_WINDOW_SIZE = new Dimension(800, 800);
    public static final Dimension PowerupDimensions = new Dimension(20, 20);
    public static final Dimension MoleculeDimensions = new Dimension(30, 30);
    public static final Dimension AtomDimensions = new Dimension(10, 10);
    public static final Dimension ShooterDimensions = new Dimension(20, 50);
    public static final Dimension BlockerDimensions = new Dimension(20, 20);
    public static final int FPS = 60;
}
