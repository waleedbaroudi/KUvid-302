package ui.windows;

import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_running.GameConstants;
import ui.movable_drawables.ImageResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StatisticsWindow extends JPanel {

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
    JTextField gammaAtomsNumberTF;
    JTextField alphaAtomsNumberTF;
    JTextField betaAtomsNumberTF;
    JTextField sigmaAtomsNumberTF;

    JTextField gammaPowerupsNumberTF;
    JTextField alphaPowerupsNumberTF;
    JTextField betaPowerupsNumberTF;
    JTextField sigmaPowerupsNumberTF;

    JTextField healthTF;
    JTextField timeTF;
    JTextField scoreTF;
    JTextField scoreTextTF;


    public StatisticsWindow() {
        this.setSize(GameConstants.STATISTICS_WINDOW_SIZE);

        setImages();
        initializeTextFields();
    }

    private void initializeTextFields() {
        gammaAtomsNumberTF = new JTextField();
        alphaAtomsNumberTF = new JTextField();
        betaAtomsNumberTF = new JTextField();
        sigmaAtomsNumberTF = new JTextField();

        gammaPowerupsNumberTF = new JTextField();
        alphaPowerupsNumberTF = new JTextField();
        betaPowerupsNumberTF = new JTextField();
        sigmaPowerupsNumberTF = new JTextField();

        healthTF = new JTextField();
        timeTF = new JTextField();
        scoreTF = new JTextField();
        scoreTextTF = new JTextField();
    }

    private void setImages() {
        atomAlphaImg = ImageResources.get(EntityType.ALPHA, SuperType.ATOM, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomBetaImg = ImageResources.get(EntityType.BETA, SuperType.ATOM, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomSigmaImg = ImageResources.get(EntityType.SIGMA, SuperType.ATOM, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        atomGammaImg = ImageResources.get(EntityType.GAMMA, SuperType.ATOM, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);

        powerupAlphaImg = ImageResources.get(EntityType.ALPHA, SuperType.POWERUP, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupBetaImg = ImageResources.get(EntityType.BETA, SuperType.POWERUP, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupSigmaImg = ImageResources.get(EntityType.SIGMA, SuperType.POWERUP, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        powerupGammaImg = ImageResources.get(EntityType.GAMMA, SuperType.POWERUP, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);

        healthImg = ImageResources.getIcon(1, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        watchImg = ImageResources.getIcon(2, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
        blenderImg = ImageResources.getIcon(3, GameConstants.ICON_WIDTH, GameConstants.ICON_HEIGHT);
    }
}
