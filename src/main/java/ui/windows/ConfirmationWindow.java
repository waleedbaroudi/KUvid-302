package main.java.ui.windows;


import main.java.model.game_building.BuildingMode;

import javax.swing.*;

public class ConfirmationWindow implements BuildingMode.ParametersConfirmationListener {


    public ConfirmationWindow(JFrame frame){
        int replay = JOptionPane.showConfirmDialog(frame, "Are you sure?" ,"Confirmation", JOptionPane.YES_NO_OPTION);
        if(replay == JOptionPane.YES_OPTION)
            onConfirmedParameters();
        else
            System.out.println("Returning to building window");

    }

    @Override
    public void onConfirmedParameters() {

    }
}
