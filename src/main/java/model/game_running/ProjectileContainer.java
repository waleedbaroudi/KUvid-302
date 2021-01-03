package model.game_running;

import model.game_entities.Atom;
import model.game_entities.Powerup;
import model.game_entities.Projectile;
import model.game_entities.enums.EntityType;
import model.game_entities.enums.SuperType;
import model.game_entities.factories.AtomFactory;
import model.game_entities.shields.ShieldTuple;
import model.game_entities.shields.ShieldedAtomFactory;
import model.game_physics.hitbox.HitboxFactory;
import model.game_physics.path_patterns.PathPatternFactory;
import utils.Coordinates;

import java.util.*;

/**
 * this class will keep information about the current amount of projectiles remaining as well as provide a projectile
 * on demand.
 */
public class ProjectileContainer {

    private final int[] atomMap;
    private final int[] powerUpMap; // keeps the number of power-ups per type.
    private final HashMap<Integer, ArrayList<ShieldTuple>> shieldsMap;
    int totalAtomCount;

    private final RunningMode runningMode;

    Random random;

    public ProjectileContainer(RunningMode runningMode, int numOfAlphaAtoms, int numOfBetaAtoms, int numOfSigmaAtoms, int numOfGammaAtoms, int numOfAlphaPowerUps, int numOfBetaPowerUps, int numOfSigmaPowerUps, int numOfGammaPowerUps) {
        this.runningMode = runningMode;

        atomMap = new int[4];
        atomMap[0] = numOfAlphaAtoms;
        atomMap[1] = numOfBetaAtoms;
        atomMap[2] = numOfGammaAtoms;
        atomMap[3] = numOfSigmaAtoms;

        totalAtomCount = numOfAlphaAtoms + numOfBetaAtoms + numOfGammaAtoms + numOfSigmaAtoms;

        powerUpMap = new int[4];
        powerUpMap[0] = numOfAlphaPowerUps;
        powerUpMap[1] = numOfBetaPowerUps;
        powerUpMap[2] = numOfGammaPowerUps;
        powerUpMap[3] = numOfSigmaPowerUps;

        shieldsMap = new HashMap<>();
        shieldsMap.put(0, new ArrayList<>());
        shieldsMap.put(1, new ArrayList<>());
        shieldsMap.put(2, new ArrayList<>());
        shieldsMap.put(3, new ArrayList<>());

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
    private Atom getAtom(Coordinates coordinates, int type) {
        if (updateProjectileMap(atomMap, SuperType.ATOM, type, -1)) {
            Atom atom = AtomFactory.getInstance().getAtom((EntityType.forValue(type + 1))); //TODO: FIX indices
            if (atom != null)
                atom.setCoordinates(coordinates);
            return atom;
        }
        return null;
    }

    public Atom getRandomAtom(Coordinates coordinates) { //needs some refactoring
        if (totalAtomCount == 0)
            return null; //out of atoms
        int atomType;
        Atom atom = null;
        while (atom == null) {
            atomType = random.nextInt(4);
            atom = getAtom(coordinates, atomType);
        }
        atom = shieldAtom(atom);
        return atom;
    }

    /**
     * return either a shielded atom from the shields available, or the atom itself depending on
     * the number of shields of a type and a random boolean
     *
     * @param atom the atom to be shielded
     * @return either a shielded atom or the atom itself
     */
    private Atom shieldAtom(Atom atom) {
        EntityType type = atom.getType();
        if (shieldedAtoms(type.getValue() - 1) > 0)
            if (random.nextBoolean() || shieldedAtoms(type.getValue() - 1) == getAtomCountForType(type))
                return ShieldedAtomFactory.applyShields(getShields(type.getValue() - 1), atom);
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
        if (updateProjectileMap(powerUpMap, SuperType.POWERUP, type.getValue() - 1, -1)) // TODO: when changing the updateProjectileMap to take type as Entitytype remove .getValue() from this line
            return new Powerup(coordinates, HitboxFactory.getInstance().getPowerUpHitbox(),
                    PathPatternFactory.getInstance().getPowerUpPathPattern(),
                    EntityType.forValue(type.getValue()), false); //TODO: FIX indices
        return null;
    }

    public void addPowerUp(Powerup powerup) {
        updateProjectileMap(powerUpMap, SuperType.POWERUP, powerup.getType().getValue() - 1, 1);
    }

    /**
     * used in blending/breaking. it reduces the amount of the given type by the given amount.
     *
     * @param type  type to be reduced
     * @param count amount of reduction
     * @return returns whether the decrease was successful (player is not out of atoms)
     */
    public boolean decreaseAtoms(int type, int count) { //todo: make this method take an enum type instead of an int
        return updateProjectileMap(atomMap, SuperType.ATOM, type - 1, -count); //todo: fix index
    }


    /**
     * used in blending/breaking. it increases the amount of the given type by the given amount.
     *
     * @param type  type to be increased
     * @param count the amount of increase
     * @return returns whether the decrease was successful (purpose TBD)
     */
    public boolean increaseAtoms(int type, int count, Projectile atom) { //todo: make this method take an enum type instead of an int
        addShields(atom);
        return updateProjectileMap(atomMap, SuperType.ATOM, type - 1, count); //todo: fix index
    }

    private void addShields(Projectile atomProjectile) {
        Atom atom = (Atom) atomProjectile;
        if (atom.getShieldTuple().isNotZeros())
            shieldsMap.get(atom.getType().getValue() - 1).add(atom.getShieldTuple());
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
    private boolean updateProjectileMap(int[] map, SuperType superType, int type, int count) {
        //System.out.println(type + " : " + count); //TODO: Change to logger
        int remaining = map[type];
        if (remaining < -count)
            return false;
        map[type] = remaining + count;
        if (superType.equals(SuperType.ATOM))
            totalAtomCount += count;
        if (runningMode != null)
            runningMode.updateStatisticsProjectileCount(superType, EntityType.forValue(type + 1), map[type]);
        return true;
    }

    public int[] getAtomMap() {
        return this.atomMap;
    }

    public int getAtomCountForType(EntityType type) {
        return atomMap[type.getValue() - 1]; //todo: fix index
    }

    @Override
    public String toString() {
        return "ProjectileContainer{" +
                "atomMap=" + Arrays.toString(atomMap) +
                '}';
    }

    public ShieldTuple getShields(int type) {
        ArrayList<ShieldTuple> shieldLst = shieldsMap.get(type);
        if (shieldLst.size() > 0)
            return shieldsMap.get(type).get(random.nextInt(shieldLst.size()));
        return new ShieldTuple();
    }

    public int shieldedAtoms(int type) {
        return shieldsMap.get(type).size();
    }
}


