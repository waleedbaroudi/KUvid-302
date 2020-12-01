package model.game_space;

import model.game_entities.Blocker;
import model.game_entities.Entity;
import model.game_entities.Molecule;
import model.game_entities.Powerup;
import model.game_entities.enums.BlockerType;
import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.MoleculeType;
import model.game_entities.enums.PowerupType;
import model.game_physics.hitbox.CircularHitbox;
import model.game_physics.hitbox.Hitbox;
import model.game_physics.hitbox.RectangularHitbox;
import model.game_physics.path_patterns.PathPattern;
import model.game_physics.path_patterns.StraightPattern;
import model.game_running.GameConstants;
import utils.Coordinates;
import utils.Velocity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * responsible for creating blockers, and powerups, molecules in the game space
 */
public class ObjectGenerator  implements Runnable{
    private Map<Map<MoleculeType, MoleculeStructure>, Integer> numberOfMolecules;
    private Map<BlockerType, Integer> numberOfBlockers;
    private Map<PowerupType, Integer> numberOfPowerup;
    private ArrayList<Entity> spaceEntities;

    private static Random random = new Random();

    // TODO: obtain default arguments in a better way
    PathPattern defaultBlockerPath = new StraightPattern(new Velocity(0, 10));
    PathPattern defaultPowerupPath = new StraightPattern(new Velocity(0, 10));
    PathPattern defaultMoleculePath = new StraightPattern(new Velocity(0, 10));
    Hitbox defaultBlockerHitbox = new CircularHitbox(GameConstants.BlockerDimensions.width/2.0);
    Hitbox defaultPowweupHitbox = new RectangularHitbox(GameConstants.PowerupDimensions.width, GameConstants.PowerupDimensions.height);
    Hitbox defaultMoleculeHitbox = new CircularHitbox(GameConstants.MoleculeDimensions.width/2.0);



    public ObjectGenerator(ArrayList<Entity> spaceEntities) {
        this.spaceEntities = spaceEntities;
    }

    @Override
    public void run() {
        while (true){
            int choice = random.nextInt(3);
            switch (choice){
                case 0:
                    this.spaceEntities.add(generateMolecule());
                    break;
                case 1:
                    this.spaceEntities.add(generateBlocker());
                    break;
                case 2:
                    this.spaceEntities.add(generatePowerup());
                    break;
            }
            //sleep before adding new objects
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * generates a random Blocker to be thrown in the space
     * @return a Blocker of a random type
     */
    public Blocker generateBlocker(){
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        return new Blocker(new Coordinates(x_coord, 0), defaultBlockerHitbox, defaultBlockerPath, BlockerType.ALPHA_B, 5, 10 );
    }

    /**
     * generates a random powerup to be thrown in the space
     * @return a Powerup of a random type
     */
    public Powerup generatePowerup(){
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        return new Powerup(new Coordinates(x_coord, 0), defaultPowweupHitbox, defaultPowerupPath, PowerupType._ALPHA_B);
    }

    /**
     * generates a random Molecule to be thrown in the space
     * @return a Molecule of a random type
     */
    public Molecule generateMolecule(){
        double x_coord = Math.random() * GameConstants.BUILDING_WINDOW_SIZE.width;
        return  new Molecule(new Coordinates(x_coord, 0), defaultMoleculeHitbox, defaultMoleculePath, MoleculeType.ALPHA_, MoleculeStructure.CIRCULAR);
    }
}
