package ui.windows;

import model.game_building.BuildingMode;
import model.game_building.ConfigBundle;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class draws the game building window.
 * through this window, the player can specify game parameters.
 */
public class BuildingWindow implements BuildingMode.ParametersValidationListener {
    Scanner scanner;
    ConfigBundle bundle;
    BuildingMode buildingMode;

    /**
     * Constructor initiates the Scanner and BuildingMode instances
     */
    public BuildingWindow() {
        scanner = new Scanner(System.in);
        buildingMode = new BuildingMode(this);
    }

    /**
     * starts building mode UI
     */
    public void start(){
        promptForParameters();
        buildingMode.validateParameters(bundle);
        /// do other stuff
    }

    /**
     * this method handles asking for game parameters, recording them, and collecting them in bundle.
     */
    public void promptForParameters() {
        System.out.println("=== Welcome to KUvid! ===");
        System.out.println("Please specify game parameters");

        int numOfAtoms = (int) promptForNumParameter("Number of atoms:\t");
        int numOfMolecules = (int) promptForNumParameter("Number of molecules:\t");
        int numOfPowerUps = (int) promptForNumParameter("Number of power-ups:\t");
        int numOfBlockers = (int) promptForNumParameter("Number of reaction blockers:\t");

        double lengthOfL = promptForNumParameter("Length of unit L:\t");
        boolean linearAlpha = promptForBoolParameter("Linear Alpha molecules? [true/false]\t");
        boolean spinningAlpha = false;
        if(linearAlpha)
            spinningAlpha = promptForBoolParameter("Spinning Alpha molecules? [true/false]\t");

        boolean linearBeta = promptForBoolParameter("Linear Beta molecules? [true/false]\t");
        boolean spinningBeta = false;
        if(linearBeta)
            spinningBeta = promptForBoolParameter("Spinning Beta molecules? [true/false]\t");

        int difficulty = (int) promptForNumParameter("Difficulty level: [0/1/2]\t");

        bundle = new ConfigBundle(numOfAtoms, numOfBlockers, numOfPowerUps, numOfMolecules, lengthOfL, linearAlpha,linearBeta,spinningAlpha,spinningBeta,difficulty);
    }

    /**
     *
     * @param message prompt message shown to the user
     * @return returns the numeric value read from user input
     */
    public double promptForNumParameter(String message){
        System.out.print(message);
        double results = -1;
        try{
            results = scanner.nextDouble();
        } catch(InputMismatchException e){
            System.out.println("wrong type entered");
        }
        return results;
    }

    /**
     *
     * @param message prompt message shown to the user
     * @return returns the boolean value read from user input
     */
    public boolean promptForBoolParameter(String message){
        System.out.print(message);
        String result = scanner.next();
        return result.trim().toLowerCase().equals("true");
    }

    public void onValidParameters() {
        ConfirmationWindow confirmationWindow = new ConfirmationWindow();
        confirmationWindow.show();
    }

    public void onInvalidParameters(String message) {
        ErrorWindow errorWindow = new ErrorWindow(message);
        errorWindow.popError();
    }
}
