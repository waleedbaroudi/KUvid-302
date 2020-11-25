package model.game_building;

import java.util.ArrayList;

/**
 * a container for game parameters
 */
public class ConfigBundle {

	private int  numOfBlockersPerType, numOfPowerUpsPerType, numOfMoleculesPerType;
	private double l;
	private boolean isLinearAlpha, isLinearBeta, isSpinningAlpha, isSpinningBeta; // TODO: convert to enums
	private int difficulty; // 0, 1, 2 for easy, normal, difficult, respectively. TODO: convert to enum
	private ArrayList<Integer> atoms;

	/**
	 * Constructor to initialize game parameter attributes
	 * 
	 * @param atoms    /
	 * @param numOfBlockersPerType /
	 * @param numOfPowerUpsPerType /
	 * @param numOfMoleculesPerType /
	 * @param l                    /
	 * @param isLinearAlpha        /
	 * @param isLinearBeta         /
	 * @param isSpinningAlpha      /
	 * @param isSpinningBeta       /
	 * @param difficulty           /
	 */
	public ConfigBundle(ArrayList<Integer> atoms, int numOfBlockersPerType, int numOfPowerUpsPerType,
			int numOfMoleculesPerType, double l, boolean isLinearAlpha, boolean isLinearBeta, boolean isSpinningAlpha,
			boolean isSpinningBeta, int difficulty) {
		this.atoms = atoms;
		this.numOfBlockersPerType = numOfBlockersPerType;
		this.numOfPowerUpsPerType = numOfPowerUpsPerType;
		this.numOfMoleculesPerType = numOfMoleculesPerType;
		this.l = l;
		this.isLinearAlpha = isLinearAlpha;
		this.isLinearBeta = isLinearBeta;
		this.isSpinningAlpha = isSpinningAlpha;
		this.isSpinningBeta = isSpinningBeta;
		this.difficulty = difficulty;
	}

	public int getNumOfAlphaAtoms() {
		return atoms.get(0);
	}

	public int getNumOfBetaAtoms() {
		return atoms.get(1);
	}

	public int getNumOfGammaAtoms() {
		return atoms.get(2);
	}

	public int getNumOfSigmaAtoms() {
		return atoms.get(3);
	}

	public int getNumOfBlockersPerType() {
		return numOfBlockersPerType;
	}

	public int getNumOfPowerUpsPerType() {
		return numOfPowerUpsPerType;
	}

	public int getNumOfMoleculesPerType() {
		return numOfMoleculesPerType;
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
