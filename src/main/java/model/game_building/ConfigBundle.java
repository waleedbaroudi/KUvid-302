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
    public int getNumAlphaPowerups() {
        return powerups.get(0);
    }

    @JsonIgnore
    public int getNumBetaPowerups() {
        return powerups.get(1);
    }

    @JsonIgnore
    public int getNumGammaPowerups() {
        return powerups.get(2);
    }

    @JsonIgnore
    public int getNumSigmaPowerups() {
        return powerups.get(3);
    }

    @JsonIgnore
    public int getNumAlphaBlockers() {
        return blockers.get(0);
    }

    @JsonIgnore
    public int getNumBetaBlockers() {
        return blockers.get(1);
    }

    @JsonIgnore
    public int getNumGammaBlockers() {
        return blockers.get(2);
    }

    @JsonIgnore
    public int getNumSigmaBlockers() {
        return blockers.get(3);
    }

    @JsonIgnore
    public int getNumAlphaMolecules() {
        return molecules.get(0);
    }

    @JsonIgnore
    public int getNumBetaMolecules() {
        return molecules.get(1);
    }

    @JsonIgnore
    public int getNumGammaMolecules() {
        return molecules.get(2);
    }

    @JsonIgnore
    public int getNumSigmaMolecules() {
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
