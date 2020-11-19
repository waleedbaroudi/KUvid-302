package ui.windows;

import java.util.Scanner;

/**
 * Handles the frame that asks the user to confirm their entered parameters
 */
public class ConfirmationWindow {
    private final Scanner scanner;
    public ConfirmationWindow(){
        scanner = new Scanner(System.in);
    }

    /**
     * triggers the confirmation window
     */
    public void show(){
        System.out.println("Parameters set, enter any text to proceed");
        scanner.nextLine();
        System.out.println("Initiating Game...");
    }
}
