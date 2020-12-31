package ui.windows;

import model.game_building.ConfigPreset;
import model.game_running.RunningMode;
import utils.IOHandler;

import javax.swing.*;
import java.util.List;

public class LoadWindow extends JFrame implements RunningMode.SaveLoadListener {
    private JPanel contentPane;

    public LoadWindow(){
        super("Load Session");

        getContentPane().add(contentPane);
        this.pack(); // Pack the frame around the components
        this.setLocationRelativeTo(null); // Center the blender frame
    }
    @Override
    public void showSavedSessionsWindow(List<String> sessionsList) {
        // TODO: ADD THE LIST OF SAVES TO A JLIST

        this.setVisible(true);
    }
}
