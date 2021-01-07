package ui.windows;

import model.game_building.Configuration;
import model.game_building.GameConstants;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.ShieldType;
import model.game_entities.enums.SuperType;
import model.game_running.ProjectileContainer;
import model.game_running.RunningMode;
import model.game_space.GameStatistics;
import ui.movable_drawables.ImageResources;
import utils.MathUtils;

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
    private Image atomAlphaImg, atomBetaImg, atomSigmaImg, atomGammaImg;
    private Image healthImg, watchImg, blenderImg, blenderImg_bg;

    //powerups
    private Image powerupAlphaImg, powerupBetaImg, powerupSigmaImg, powerupGammaImg;
    private Image powerupAlphaImg_bg, powerupBetaImg_bg, powerupSigmaImg_bg, powerupGammaImg_bg;

    //shields
    private Image etaImg, lotaImg, thetaImg, zetaImg;
    private Image etaImg_bg, lotaImg_bg, thetaImg_bg, zetaImg_bg;
    private JLabel etaButton, lotaButton, thetaButton, zetaButton;
    private JLabel etaNumberLabel, lotaNumberLabel, thetaNumberLabel, zetaNumberLabel;

    //Atoms
    

    // JLabels
    private JLabel gammaAtomsNumberLabel, alphaAtomsNumberLabel, betaAtomsNumberLabel, sigmaAtomsNumberLabel;
    private JLabel gammaPowerupsNumberLabel, alphaPowerupsNumberLabel, betaPowerupsNumberLabel, sigmaPowerupsNumberLabel;
    private JLabel healthLabel, timeLabel, SCORE, scoreLabel;

    // JButtons
    private JLabel blenderButton, alphaPowerupButton, betaPowerupButton, gammaPowerupButton, sigmaPowerupButton;

    //iconSize
    int iconSize = (int) (Configuration.getInstance().getUnitL() * GameConstants.ICON_SIZE);

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
        initializeTextLabels();
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

        etaButton = new JLabel(new ImageIcon(etaImg));
        lotaButton = new JLabel(new ImageIcon(lotaImg));
        thetaButton = new JLabel(new ImageIcon(thetaImg));
        zetaButton = new JLabel(new ImageIcon(zetaImg));


        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 6;
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

        //x = 1
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.weightx = 0.5;
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

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 1;
        add(blenderButton, gridBagConstraints);

        //x = 0
        gridBagConstraints.gridx = 0;
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
        gridBagConstraints.gridy = 8;
        add(new JLabel(new ImageIcon(atomAlphaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(new JLabel(new ImageIcon(atomBetaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(new JLabel(new ImageIcon(atomGammaImg)), gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(new JLabel(new ImageIcon(atomSigmaImg)), gridBagConstraints);

        //x = 2
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        add(etaButton, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(lotaButton, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(thetaButton, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(zetaButton, gridBagConstraints);

        //x = 3
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        add(etaNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 9;
        add(lotaNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 10;
        add(thetaNumberLabel, gridBagConstraints);
        gridBagConstraints.gridy = 11;
        add(zetaNumberLabel, gridBagConstraints);

        setButtonListeners();

    }

    /**
     * initializes TextField that correspond to number of atoms/powerups, score, health, and time
     */
    private void initializeTextLabels() {
        ProjectileContainer container = this.runningMode.getProjectileContainer();
        alphaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.ALPHA)));
        betaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.BETA)));
        gammaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.GAMMA)));
        sigmaAtomsNumberLabel = new JLabel(String.valueOf(container.getAtomCountForType(EntityType.SIGMA)));

        gammaPowerupsNumberLabel = new JLabel("0");
        alphaPowerupsNumberLabel = new JLabel("0");
        betaPowerupsNumberLabel = new JLabel("0");
        sigmaPowerupsNumberLabel = new JLabel("0");

        etaNumberLabel = new JLabel("" + Configuration.getInstance().getNumOfEtaShields());
        lotaNumberLabel = new JLabel("" + Configuration.getInstance().getNumOfLotaShields());
        thetaNumberLabel = new JLabel("" + Configuration.getInstance().getNumOfThetaShields());
        zetaNumberLabel = new JLabel("" + Configuration.getInstance().getNumOfZetaShields());

        healthLabel = new JLabel("100");
        timeLabel = new JLabel("10 : 00");
        SCORE = new JLabel("Score: ");
        scoreLabel = new JLabel("0.0");
    }

    /**
     * retrieves images from ImageResources with the specified height and width
     */
    private void retrieveImages() {
        atomAlphaImg = ImageResources.getEntityIcon(SuperType.ATOM.toString(), EntityType.ALPHA.toString(), iconSize, iconSize, false);
        atomBetaImg = ImageResources.getEntityIcon(SuperType.ATOM.toString(), EntityType.BETA.toString(), iconSize, iconSize, false);
        atomSigmaImg = ImageResources.getEntityIcon(SuperType.ATOM.toString(), EntityType.SIGMA.toString(), iconSize, iconSize, false);
        atomGammaImg = ImageResources.getEntityIcon(SuperType.ATOM.toString(), EntityType.GAMMA.toString(), iconSize, iconSize, false);

        powerupAlphaImg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.ALPHA.toString(), iconSize, iconSize, false);
        powerupBetaImg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.BETA.toString(), iconSize, iconSize, false);
        powerupSigmaImg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.SIGMA.toString(), iconSize, iconSize, false);
        powerupGammaImg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.GAMMA.toString(), iconSize, iconSize, false);

        powerupAlphaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.ALPHA.toString(), iconSize, iconSize, true);
        powerupBetaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.BETA.toString(), iconSize, iconSize, true);
        powerupSigmaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.SIGMA.toString(), iconSize, iconSize, true);
        powerupGammaImg_bg = ImageResources.getEntityIcon(SuperType.POWERUP.toString(), EntityType.GAMMA.toString(), iconSize, iconSize, true);

        healthImg = ImageResources.getEntityIcon("icons", "health", iconSize, iconSize, false);
        watchImg = ImageResources.getEntityIcon("icons", "timer", iconSize, iconSize, false);
        blenderImg = ImageResources.getEntityIcon("icons", "blender", iconSize, iconSize, false);
        blenderImg_bg = ImageResources.getEntityIcon("icons", "blender", iconSize, iconSize, true);

        etaImg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.ETA.toString(), iconSize, iconSize, false);
        lotaImg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.LOTA.toString(), iconSize, iconSize, false);
        thetaImg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.THETA.toString(), iconSize, iconSize, false);
        zetaImg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.ZETA.toString(), iconSize, iconSize, false);

        etaImg_bg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.ETA.toString(), iconSize, iconSize, true);
        lotaImg_bg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.LOTA.toString(), iconSize, iconSize, true);
        thetaImg_bg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.THETA.toString(), iconSize, iconSize, true);
        zetaImg_bg = ImageResources.getEntityIcon(SuperType.SHIELD.toString(), ShieldType.ZETA.toString(), iconSize, iconSize, true);
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


        MouseAdapter etaAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShieldHandler().applyEtaShield();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                etaButton.setIcon(new ImageIcon(etaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                etaButton.setIcon(new ImageIcon(etaImg));
            }
        };

        MouseAdapter lotaAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShieldHandler().applyLotaShield();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lotaButton.setIcon(new ImageIcon(lotaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lotaButton.setIcon(new ImageIcon(lotaImg));
            }
        };
        MouseAdapter thetaAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShieldHandler().applyThetaShield();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                thetaButton.setIcon(new ImageIcon(thetaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                thetaButton.setIcon(new ImageIcon(thetaImg));
            }
        };
        MouseAdapter zetaAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                runningMode.getShieldHandler().applyZetaShield();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                zetaButton.setIcon(new ImageIcon(zetaImg_bg));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                zetaButton.setIcon(new ImageIcon(zetaImg));
            }
        };

        blenderButton.addMouseListener(blenderAdapter);

        alphaPowerupButton.addMouseListener(alphaPowerupAdapter);
        betaPowerupButton.addMouseListener(betaPowerupAdapter);
        sigmaPowerupButton.addMouseListener(sigmaPowerupAdapter);
        gammaPowerupButton.addMouseListener(gammaPowerupAdapter);

        etaButton.addMouseListener(etaAdapter);
        lotaButton.addMouseListener(lotaAdapter);
        thetaButton.addMouseListener(thetaAdapter);
        zetaButton.addMouseListener(zetaAdapter);

        blenderButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        alphaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        betaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        sigmaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        gammaPowerupButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        etaButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        lotaButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        thetaButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
        zetaButton.setFocusable(false); // This is necessary so that clicking the button does not steal the focus from the main panel
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
    public void onScoreChanged(double score) {
        scoreLabel.setText(MathUtils.truncateByTwo(score));
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

    @Override
    public void onShieldsCountChange(ShieldType type, int newCount) {
        String stringValue = String.valueOf(newCount);
        switch (type) {
            case ETA:
                etaNumberLabel.setText(stringValue);
                break;
            case LOTA:
                lotaNumberLabel.setText(stringValue);
                break;
            case THETA:
                thetaNumberLabel.setText(stringValue);
                break;
            case ZETA:
                zetaNumberLabel.setText(stringValue);
                break;
        }
    }
}
