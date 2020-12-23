package model.game_building;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * a container for game parameters
 */
public class ConfigBundle {

    private int numOfBlockersPerType, numOfPowerUpsPerType, numOfMoleculesPerType;
    private double l;
    private boolean linearAlpha, linearBeta, spinningAlpha, spinningBeta; // TODO: convert to enums
    private int difficulty; // 0, 1, 2 for easy, normal, difficult, respectively. TODO: convert to enum
    private ArrayList<Integer> atoms, powerups, molecules, blockers;

    /**
     * Constructor to initialize game parameter attributes
     *
     * @param atoms
     * @param powerups
     * @param blockers
     * @param molecules
     * @param l
     * @param linearAlpha
     * @param linearBeta
     * @param spinningAlpha
     * @param spinningBeta
     * @param difficulty
     */
    public ConfigBundle(ArrayList<Integer> atoms, ArrayList<Integer> powerups, ArrayList<Integer> blockers, ArrayList<Integer> molecules, double l, boolean linearAlpha, boolean linearBeta, boolean spinningAlpha,
                        boolean spinningBeta, int difficulty) {
        this.atoms = atoms;
        this.powerups = powerups;
        this.blockers = blockers;
        this.molecules = molecules;
        this.numOfBlockersPerType = numOfBlockersPerType;
        this.numOfPowerUpsPerType = numOfPowerUpsPerType;
        this.numOfMoleculesPerType = numOfMoleculesPerType;
        this.l = l;
        this.linearAlpha = linearAlpha;
        this.linearBeta = linearBeta;
        this.spinningAlpha = spinningAlpha;
        this.spinningBeta = spinningBeta;
        this.difficulty = difficulty;
    }

    public ConfigBundle() {

    }

    public ArrayList<Integer> getAtoms() {
        return atoms;
    }

    public ArrayList<Integer> getPowerups() {
        return powerups;
    }

    public ArrayList<Integer> getMolecules() {
        return molecules;
    }

    public ArrayList<Integer> getBlockers() {
        return blockers;
    }

    /*
    Adding @JsonIgnore annotation to some of the methods below so that numbers of individual elements are ignored
    while writing and reading YAML files, as arrays of these numbers will be read/written under there corresponding
    type.
     */

    @JsonIgnore
    public int getNumOfAlphaAtoms() {
        return atoms.get(0);
    }

    @JsonIgnore
    public int getNumOfBetaAtoms() {
        return atoms.get(1);
    }

    @JsonIgnore
    public int getNumOfGammaAtoms() {
        return atoms.get(2);
    }

    @JsonIgnore
    public int getNumOfSigmaAtoms() {
        return atoms.get(3);
    }

    @JsonIgnore
    public int getNumOfAlphaPowerups() {
        return powerups.get(0);
    }

    @JsonIgnore
    public int getNumOfBetaPowerups() {
        return powerups.get(1);
    }

    @JsonIgnore
    public int getNumOfGammaPowerups() {
        return powerups.get(2);
    }

    @JsonIgnore
    public int getNumOfSigmaPowerups() {
        return powerups.get(3);
    }

    @JsonIgnore
    public int getNumOfAlphaBlockers() {
        return blockers.get(0);
    }

    @JsonIgnore
    public int getNumOfBetaBlockers() {
        return blockers.get(1);
    }

    @JsonIgnore
    public int getNumOfGammaBlockers() {
        return blockers.get(2);
    }

    @JsonIgnore
    public int getNumOfSigmaBlockers() {
        return blockers.get(3);
    }

    @JsonIgnore
    public int getNumOfAlphaMolecules() {
        return molecules.get(0);
    }

    @JsonIgnore
    public int getNumOfBetaMolecules() {
        return molecules.get(1);
    }

    @JsonIgnore
    public int getNumOfGammaMolecules() {
        return molecules.get(2);
    }

    @JsonIgnore
    public int getNumOfSigmaMolecules() {
        return molecules.get(3);
    }

    public double getL() {
        return l;
    }

    public boolean isLinearAlpha() {
        return linearAlpha;
    }

    public boolean isLinearBeta() {
        return linearBeta;
    }

    public boolean isSpinningAlpha() {
        return spinningAlpha;
    }

    public boolean isSpinningBeta() {
        return spinningBeta;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
