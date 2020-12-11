package model.game_running;

import model.game_entities.Atom;
import model.game_entities.Powerup;
import model.game_entities.enums.EntityType;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPatternFactory;
import utils.Coordinates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * this class will keep information about the current amount of projectiles remaining as well as provide a projectile
 * on demand.
 */
public class ProjectileContainer {

    //    private final HashMap<EntityType, Integer> atomMap; // keeps the number of remaining atoms per type.
    private final HashMap<EntityType, Integer> powerUpMap; // keeps the number of power-ups per type.

    private final int[] atomMap;
    int totalAtomCount;

    Random random;

    public ProjectileContainer(int numOfAlphaAtoms, int numOfBetaAtoms, int numOfSigmaAtoms, int numOfGammaAtoms, int numOfAlphaPowerUps, int numOfBetaPowerUps, int numOfSigmaPowerUps, int numOfGammaPowerUps) {
        atomMap = new int[4];
        atomMap[0] = numOfAlphaAtoms;
        atomMap[1] = numOfBetaAtoms;
        atomMap[2] = numOfGammaAtoms;
        atomMap[3] = numOfSigmaAtoms;
        System.out.println(Arrays.toString(atomMap));
        totalAtomCount = numOfAlphaAtoms + numOfBetaAtoms + numOfGammaAtoms + numOfSigmaAtoms;

        powerUpMap = new HashMap<>(); //todo make this an array too
        powerUpMap.put(EntityType.ALPHA, numOfAlphaPowerUps);
        powerUpMap.put(EntityType.BETA, numOfBetaPowerUps);
        powerUpMap.put(EntityType.GAMMA, numOfGammaPowerUps);
        powerUpMap.put(EntityType.SIGMA, numOfSigmaPowerUps);

        random = new Random();
    }

    /**
     * if there are remaining atoms of the desired type, creates the desired atom object and decrements the number
     * of remaining atoms of the created type. returns null if there are no remaining atoms of the desired type
     *
     * @param coordinates: the coordinates of the desired atom
     * @param type:        the type of desired atom
     * @return the desired atom if there are remaining atoms of that type. null otherwise.
     */
    public Atom getAtom(Coordinates coordinates, int type) {
        System.out.println(EntityType.forValue(type + 1));
        if (checkAndChange(atomMap, type, -1))
            return new Atom(coordinates, HitboxFactory.getInstance().getAtomHitbox(), PathPatternFactory.getInstance().getAtomPathPattern(), EntityType.forValue(type + 1)); //TODO: FIX IMMEDIATELY
        return null;
    }

    public Atom getRandomAtom(Coordinates coordinates) { //needs some refactoring
        if (totalAtomCount == 0)
            return null; //out of atoms

        Atom atom = null;
        while (atom == null) {
            int atomType = random.nextInt(4);
            atom = getAtom(coordinates, atomType);
        }
        return atom;
    }

    /**
     * if there are remaining power-ups of the desired type, creates the desired power-up object and decrements the number
     * of remaining power-ups of the created type. returns null if there are no remaining power-ups of the desired type
     *
     * @param coordinates: the coordinates of the desired power-up
     * @param type:        the type of desired power-up
     * @return the desired power-up if there are remaining power-ups of that type. null otherwise.
     */
    public Powerup getPowerUp(Coordinates coordinates, EntityType type) { //todo: to be implemented
//        if (checkAndChange(powerUpMap, type, -1))
//            return new Powerup(coordinates, HitboxFactory.getInstance().getPowerUpHitbox(), PathPatternFactory.getInstance().getPowerUpPathPattern(), type);
        return null;
    }

    /**
     * used in blending/breaking. it reduces the amount of the given type by the given amount.
     *
     * @param type  type to be reduced
     * @param count amount of reduction
     * @return returns whether the decrease was successful (player is not out of atoms)
     */
    public boolean decreaseAtoms(int type, int count) { //todo: make this method take an enum type instead of an int
        return checkAndChange(atomMap, type - 1, -count);
    }


    /**
     * used in blending/breaking. it increases the amount of the given type by the given amount.
     *
     * @param type  type to be increased
     * @param count the amount of increase
     * @return returns whether the decrease was successful (purpose TBD)
     */
    public boolean increaseAtoms(int type, int count) { //todo: make this method take an enum type instead of an int
        return checkAndChange(atomMap, type - 1, count);
    }

    /**
     * checks the number of remaining items of a given type in a given map.
     * if the number is greater than zero, it decrements it and returns true (indicating successful decrement).
     * returns false otherwise (indicating there are no remaining items of the given type).
     *
     * @param map   the map in which the check will happen
     * @param type  the type for which the check will happen
     * @param count the amount of change. a negative number decreases the count of the given the type, a positive
     *              number increases it
     * @return the result of the check and decrement.
     */
    private boolean checkAndChange(int[] map, int type, int count) {
        System.out.println(type + " : " + count);
        int remaining = map[type];
        if (remaining < -count)
            return false;
        map[type] = remaining + count;
        totalAtomCount += count;
        return true;
    }

    @Override
    public String toString() {
        return "ProjectileContainer{" +
                "atomMap=" + Arrays.toString(atomMap) +
                '}';
    }
}
