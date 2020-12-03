package model.game_running.runnables;

import model.game_building.Configuration;
import model.game_entities.*;
import model.game_entities.enums.BlockerType;
import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.MoleculeType;
import model.game_entities.enums.PowerupType;
import model.game_entities.factories.BlockerFactory;
import model.game_entities.factories.MoleculeFactory;
import model.game_entities.factories.PowerupFactory;
import model.game_running.GameConstants;
import model.game_running.RunningMode;
import org.apache.log4j.Logger;
import utils.Coordinates;

import java.util.Map;
import java.util.Random;

/**
 * responsible for creating blockers, and powerups, molecules in the game space
 */
public class EntityGeneratorRunnable extends GameRunnable {
    private Map<Map<MoleculeType, MoleculeStructure>, Integer> numberOfMolecules;
    private Map<BlockerType, Integer> numberOfBlockers;
    private Map<PowerupType, Integer> numberOfPowerup;
    private RunningMode runningMode;

    private static Random random = new Random();
    private static Logger logger = Logger.getLogger(EntityGeneratorRunnable.class.getName());


    public EntityGeneratorRunnable(RunningMode runningMode) {
        super();
        this.runningMode = runningMode;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                int choice = random.nextInt(3);
                switch (choice) {
                    case 0:
                        this.runningMode.addEntity(generateMolecule());
                        break;
                    case 1:
                        this.runningMode.addEntity(generateBlocker());
                        break;
                    case 2:
                        this.runningMode.addEntity(generatePowerup());
                        break;
                }
                //sleep before adding new objects
                Thread.sleep(Configuration.getInstance().getDropRate());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    // TODO: generator ALWAYS generate entities of the same type
    /**
     * generates a random Blocker to be thrown in the space
     *
     * @return a Blocker of a random type
     */
    public Blocker generateBlocker() {
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        logger.info("[ObjectGenerator: generating a blocker at coordinates " + new Coordinates(x_coord, 0) + " ]");

        Random random = new Random();
        int rand = random.nextInt(4);

        Blocker blocker = BlockerFactory.getInstance().getBlocker(rand);
        blocker.setCoordinates(new Coordinates(x_coord, 0));

        return blocker;
    }

    /**
     * generates a random powerup to be thrown in the space
     *
     * @return a Powerup of a random type
     */
    public Powerup generatePowerup() {
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        logger.info("[ObjectGenerator: generating a powerup at coordinates " + new Coordinates(x_coord, 0) + " ]");

        Random random = new Random();
        int rand = random.nextInt(4);

        Powerup powerup = PowerupFactory.getInstance().getPowerup(rand);
        powerup.setCoordinates(new Coordinates(x_coord, 0));

        return powerup;
    }

    /**
     * generates a random Molecule to be thrown in the space
     *
     * @return a Molecule of a random type
     */
    public Molecule generateMolecule() {
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;

        logger.info("[ObjectGenerator: generating a molecule at coordinates " + new Coordinates(x_coord, 0) + " ]");

        Random random = new Random();
        int rand = random.nextInt(4);

        Molecule molecule = MoleculeFactory.getInstance().getMolecule(rand);
        molecule.setCoordinates(new Coordinates(x_coord, 0));

        return molecule;
    }
}
