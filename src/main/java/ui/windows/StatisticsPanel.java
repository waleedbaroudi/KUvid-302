package ui.windows;

import model.game_building.Configuration;
import model.game_building.GameConstants;
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

    // JTextFields
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
        add(new JLabel(new ImageIcon(powerupAlphaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 4;
        add(new JLabel(new ImageIcon(powerupBetaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 5;
        add(new JLabel(new ImageIcon(powerupGammaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 6;
        add(new JLabel(new ImageIcon(powerupSigmaImg)), gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = 7;
        JButton blenderButton = new JButton(new ImageIcon(blenderImg));
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

        //set click listener for blender
        blenderButton.addActionListener(e -> {
            runningMode.setRunningState(GameConstants.GAME_STATE_PAUSED);
            runningMode.getBlender().showBlender();
        });
        blenderButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel

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
        atomAlphaImg = ImageResources.get(EntityType.ALPHA, SuperType.ATOM, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomBetaImg = ImageResources.get(EntityType.BETA, SuperType.ATOM, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomSigmaImg = ImageResources.get(EntityType.SIGMA, SuperType.ATOM, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomGammaImg = ImageResources.get(EntityType.GAMMA, SuperType.ATOM, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);

        powerupAlphaImg = ImageResources.get(EntityType.ALPHA, SuperType.POWERUP, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupBetaImg = ImageResources.get(EntityType.BETA, SuperType.POWERUP, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupSigmaImg = ImageResources.get(EntityType.SIGMA, SuperType.POWERUP, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupGammaImg = ImageResources.get(EntityType.GAMMA, SuperType.POWERUP, null, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);

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
