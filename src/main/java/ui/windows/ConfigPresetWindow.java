package ui.windows;

import model.game_building.ConfigBundle;
import model.game_building.ConfigConfirmation;
import model.game_building.ConfigPreset;
import model.game_building.GameConstants;
import utils.IOHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ConfigPresetWindow extends JFrame implements ConfigPreset.PresetSelectionListener {
    ConfigPreset configPreset;
    JFrame buildingGameFrame;
    JList configurationFilesList;

    public ConfigPresetWindow(JFrame parent) {
        super("Preset Selection");
        this.configPreset = new ConfigPreset(this);
        this.buildingGameFrame = parent;
        this.setContentPane(new JPanel());
        boolean presetsLoaded = this.addComponents(IOHandler.getConfigFiles());
        this.pack(); // Pack the frame around the components
        this.setLocationRelativeTo(null); // Center the blender frame
        if (presetsLoaded)
            this.setVisible(true);
    }

    /**
     * adds the list of saved presets to the window. displays an error if the list is empty
     *
     * @param fileNames list of preset file names to be added to the window
     * @return true if file names were added to the list, false otherwise (no saved presets).
     */
    private boolean addComponents(String[] fileNames) {
        if (fileNames.length == 0) {//if there are no presets.
            onNoPresetsFound("No Presets Found!");
            return false;
        }
        //create list
        fileNames = Arrays.stream(fileNames).map(IOHandler::prettifyFileName).toArray(String[]::new);
        configurationFilesList = new JList(fileNames);

        JButton confirmPresetButton = new JButton("Confirm Preset");
        confirmPresetButton.addActionListener(e -> {
            String properFileName = IOHandler.prettyToProperFileName(configurationFilesList.getSelectedValue().toString());
            configPreset.getConfigBundleFromFile(properFileName);
        });
        //set a selected index
        configurationFilesList.setSelectedIndex(0);

        //add list to panel
        this.getContentPane().add(configurationFilesList);
        this.getContentPane().add(confirmPresetButton);
        return true;
    }

    @Override
    public void onSelectedPreset(ConfigBundle bundle) {
        // Load the bundle into the buildingWindow
        ((BuildingWindow) buildingGameFrame).loadPresetParameters(bundle);
        // Close the frame
        this.dispose();
    }

    @Override
    public void onNoPresetsFound(String message) {
        // Close the current game-building frame.
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        // Close the frame
        this.dispose();
    }
}
