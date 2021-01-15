package model.game_building;

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

    public static final String SESSION_COLLECTION_TITLE = "GameSessions";

    public static final double GAME_SIZE_RATIO = (16.0 / 9.0);

    // Game difficulty drop rate values
    public static final int EASY_MODE_GAME_DROP_RATE = 3000;
    public static final int MEDIUM_MODE_GAME_DROP_RATE = 2000;
    public static final int HARD_MODE_GAME_DROP_RATE = 1000;

    public static final int GAME_THREAD_DELAY = 7; // TODO: Check if valid
    public static final int DEFAULT_SHOOTER_DELAY = 250;

    // Objects Dimensions.
    public static final Dimension BUILDING_WINDOW_SIZE = new Dimension(800, 800);
    public static final Dimension BLENDER_WINDOW_SIZE = new Dimension(300, 100);
    public static final int FPS = 60;

    // Drawable sizes with regard to L
    public static final double ATOM_RADIUS = 0.1;
    public static final double MOLECULE_RADIUS = 0.25;
    public static final double LINEAR_MOLECULE_HEIGHT = 0.18;
    public static final double BLOCKER_RADIUS = 0.5;
    public static final double POWERUP_RADIUS = 0.5; //not specified
    public static final double SHOOTER_HEIGHT = 1.0;
    public static final double SHOOTER_WIDTH = 0.5;

    public static final double ICON_SIZE = 0.5;

    public static final double BLOCKER_BLOCKING_RADIUS = 1.0;
    public static final double BLOCKER_EXPLOSION_RADIUS = 2.0;
    // Entities speed with respect to L
    public static final double ATOM_SPEED = 1.0;
    public static final double STRAIGHT_SPEED = 1.0;
    public static final double ZIGZAG_SPEED = 1.0;
    public static final double ZIGZAG_SPEED_ANGLE = 45;

    public static final double[][] BLENDING_MATRIX = {{1, 2, 3, 4}, {.5, 1, 1, 1.5}, {0.333, 0.333, 1, 0.666}, {.25, .25, .25, 1}}; // Contains the values corresponding to blending/breaking atoms.
    public static final int ICON_WIDTH = 50;
    public static final int ICON_HEIGHT = 50;

    public static final double STATISTICS_PANEL_WIDTH_RATIO = 0.2;
    public static final double GAME_PANEL_WIDTH_RATIO = 0.8;
    public static final int PANEL_SEPARATOR_WIDTH = 1;

    public static final double SPINNING_SPEED = 10;

    // Shields and Atoms constants.
    public static final int[] ALPHA_NEUTRON_VALUES = {7, 8, 9};
    public static final int[] BETA_NEUTRON_VALUES = {15, 16, 17, 18, 21};
    public static final int[] GAMMA_NEUTRON_VALUES = {29, 32, 33};
    public static final int[] SIGMA_NEUTRON_VALUES = {63, 64, 67};

    public static final int ALPHA_PROTONS = 8; // TODO: Change implementation to something cleaner.
    public static final int BETA_PROTONS = 16;
    public static final int GAMMA_PROTONS = 32;
    public static final int SIGMA_PROTONS = 64;

    // Shield types constants.
    public static final double ETA_EFFICIENCY_BOOST = 0.05;
    public static final double ETA_SPEED_REDUCTION_PERCENTAGE = 0.05;

    public static final double LOTA_EFFICIENCY_BOOST = 0.1;
    public static final double LOTA_SPEED_REDUCTION_PERCENTAGE = 0.07;

    public static final double ZETA_EFFICIENCY_BOOST = 0.2;
    public static final double ZETA_SPEED_REDUCTION_PERCENTAGE = 0.11;

    public static final double THETA_SPEED_REDUCTION_PERCENTAGE = 0.09;

    public static final double[] thetaEfficiencyValues = {0.05, 0.15};

    public static final double DEFAULT_ATOM_SPEED_PERCENTAGE = 1.0;
    public static final double DEFAULT_POWERUP_SPEED_PERCENTAGE = 1.0;

    // Atoms' Stability Constants.
    public static final double ALPHA_STABILITY_CONSTANT = 0.85;
    public static final double BETA_STABILITY_CONSTANT = 0.9;
    public static final double GAMMA_STABILITY_CONSTANT = 0.8;
    public static final double SIGMA_STABILITY_CONSTANT = 0.7;

    public static final double ATOM_SPEED_PERCENTAGE = 1.0;

    //Themes
    public static final String DISCO = "disco";
    public static final String PEPEGA = "pepega";

}
