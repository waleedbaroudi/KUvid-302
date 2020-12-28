package model.game_building;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import utils.IOHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static model.game_building.GameConstants.*;

public class Configuration {

    private static Configuration instance;
    private ConfigBundle configBundle;
    private static Logger logger;

    // Difficulty is represented by  0 1 or 2 representing easy, medium, or difficult.
    //private int numAtoms, numMolecules, numBlockers, numPowerup, difficulty;
    //private double l; // L representing the unit length of the game
    //private boolean isSpinningAlpha, isSpinningBeta , isLinearAlpha, isLinearBeta;


    // private constructor restricted to this class itself
    private Configuration() {
        BasicConfigurator.configure();
        logger = Logger.getLogger(Configuration.class.getName());

        try {
            configBundle = IOHandler.readConfigFromYaml("temp");
        } catch (IOException exception) {
            logger.error("[Configuration] {FATAL}: Could not load game configurations.", exception);
            exception.printStackTrace();
        }

        File temp = new File(System.getProperty("user.dir") + "/configurations/temp.yaml");
        if (!temp.delete())
            logger.warn("Temporary configuration file was not deleted");
    }

    /**
     * Creates a configuration instance if there wasn't one created already.
     *
     * @return instance
     */
    public synchronized static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration(); //makeInstance
        }
        return instance;
    }

    /**
     * This method must be called in the building-mode before the game starts. It attaches the configBundle object
     * to the Configuration singleton.
     *
     * @param configBundle takes configurations as a bundle
     */
    public synchronized void setConfig(ConfigBundle configBundle) {
        if (this.configBundle == null) {
            this.configBundle = configBundle;
        } else {
            // The logger is initialised in the constructor so we check if the instance was created so
            // we can use the logger instance
            if (instance == null)
                logger.error("Configuration instance has not been initialised");
            else
                logger.info("[Configuration] Configuration has already been set. Build the game again to change it.");
        }
    }

    public int getNumAlphaAtoms() {
        return isConfigBundleSet() ? configBundle.getNumOfAlphaAtoms() : -1;
    }

    public int getNumBetaAtoms() {
        return isConfigBundleSet() ? configBundle.getNumOfBetaAtoms() : -1;
    }

    public int getNumGammaAtoms() {
        return isConfigBundleSet() ? configBundle.getNumOfGammaAtoms() : -1;
    }

    public int getNumSigmaAtoms() {
        return isConfigBundleSet() ? configBundle.getNumOfSigmaAtoms() : -1;
    }

    public int getNumAlphaPowerups() {
        return isConfigBundleSet() ? configBundle.getNumOfAlphaPowerups() : -1;
    }

    public int getNumBetaPowerups() {
        return isConfigBundleSet() ? configBundle.getNumOfBetaPowerups() : -1;
    }

    public int getNumGammaPowerups() {
        return isConfigBundleSet() ? configBundle.getNumOfGammaPowerups() : -1;
    }

    public int getNumSigmaPowerups() {
        return isConfigBundleSet() ? configBundle.getNumOfSigmaPowerups() : -1;
    }

    public int getNumAlphaBlockers() {
        return isConfigBundleSet() ? configBundle.getNumOfAlphaBlockers() : -1;
    }

    public int getNumBetaBlockers() {
        return isConfigBundleSet() ? configBundle.getNumOfBetaBlockers() : -1;
    }

    public int getNumGammaBlockers() {
        return isConfigBundleSet() ? configBundle.getNumOfGammaBlockers() : -1;
    }

    public int getNumSigmaBlockers() {
        return isConfigBundleSet() ? configBundle.getNumOfSigmaBlockers() : -1;
    }

    public int getNumAlphaMolecules() {
        return isConfigBundleSet() ? configBundle.getNumOfAlphaMolecules() : -1;
    }

    public int getNumBetaMolecules() {
        return isConfigBundleSet() ? configBundle.getNumOfBetaMolecules() : -1;
    }

    public int getNumGammaMolecules() {
        return isConfigBundleSet() ? configBundle.getNumOfGammaMolecules() : -1;
    }

    public int getNumSigmaMolecules() {
        return isConfigBundleSet() ? configBundle.getNumOfSigmaMolecules() : -1;
    }

    public int getDropRate() {// TODO: modify when difficulty is converted to enum
        if (!isConfigBundleSet())
            return -1;
        int difficulty = this.getDifficulty();
        switch (difficulty) {
            case 0:
                return EASY_MODE_GAME_DROP_RATE;
            case 1:
                return MEDIUM_MODE_GAME_DROP_RATE;
            case 2:
                return HARD_MODE_GAME_DROP_RATE;
            default:
                return 1000;
        }
    }

    public double getUnitL() {
        return isConfigBundleSet() ? configBundle.getL() : -1;
    }

    public int getDifficulty() {
        return isConfigBundleSet() ? configBundle.getDifficulty() : -1;
    }

    public boolean isLinearAlpha() {
        return isConfigBundleSet() && configBundle.isLinearAlpha();
    }

    public boolean isLinearBeta() {
        return isConfigBundleSet() && configBundle.isLinearBeta();
    }

    public boolean isSpinningAlpha() {
        return isConfigBundleSet() && configBundle.isSpinningAlpha();
    }

    public boolean isSpinningBeta() {
        return isConfigBundleSet() && configBundle.isSpinningBeta();
    }

    public int getGameHeight() {
        return (int) (10 * getUnitL());
    }

    public int getGameWidth() {
        return (int) (getGameHeight() * GAME_SIZE_RATIO);
    }

    public Dimension getGamePanelDimensions() {
        return new Dimension((int) (getGameWidth() * GAME_PANEL_WIDTH_RATIO), getGameHeight());
    }

    public Dimension getStatisticsPanelDimensions() {
        return new Dimension((int) (getGameWidth() * STATISTICS_PANEL_WIDTH_RATIO), getGameHeight());
    }

    public Dimension getRunningWindowDimension() {
        return new Dimension(getGameWidth() + PANEL_SEPARATOR_WIDTH, getGameHeight());
    }

    /**
     * checks whether the config bundle is set and, if not, signals potential invalid arguments.
     *
     * @return whether the config bundle is null or not
     */
    private boolean isConfigBundleSet() {
        if (configBundle == null) {
            logger.warn("Config bundle is not set yet. returned values are inaccurate");
            return false;
        }
        return true;
    }

    public double getShooterSpeed() {
        return getUnitL() / (double) FPS; //TODO: ask about the speed.
    }

    public double getStraightPatternSpeed() {
        return getUnitL() / (double) FPS;
    }

    public double getAtomSpeed() {
        return 3 * getUnitL() / (double) FPS;
    }

    public double getZigZagPatternSpeed() {
        return getUnitL() / (double) FPS;
    }


}
