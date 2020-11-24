package main.java.ui.windows;




import com.sun.javaws.util.JfxHelper;
import model.game_building.BuildingMode;
import model.game_building.ConfigBundle;
import model.game_building.ConfigConfirmation;

import javax.swing.*;

public class ConfirmationWindow implements ConfigConfirmation.ParametersConfirmationListener {
    ConfigConfirmation configConfirmation;
    JFrame buildingGameFrame;

    public ConfirmationWindow(JFrame frame, ConfigBundle bundle){
        this.buildingGameFrame = frame;
        this.configConfirmation = new ConfigConfirmation(this);


        int replay = JOptionPane.showConfirmDialog(frame, "Are you sure?" ,"Confirmation", JOptionPane.YES_NO_OPTION);
        // TODO: View a summary of the bundle
        if(replay == JOptionPane.YES_OPTION)
            configConfirmation.confirm(bundle);
        else
            System.out.println("Returning to building window");

    }


    @Override
    public void onConfirmedParameters() {
        // Close the current game-building frame.
        buildingGameFrame.dispose();
    }
}
