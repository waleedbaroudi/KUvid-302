package model.game_running;

import model.game_entities.Atom;
import model.game_entities.Powerup;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPatternFactory;
import utils.Coordinates;

import java.util.HashMap;

/**
 * this class will keep information about the current amount of projectiles remaining as well as provide a projectile
 * on demand.
 */
public class ProjectileContainer {

    private final HashMap<EntityType, Integer> atomMap; // keeps the number of remaining atoms per type.
    private final HashMap<EntityType, Integer> powerUpMap; // keeps the number of power-ups per type.

    public ProjectileContainer(int numOfAlphaAtoms, int numOfBetaAtoms, int numOfSigmaAtoms, int numOfGammaAtoms, int numOfAlphaPowerUps, int numOfBetaPowerUps, int numOfSigmaPowerUps, int numOfGammaPowerUps) {
        atomMap = new HashMap<>();
        atomMap.put(EntityType.ALPHA, numOfAlphaAtoms);
        atomMap.put(EntityType.BETA, numOfBetaAtoms);
        atomMap.put(EntityType.SIGMA, numOfSigmaAtoms);
        atomMap.put(EntityType.GAMMA, numOfGammaAtoms);

        powerUpMap = new HashMap<>();
        powerUpMap.put(EntityType.ALPHA, numOfAlphaPowerUps);
        powerUpMap.put(EntityType.BETA, numOfBetaPowerUps);
        powerUpMap.put(EntityType.SIGMA, numOfSigmaPowerUps);
        powerUpMap.put(EntityType.GAMMA, numOfGammaPowerUps);
    }

    /**
     * if there are remaining atoms of the desired type, creates the desired atom object and decrements the number
     * of remaining atoms of the created type. returns null if there are no remaining atoms of the desired type
     *
     * @param coordinates: the coordinates of the desired atom
     * @param type:        the type of desired atom
     * @return the desired atom if there are remaining atoms of that type. null otherwise.
     */
    public Atom getAtom(Coordinates coordinates, EntityType type) {
        if (checkAndDecrement(atomMap, type))
            return new Atom(coordinates, HitboxFactory.getInstance().getAtomHitbox(), PathPatternFactory.getInstance().getAtomPathPattern(), type);
        return null;
    }

    /**
     * if there are remaining power-ups of the desired type, creates the desired power-up object and decrements the number
     * of remaining power-ups of the created type. returns null if there are no remaining power-ups of the desired type
     *
     * @param coordinates: the coordinates of the desired power-up
     * @param type:        the type of desired power-up
     * @return the desired power-up if there are remaining power-ups of that type. null otherwise.
     */
    public Powerup getPowerUp(Coordinates coordinates, EntityType type) {
        if (checkAndDecrement(powerUpMap, type))
            return new Powerup(coordinates, HitboxFactory.getInstance().getPowerUpHitbox(), PathPatternFactory.getInstance().getPowerUpPathPattern(), type);
        return null;
    }

    /**
     * checks the number of remaining items of a given type in a given map.
     * if the number is greater than zero, it decrements it and returns true (indicating successful decrement).
     * returns false otherwise (indicating there are no remaining items of the given type).
     *
     * @param map  the map in which the check will happen
     * @param type the type for which the check will happen
     * @return the result of the check and decrement.
     */
    private boolean checkAndDecrement(HashMap<EntityType, Integer> map, EntityType type) {
        int remaining = map.get(type);
        if (remaining < 1)
            return false;
        map.replace(type, remaining - 1);
        return true;
    }
}
