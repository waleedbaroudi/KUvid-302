package model.game_space;

import model.game_entities.*;
import model.game_entities.enums.BlockerType;
import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.MoleculeType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.CircularHitbox;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.hitbox.RectangularHitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_physics.path_patterns.StraightPattern;
import model.game_physics.path_patterns.ZigzagPatten;
import model.game_running.GameConstants;
import model.game_running.RunningMode;
import model.game_running.runnables.GameRunnable;
import org.apache.log4j.Logger;
import utils.Coordinates;
import utils.Velocity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * responsible for creating blockers, and powerups, molecules in the game space
 */
public class ObjectGenerator extends GameRunnable {
    private Map<Map<MoleculeType, MoleculeStructure>, Integer> numberOfMolecules;
    private Map<BlockerType, Integer> numberOfBlockers;
    private Map<PowerupType, Integer> numberOfPowerup;
    private RunningMode runningMode;

    private static Random random = new Random();
    private static Logger logger = Logger.getLogger(ObjectGenerator.class.getName());

    // TODO: obtain default arguments in a better way
    PathPattern defaultBlockerPath = new StraightPattern(new Velocity(0, 2));
    PathPattern defaultPowerupPath = new ZigzagPatten(new Velocity(3, 3), 30);
    PathPattern defaultMoleculePath = new ZigzagPatten(new Velocity(2, 2), new Velocity(-1, 1), 30, 30);
    Hitbox defaultBlockerHitbox = new CircularHitbox(GameConstants.BlockerDimensions.width / 2.0);
    Hitbox defaultPowweupHitbox = new RectangularHitbox(GameConstants.PowerupDimensions.width, GameConstants.PowerupDimensions.height);
    Hitbox defaultMoleculeHitbox = new CircularHitbox(GameConstants.MoleculeDimensions.width / 2.0);

    public ObjectGenerator(RunningMode runningMode) {
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
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * generates a random Blocker to be thrown in the space
     *
     * @return a Blocker of a random type
     */
    public Blocker generateBlocker() {
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        logger.info("[ObjectGenerator: generating a blocker at coordinates " + new Coordinates(x_coord, 0) + " ]");
        return new Blocker(new Coordinates(x_coord, 0), defaultBlockerHitbox, defaultBlockerPath, BlockerType.ALPHA_B, 5, 10);
    }

    /**
     * generates a random powerup to be thrown in the space
     *
     * @return a Powerup of a random type
     */
    public Powerup generatePowerup() {
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        logger.info("[ObjectGenerator: generating a powerup at coordinates " + new Coordinates(x_coord, 0) + " ]");
        return new Powerup(new Coordinates(x_coord, 0), defaultPowweupHitbox, defaultPowerupPath, PowerupType._ALPHA_B);
    }

    /**
     * generates a random Molecule to be thrown in the space
     *
     * @return a Molecule of a random type
     */
    public Molecule generateMolecule() {
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        logger.info("[ObjectGenerator: generating a molecule at coordinates " + new Coordinates(x_coord, 0) + " ]");
        return new Molecule(new Coordinates(x_coord, 0), defaultMoleculeHitbox, defaultMoleculePath, MoleculeType.ALPHA_, MoleculeStructure.CIRCULAR);
    }
}
