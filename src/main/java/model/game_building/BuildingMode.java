package model.game_building;


public class BuildingMode {

	private boolean isValidParameters;
	private ParametersValidationListener listener;

	public BuildingMode(ParametersValidationListener listener){
		isValidParameters = true;
		this.listener = listener;
	}

	/**
	 * this method is to check if all the parameters of the object ConfigBundle are valid or not at the same time. 
	 * @return true if the parameters are valid, or false otherwise
	 */
	public void validateParameters(ConfigBundle bundle) {
		String invalidFields = "";
		if(!isValidNumber(bundle.getNumOfPowerUpsPerType()))
			invalidFields+= "Number of Power-ups per type\n";
		if(!isValidNumber(bundle.getNumOfAtomsPerType()))
			invalidFields+= "Number of Atoms per type\n";
		if(!isValidNumber(bundle.getNumOfBlockersPerType()))
			invalidFields+= "Number of Blockers per type\n";
		if(!isValidNumber(bundle.getNumOfMoleculePerType()))
			invalidFields+= "Number of Molecule per type\n";
		if(!isValidDifficulty(bundle.getDifficulty()))
			invalidFields+= "Invalid Difficulty number\n";
		if(!isValidLength(bundle.getL()))
			invalidFields+= "Invalid Value for L\n";
		if (isValidParameters)
			listener.onValidParameters();
		else {
			invalidFields = "== Invalid Fields Entered:\n" + invalidFields;
			listener.onInvalidParameters(invalidFields);
		}

	}
	
	
	/**
	 * this method to check if a given integer is greater than 0 to be valid according to the game's rules 
	 * @param number
	 * @return true if number > 0
	 */
	private boolean isValidNumber(int number) {
		if (number > 0){
			return true;
		}
		isValidParameters = false;
		return false;
	}
	
	
	/**
	 * this method is to check if the value of the given integer in the range [0,2].
	 * @param difficulty
	 * @return true if difficulty is in range [0,2].
	 */
	private boolean isValidDifficulty(int difficulty) {
		if(difficulty >= 0 && difficulty <= 2){
			return true;
		}
		isValidParameters = false;
		return false;
	}
	
	
	/**
	 * this method to check if a given integer is greater than 0 to be valid according to the game's rules 
	 * @param l
	 * @return true if l > 0.
	 */
	private boolean isValidLength(double l) {
		if (l > 0)
			return true;
		isValidParameters = false;
		return false;
	}

	public interface ParametersValidationListener{
		void onValidParameters();
		void onInvalidParameters(String message);
	}

}


