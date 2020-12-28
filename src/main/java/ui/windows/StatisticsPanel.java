package ui.windows;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.Entity;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_running.ProjectileContainer;
import model.game_running.RunningMode;
import model.game_space.GameStatistics;
import ui.movable_drawables.ImageResources;

import javax.swing.*;
import java.awt.*;

/**
 * This class draws the Statistics window. through this window, the player
 * can observe the statistics of the game such as atom/power numbers and score...
 */
public class StatisticsPanel extends JPanel implements GameStatistics.GameStatisticsListener {

    private final RunningMode runningMode;

    //Icons
    private Image atomAlphaImg;
    private Image atomBetaImg;
    private Image atomSigmaImg;
    private Image atomGammaImg;

    private Image powerupAlphaImg;
    private Image powerupBetaImg;
    private Image powerupSigmaImg;
    private Image powerupGammaImg;

    private Image healthImg;
    private Image watchImg;
    private Image blenderImg;

    // JLabels
    JLabel gammaAtomsNumberLabel;
    JLabel alphaAtomsNumberLabel;
    JLabel betaAtomsNumberLabel;
    JLabel sigmaAtomsNumberLabel;

    JLabel gammaPowerupsNumberLabel;
    JLabel alphaPowerupsNumberLabel;
    JLabel betaPowerupsNumberLabel;
    JLabel sigmaPowerupsNumberLabel;

    JLabel healthLabel;
    JLabel timeLabel;
    JLabel SCORE;
    JLabel scoreLabel;

    // JButtons
    JButton blenderButton;
    JButton alphaPowerupButton;
    JButton betaPowerupButton;
    JButton gammaPowerupButton;
    JButton sigmaPowerupButton;

    public StatisticsPanel(RunningMode runningMode) {
        this.setPreferredSize(Configuration.getInstance().getStatisticsPanelDimensions());

        //Controller
        GameStatistics gameStatistics = new GameStatistics(this);
        this.runningMode = runningMode;
        runningMode.setStatisticsController(gameStatistics);
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        setOpaque(false);

        retrieveImages();
        initializeTextFields();
        setContent();
    }

    /**
     * Add images and numbers to the window
     */
    private void setContent() {

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Defining our buttons
        blenderButton = new JButton(new ImageIcon(blenderImg));
        alphaPowerupButton = new JButton(new ImageIcon(powerupAlphaImg));
        betaPowerupButton = new JButton(new ImageIcon(powerupBetaImg));
        gammaPowerupButton = new JButton(new ImageIcon(powerupGammaImg));
        sigmaPowerupButton = new JButton(new ImageIcon(powerupSigmaImg));

        gridBagConstraints.ipady = 10;
        //x = 0
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;
        add(SCORE, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(new JLabel(new ImageIcon(watchImg)), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(new JLabel(new ImageIcon(healthImg)), gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(alphaPowerupButton, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(betaPowerupButton, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(gammaPowerupButton, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(sigmaPowerupButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = 7;
        add(blenderButton, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy = 8;
        add(alphaAtomsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(betaAtomsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(gammaAtomsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(sigmaAtomsNumberLabel, gridBagConstraints);

        //x = 1
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(scoreLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(timeLabel, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(healthLabel, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(alphaPowerupsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(betaPowerupsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(gammaPowerupsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(sigmaPowerupsNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 8;

        add(new JLabel(new ImageIcon(atomAlphaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(new JLabel(new ImageIcon(atomBetaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(new JLabel(new ImageIcon(atomGammaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(new JLabel(new ImageIcon(atomSigmaImg)), gridBagConstraints);

        setButtonListeners();
    }

    private void setButtonListeners(){
        //set click listener for blender
        blenderButton.addActionListener(e -> {
            runningMode.setRunningState(GameConstants.GAME_STATE_PAUSED);
            runningMode.getBlender().showBlender();
        });

        alphaPowerupButton.addActionListener(e -> {
            runningMode.getShooter().setPowerup(EntityType.ALPHA);
        });

        betaPowerupButton.addActionListener(e -> {
            runningMode.getShooter().setPowerup(EntityType.BETA);
        });

        gammaPowerupButton.addActionListener(e -> {
            runningMode.getShooter().setPowerup(EntityType.GAMMA);
        });

        sigmaPowerupButton.addActionListener(e -> {
            runningMode.getShooter().setPowerup(EntityType.SIGMA);
        });

        blenderButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel //set click listener for blender

        alphaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        betaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        sigmaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        gammaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
    }

    /**
     * initializes TextField that correspond to number of atoms/powerups, score, health, and time
     */
    private void initializeTextFields() {
        ProjectileContainer container = this.runningMode.getProjectileContainer();
        alphaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.ALPHA)));
        betaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.BETA)));
        gammaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.GAMMA)));
        sigmaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.SIGMA)));

        gammaPowerupsNumberLabel = new JLabel("0");
        alphaPowerupsNumberLabel = new JLabel("0");
        betaPowerupsNumberLabel = new JLabel("0");
        sigmaPowerupsNumberLabel = new JLabel("0");

        healthLabel = new JLabel("100");
        timeLabel = new JLabel("10 : 00");
        SCORE = new JLabel("Score: ");
        scoreLabel = new JLabel("0.0");
    }

    /**
     * retrieves images from ImageResources with the specified height and width
     */
    private void retrieveImages() {
        atomAlphaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.ALPHA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomBetaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.BETA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomSigmaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.SIGMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomGammaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.GAMMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);

        powerupAlphaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.ALPHA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupBetaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.BETA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupSigmaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.SIGMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupGammaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.GAMMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);

        healthImg = ImageResources.getIcon("health", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        watchImg = ImageResources.getIcon("timer", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        blenderImg = ImageResources.getIcon("blender", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
    }

    @Override
    public void onHealthChanged(int health) {
        healthLabel.setText(String.valueOf(health));
    }

    @Override
    public void onTimerChanged(String currentTime) {
        timeLabel.setText(currentTime);
    }

    @Override
    public void onScoreChanged(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    @Override
    public void onProjectileCountChange(SuperType superType, EntityType type, int newCount) {
        String stringValue = String.valueOf(newCount);
        switch (type) {
            case ALPHA:
                if (superType == SuperType.ATOM)
                    alphaAtomsNumberLabel.setText(stringValue);
                else
                    alphaPowerupsNumberLabel.setText(stringValue);
                break;
            case BETA:
                if (superType == SuperType.ATOM)
                    betaAtomsNumberLabel.setText(stringValue);
                else
                    betaPowerupsNumberLabel.setText(stringValue);
                break;
            case GAMMA:
                if (superType == SuperType.ATOM)
                    gammaAtomsNumberLabel.setText(stringValue);
                else
                    gammaPowerupsNumberLabel.setText(stringValue);
                break;
            case SIGMA:
                if (superType == SuperType.ATOM)
                    sigmaAtomsNumberLabel.setText(stringValue);
                else
                    sigmaPowerupsNumberLabel.setText(stringValue);
                break;
        }
    }
}
