package model.game_building;

import org.apache.log4j.Logger;

public class Configuration {

    private static Configuration instance = null;
    private ConfigBundle configBundle = null;
    private Logger logger;

    // Difficulty is represented by  0 1 or 2 representing easy, medium, or difficult.
    //private int numAtoms, numMolecules, numBlockers, numPowerup, difficulty;
    //private double l; // L representing the unit length of the game
    //private boolean isSpinningAlpha, isSpinningBeta , isLinearAlpha, isLinearBeta;


    // private constructor restricted to this class itself
    private Configuration() {
        this.logger = Logger.getLogger(Configuration.class.getName());
    }

    /**
     * Creates a configuration instance if there wasn't one created already.
     * @return instance
     */
    public synchronized static Configuration getInstance() {
        if (instance == null)
            instance = new Configuration(); //getInstance
        return instance;
    }

    /**
     * This method must be called in the building-mode before the game starts. It attaches the configBundle object
     * to the Configuration singleton.
     * @param configBundle
     */
    public void setConfigBundle(ConfigBundle configBundle){
        if(this.configBundle == null) {
            this.configBundle = configBundle;
        } else {
            // The logger is initialised in the constructor so we check if the instance was created so
            // we can use the logger instance
            if(instance == null)
                System.out.println("Configuration instance has not been initialised");
            logger.info("Configuration has already been set. Build the game against to change it.");
        }
    }

    /**
     * Returns the configBundle (configurations) of the game.
     * An example of using this method outside of this class would be: Configuration.getInstance().getConfigBundle().getDifficulty()
     * @return configBundle
     */
    private ConfigBundle getConfigBundle(){
        if(this.configBundle == null)
            logger.warn("ConfigBundle hasn't been set in the Configuration singleton");
        return this.configBundle;
    }

}
