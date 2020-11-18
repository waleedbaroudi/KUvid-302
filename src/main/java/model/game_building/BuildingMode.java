package main.java.model.game_building;



public class BuildingMode {
	
	int numberOfMovables;
	double length;
	int difficylty;
	ConfigBundle configBundle;
	
	
	public BuildingMode(ConfigBundle configBundle){
		
	}
	
	
	public boolean isValidNumber(int number) {
		
		return number > 0;
		
	}
	
	public boolean isValidDifficulty(int difficaulty) {
		
		return difficaulty >= 0 && difficaulty <= 2;
	}
	
	public boolean isValidLength(double l) {
		
		return l > 0;
		
	}

}


