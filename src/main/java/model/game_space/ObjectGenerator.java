package model.game_space;

import model.game_entities.Blocker;
import model.game_entities.Molecule;
import model.game_entities.Powerup;
import model.game_entities.enums.BlockerType;
import model.game_entities.enums.MoleculeStructure;
import model.game_entities.enums.MoleculeType;
import model.game_entities.enums.PowerupType;

import java.util.Map;

/**
 * responsible for creating blockers, and powerups, molecules in the game space
 */
public class ObjectGenerator {
    Map<Map<MoleculeType, MoleculeStructure>, Integer> numberOfMolecules;
    Map<BlockerType, Integer> numberOfBlockers;
    Map<PowerupType, Integer> numberOfPowerup;

    static ObjectGenerator instance;

    private ObjectGenerator() {
    }

    public static ObjectGenerator getInstance() {
        if (instance == null)
            instance = new ObjectGenerator();
        return instance;
    }

    /**
     * generates a random Blocker to be thrown in the space
     * @return a Blocker of a random type
     */
    public Blocker generateBlocker(){
        return null;
    }

    /**
     * generates a random powerup to be thrown in the space
     * @return a Powerup of a random type
     */
    public Powerup generatePowerup(){
        return null;
    }

    /**
     * generates a random Molecule to be thrown in the space
     * @return a Molecule of a random type
     */
    public Molecule generateMolecule(){
        return null;
    }
}
