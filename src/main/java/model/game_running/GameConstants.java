package model.game_running;

import model.game_building.Configuration;

import java.awt.*;

public final class GameConstants {

    private GameConstants() {
    }//this class should not be initialized.

    // Strings
    public static final String GAME_TITLE = "KUvid";

    // Game state constants.
    public static final int GAME_STATE_PAUSED = -1;
    public static final int GAME_STATE_RESUMED = 1;
    public static final int GAME_STATE_STOP = -10;

    // Shooter movement and rotation constants.
    public static final int SHOOTER_MOVEMENT_LEFT = 9;
    public static final int SHOOTER_MOVEMENT_RIGHT = 3;
    public static final int SHOOTER_MOVEMENT_STILL = 12;
    public static final int SHOOTER_ROTATION_LEFT = -5;
    public static final int SHOOTER_ROTATION_RIGHT = 5;
    public static final int SHOOTER_ROTATION_STILL = 0;

    public static final double GAME_SIZE_RATIO = (16.0 / 9.0);

    // Game difficulty delay values
    public static final int EASY_MODE_GAME_THREAD_DELAY = 45;
    public static final int MEDIUM_MODE_GAME_THREAD_DELAY = 30;
    public static final int HARD_MODE_GAME_THREAD_DELAY = 15;

    // Game difficulty drop rate values
    public static final int EASY_MODE_GAME_DROP_RATE = 5000;
    public static final int MEDIUM_MODE_GAME_DROP_RATE = 3000;
    public static final int HARD_MODE_GAME_DROP_RATE = 1000;

    public static final int GAME_THREAD_DELAY = 15;

    // Objects Dimensions.
    public static final Dimension BUILDING_WINDOW_SIZE = new Dimension(800, 800);
    public static final Dimension PowerupDimensions = new Dimension(20, 20);
    public static final Dimension MoleculeDimensions = new Dimension(30, 30);
    public static final Dimension AtomDimensions = new Dimension(10, 10);
    public static final Dimension ShooterDimensions = new Dimension(20, 50);
    public static final Dimension BlockerDimensions = new Dimension(20, 20);
    public static final int FPS = 60;

    //drawable sizes with regard to L
    public static final double ATOM_SIZE = 0.05;
    public static final double MOLECULE_SIZE = 0.5;
    public static final double BLOCKER_SIZE = 0.3;
    public static final double POWERUP_SIZE = 0.5;
    public static final double SHOOTER_HEIGHT = 1;
    public static final double SHOOTER_WIDTH = 0.6;

}
