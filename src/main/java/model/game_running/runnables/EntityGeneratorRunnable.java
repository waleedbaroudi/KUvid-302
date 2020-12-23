package model.game_running.runnables;

import model.game_building.Configuration;
import model.game_entities.*;
import model.game_entities.enums.EntityType;
import model.game_entities.factories.BlockerFactory;
import model.game_entities.factories.MoleculeFactory;
import model.game_entities.factories.PowerupFactory;
import model.game_building.GameConstants;
import model.game_running.RunningMode;
import org.apache.log4j.Logger;
import utils.Coordinates;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * responsible for creating blockers, and powerups, molecules in the game space
 */
public class EntityGeneratorRunnable extends GameRunnable {
    private Configuration config;

    private Map<EntityType, Integer> moleculeCountPerType, blockerCountPerType, powerUpCountPerType;

    private int totalMoleculeCount, totalBlockerCount, totalPowerUpCount = 0;

    private RunningMode runningMode;

    private static final Random random = new Random();
    private static final Logger logger = Logger.getLogger(EntityGeneratorRunnable.class.getName());


    public EntityGeneratorRunnable(RunningMode runningMode) {
        super();
        this.runningMode = runningMode;
        this.config = Configuration.getInstance();
        initializeMaps();
    }

    /**
     * fills the counting maps for molecules, blockers, power-ups
     */
    private void initializeMaps() {
        //fill molecule count map
        moleculeCountPerType = new HashMap<>();
        moleculeCountPerType.put(EntityType.ALPHA, config.getNumAlphaMolecules());
        moleculeCountPerType.put(EntityType.BETA, config.getNumBetaMolecules());
        moleculeCountPerType.put(EntityType.GAMMA, config.getNumGammaMolecules());
        moleculeCountPerType.put(EntityType.SIGMA, config.getNumSigmaMolecules());
        for (int count : moleculeCountPerType.values()) totalMoleculeCount += count;
        //fill blocker count map
        blockerCountPerType = new HashMap<>();
        blockerCountPerType.put(EntityType.ALPHA, config.getNumAlphaBlockers());
        blockerCountPerType.put(EntityType.BETA, config.getNumBetaBlockers());
        blockerCountPerType.put(EntityType.GAMMA, config.getNumGammaBlockers());
        blockerCountPerType.put(EntityType.SIGMA, config.getNumSigmaBlockers());
        for (int count : blockerCountPerType.values()) totalBlockerCount += count;
        //fill power-up count map
        powerUpCountPerType = new HashMap<>();
        powerUpCountPerType.put(EntityType.ALPHA, config.getNumAlphaPowerups());
        powerUpCountPerType.put(EntityType.BETA, config.getNumBetaPowerups());
        powerUpCountPerType.put(EntityType.GAMMA, config.getNumGammaPowerups());
        powerUpCountPerType.put(EntityType.SIGMA, config.getNumSigmaPowerups());
        for (int count : powerUpCountPerType.values()) totalPowerUpCount += count;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                latch.await(); // if the game is paused, this latch clogs this runnable.
                if ((totalBlockerCount + totalMoleculeCount + totalPowerUpCount) == 0) {
                    logger.info("[EntityGeneratorRunnable] OUT OF ENTITIES TO DROP");
                    runningMode.endGame();
                }
                AutonomousEntity entity = null;
                while (entity == null) {
                    int choice = random.nextInt(3);
                    entity = GetRandomEntity(choice);
                }
                this.runningMode.addEntity(entity);
                //sleep before adding new objects
                Thread.sleep(config.getDropRate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private AutonomousEntity GetRandomEntity(int choice) {
        switch (choice) {
            case 0:
                return generateMolecule();
            case 1:
                return generateBlocker();
            case 2:
                return generatePowerup();
            default:
                return null;
        }
    }

    /**
     * generates a random Blocker to be thrown in the space
     *
     * @return a Blocker of a random type
     */
    public Blocker generateBlocker() {
        // (MOAYED) TODO: if we agreed on this refactoring of blocker, change the rest accordingly
        List<Integer> randomTypes = Stream.of(1, 2, 3, 4)
                .filter(c -> powerUpCountPerType.get(EntityType.forValue(c)) > 0)
                .collect(Collectors.toList());
        Collections.shuffle(randomTypes);

        if (randomTypes.isEmpty())
            return null; //no more blockers

        double l = GameConstants.BLOCKER_DIAMETER * config.getUnitL() / 2.0;
        double r = config.getGamePanelDimensions().getWidth() - GameConstants.BLOCKER_DIAMETER * config.getUnitL() / 2.0;
        double x_coord = l + Math.random() * (r - l);
        logger.info("[ObjectGeneratorRunnable] generating a blocker at coordinates " + new Coordinates(x_coord, 0) + " ]");
        Blocker blocker = BlockerFactory.getInstance().getBlocker(EntityType.forValue(randomTypes.get(0)));
        blockerCountPerType.replace(blocker.getType(), blockerCountPerType.get(blocker.getType()) - 1);
        blocker.setCoordinates(new Coordinates(x_coord, 0));
        return blocker;
    }

    /**
     * generates a random powerup to be thrown in the space
     *
     * @return a Powerup of a random type
     */
    public Powerup generatePowerup() {
        if (totalPowerUpCount < 1)
            return null; //no more power-up

        double l = GameConstants.POWERUP_RADIUS * config.getUnitL();
        double r = config.getGamePanelDimensions().getWidth() - GameConstants.POWERUP_RADIUS * config.getUnitL();
        double x_coord = l + Math.random() * (r - l);
        logger.info("[ObjectGenerator: generating a powerup at coordinates " + new Coordinates(x_coord, 0) + " ]");
        Powerup powerup = null;
        while (powerup == null) {
            EntityType type = EntityType.forValue(random.nextInt(4) + 1);
            if (powerUpCountPerType.get(type) > 0)
                powerup = PowerupFactory.getInstance().getPowerup(type);
        }
        totalPowerUpCount--;
        int remaining = powerUpCountPerType.get(powerup.getType());
        powerUpCountPerType.replace(powerup.getType(), remaining - 1);
        powerup.setCoordinates(new Coordinates(x_coord, 0));
        return powerup;
    }

    /**
     * generates a random Molecule to be thrown in the space
     *
     * @return a Molecule of a random type
     */
    public Molecule generateMolecule() {
        if (totalMoleculeCount < 1)
            return null; //no more molecule

        double l = GameConstants.MOLECULE_RADIUS * config.getUnitL();
        double r = config.getGamePanelDimensions().getWidth() - GameConstants.MOLECULE_RADIUS * config.getUnitL();
        double x_coord = l + Math.random() * (r - l);
        logger.info("[ObjectGenerator: generating a molecule at coordinates " + new Coordinates(x_coord, 0) + " ]");
        Molecule molecule = null;
        while (molecule == null) {
            EntityType type = EntityType.forValue(random.nextInt(4) + 1);
            if (moleculeCountPerType.get(type) > 0)
                molecule = MoleculeFactory.getInstance().getMolecule(type);
        }
        totalMoleculeCount--;
        int remaining = moleculeCountPerType.get(molecule.getType());
        moleculeCountPerType.replace(molecule.getType(), remaining - 1);
        molecule.setCoordinates(new Coordinates(x_coord, 0));
        return molecule;
    }
}
