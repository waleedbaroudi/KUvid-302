package ui.windows;

import com.sun.javaws.util.JfxHelper;

import javax.swing.*;

public class ConfirmationWindow{


    public ConfirmationWindow(JFrame frame){
        int replay = JOptionPane.showConfirmDialog(frame, "Are you sure?" ,"Confirmation", JOptionPane.YES_NO_OPTION);
        if(replay == JOptionPane.YES_OPTION)
            // Create the game window
            System.out.println("Starting Game...");
        else
            System.out.println("Returning to building window");

    }
}
