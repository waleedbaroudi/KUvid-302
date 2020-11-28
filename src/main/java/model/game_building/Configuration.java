package model.game_building;

import org.apache.log4j.BasicConfigurator;

import model.game_running.GameConstants;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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

    public int getNumOfPowerUpsPerType() {
        return isConfigBundleSet() ? configBundle.getNumOfPowerUpsPerType() : -1;
    }

    public int getNumOfBlockersPerType() {
        return isConfigBundleSet() ? configBundle.getNumOfBlockersPerType() : -1;
    }

    public int getNumOfMoleculesPerType() {
        return isConfigBundleSet() ? configBundle.getNumOfMoleculesPerType() : -1;
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
        return (int) (getGameHeight() * GameConstants.GAME_SIZE_RATIO);
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

}
