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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    private Image powerupAlphaImg_bg;
    private Image powerupBetaImg_bg;
    private Image powerupSigmaImg_bg;
    private Image powerupGammaImg_bg;

    private Image healthImg;
    private Image watchImg;
    private Image blenderImg;
    private Image blenderImg_bg;

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
    JLabel blenderButton;
    JLabel alphaPowerupButton;
    JLabel betaPowerupButton;
    JLabel gammaPowerupButton;
    JLabel sigmaPowerupButton;

    public StatisticsPanel(RunningMode runningMode) {
        this.setPreferredSize(Configuration.getInstance().getStatisticsPanelDimensions());

        //Controller
        this.runningMode = runningMode;
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
        blenderButton = new JLabel(new ImageIcon(blenderImg));
        alphaPowerupButton = new JLabel(new ImageIcon(powerupAlphaImg));
        betaPowerupButton = new JLabel(new ImageIcon(powerupBetaImg));
        gammaPowerupButton = new JLabel(new ImageIcon(powerupGammaImg));
        sigmaPowerupButton = new JLabel(new ImageIcon(powerupSigmaImg));

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

    private void setButtonListeners() {
        MouseAdapter blenderAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.setRunningState(GameConstants.GAME_STATE_PAUSED);
                runningMode.getBlender().showBlender();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                blenderButton.setIcon(new ImageIcon(blenderImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                blenderButton.setIcon(new ImageIcon(blenderImg));
            }
        };
        MouseAdapter alphaPowerupAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShooter().setPowerup(EntityType.ALPHA);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                alphaPowerupButton.setIcon(new ImageIcon(powerupAlphaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                alphaPowerupButton.setIcon(new ImageIcon(powerupAlphaImg));
            }
        };
        MouseAdapter betaPowerupAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShooter().setPowerup(EntityType.BETA);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                betaPowerupButton.setIcon(new ImageIcon(powerupBetaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                betaPowerupButton.setIcon(new ImageIcon(powerupBetaImg));
            }
        };
        MouseAdapter sigmaPowerupAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShooter().setPowerup(EntityType.SIGMA);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                sigmaPowerupButton.setIcon(new ImageIcon(powerupSigmaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                sigmaPowerupButton.setIcon(new ImageIcon(powerupSigmaImg));
            }
        };
        MouseAdapter gammaPowerupAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShooter().setPowerup(EntityType.GAMMA);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                gammaPowerupButton.setIcon(new ImageIcon(powerupGammaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                gammaPowerupButton.setIcon(new ImageIcon(powerupGammaImg));
            }
        };

        blenderButton.addMouseListener(blenderAdapter);
        alphaPowerupButton.addMouseListener(alphaPowerupAdapter);
        betaPowerupButton.addMouseListener(betaPowerupAdapter);
        sigmaPowerupButton.addMouseListener(sigmaPowerupAdapter);
        gammaPowerupButton.addMouseListener(gammaPowerupAdapter);

        blenderButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
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
        atomAlphaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.ALPHA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);
        atomBetaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.BETA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);
        atomSigmaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.SIGMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);
        atomGammaImg = ImageResources.getEntityIcon(SuperType.ATOM, EntityType.GAMMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);

        powerupAlphaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.ALPHA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);
        powerupBetaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.BETA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);
        powerupSigmaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.SIGMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);
        powerupGammaImg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.GAMMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, false);

        powerupAlphaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.ALPHA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, true);
        powerupBetaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.BETA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, true);
        powerupSigmaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.SIGMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, true);
        powerupGammaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP, EntityType.GAMMA, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT, true);

        healthImg = ImageResources.getIcon("health", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        watchImg = ImageResources.getIcon("timer", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        blenderImg = ImageResources.getIcon("blender", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        blenderImg_bg = ImageResources.getIcon("blender_bg", GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
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
    public void onProjectileCountChange(int[] atoms, int[] powerUps) {
        System.out.println("PROJECTILE UPDATE CALLED");
        alphaAtomsNumberLabel.setText(String.valueOf(atoms[0]));
        betaAtomsNumberLabel.setText(String.valueOf(atoms[1]));
        gammaAtomsNumberLabel.setText(String.valueOf(atoms[2]));
        sigmaAtomsNumberLabel.setText(String.valueOf(atoms[3]));

        alphaPowerupsNumberLabel.setText(String.valueOf(powerUps[0]));
        betaPowerupsNumberLabel.setText(String.valueOf(powerUps[1]));
        gammaPowerupsNumberLabel.setText(String.valueOf(powerUps[2]));
        sigmaPowerupsNumberLabel.setText(String.valueOf(powerUps[3]));
    }
}
