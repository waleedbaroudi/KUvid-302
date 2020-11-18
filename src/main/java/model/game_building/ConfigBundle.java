package model.game_building;

public class ConfigBundle {
    int numOfAtomsPerType, numOfBlockersPerType, numOfPowerUpsPerType, numOfMoleculePerType;
    double l;
    boolean isLinearAlpha,isLinearBeta , isSpinningAlpha, isSpinningBeta ; //TODO: convert to enums
    int difficulty; //0, 1, 2 for easy, normal, difficult, respectively. TODO: convert to enum

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
}
