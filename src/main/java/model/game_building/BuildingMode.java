package main.java.model.game_building;



public class BuildingMode {
	
	int numberOfِAtomsPerType;
	int numberOfِMoleculesPerType;
	int numberOfِBlockersPerType;
	int numberOFPowerupsPerType;
	double length;
	int difficulty;
	ConfigBundle configBundle;
	
	/**
	 * // BuildingMode constructor that take a ConfigBundle instance object as parameter.
	 * @param configBundle
	 */
	public BuildingMode(ConfigBundle configBundle){
		// this.configBundle = configBundle;
		
		this.difficulty = configBundle.getDifficulty();
		this.length = configBundle.getL();
		this.numberOFPowerupsPerType = configBundle.getNumOfPowerUpsPerType();
		this.numberOfِAtomsPerType = configBundle.getNumOfAtomsPerType();
		this.numberOfِBlockersPerType = configBundle.getNumOfBlockersPerType();
		this.numberOfِMoleculesPerType = configBundle.getNumOfMoleculePerType();
		
	}
	
	
	/**
	 * this method is to check if all the parameters of the object ConfigBundle are valid or not at the same time. 
	 * @return true if the parameters are valid, or false otherwise
	 */
	public boolean validateParameters() {
		
		boolean validNumbers = isValidNumber(numberOFPowerupsPerType) && isValidNumber(numberOfِAtomsPerType)
				&& isValidNumber(numberOfِBlockersPerType) && isValidNumber(numberOfِMoleculesPerType);
		
		boolean valid = validNumbers && isValidDifficulty(difficulty) && isValidLength(length);
		
		return valid;
	}
	
	
	/**
	 * this method to check if a given integer is greater than 0 to be valid according to the game's rules 
	 * @param number
	 * @return true if number > 0
	 */
	private boolean isValidNumber(int number) {
		
		return number > 0;
		
	}
	
	
	/**
	 * this method is to check if the value of the given integer in the range [0,2].
	 * @param difficulty
	 * @return true if difficulty is in range [0,2].
	 */
	private boolean isValidDifficulty(int difficulty) {
		
		return difficulty >= 0 && difficulty <= 2;
	}
	
	
	/**
	 * this method to check if a given integer is greater than 0 to be valid according to the game's rules 
	 * @param l
	 * @return true if l > 0.
	 */
	private boolean isValidLength(double l) {
		
		return l > 0;
		
	}

}


