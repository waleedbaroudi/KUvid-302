package ui.windows;

import java.util.Scanner;

public class ConfirmationWindow {
    private final Scanner scanner;
    public ConfirmationWindow(){
        scanner = new Scanner(System.in);
    }

    public void show(){
        System.out.println("Parameters set, enter any text to proceed");
        scanner.nextLine();
        System.out.println("Initiating Game...");
    }
}
