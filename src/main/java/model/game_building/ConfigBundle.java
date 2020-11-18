package main.java.model.game_building;

public class ConfigBundle {
    private int numOfAtomsPerType, numOfBlockersPerType, numOfPowerUpsPerType, numOfMoleculePerType;
    private double l;
    private boolean isLinearAlpha,isLinearBeta , isSpinningAlpha, isSpinningBeta ; //TODO: convert to enums
    private int difficulty; //0, 1, 2 for easy, normal, difficult, respectively. TODO: convert to enum

    public ConfigBundle(int numOfAtomsPerType, int numOfBlockersPerType, int numOfPowerUpsPerType, int numOfMoleculePerType, double l, boolean isLinearAlpha, boolean isLinearBeta, boolean isSpinningAlpha, boolean isSpinningBeta, int difficulty) {
        this.numOfAtomsPerType = numOfAtomsPerType;
        this.numOfBlockersPerType = numOfBlockersPerType;
        this.numOfPowerUpsPerType = numOfPowerUpsPerType;
        this.numOfMoleculePerType = numOfMoleculePerType;
        this.l = l;
        this.isLinearAlpha = isLinearAlpha;
        this.isLinearBeta = isLinearBeta;
        this.isSpinningAlpha = isSpinningAlpha;
        this.isSpinningBeta = isSpinningBeta;
        this.difficulty = difficulty;
    }

    public int getNumOfAtomsPerType() {
        return numOfAtomsPerType;
    }

    public int getNumOfBlockersPerType() {
        return numOfBlockersPerType;
    }

    public int getNumOfPowerUpsPerType() {
        return numOfPowerUpsPerType;
    }

    public int getNumOfMoleculePerType() {
        return numOfMoleculePerType;
    }

    public double getL() {
        return l;
    }

    public boolean isLinearAlpha() {
        return isLinearAlpha;
    }

    public boolean isLinearBeta() {
        return isLinearBeta;
    }

    public boolean isSpinningAlpha() {
        return isSpinningAlpha;
    }

    public boolean isSpinningBeta() {
        return isSpinningBeta;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
