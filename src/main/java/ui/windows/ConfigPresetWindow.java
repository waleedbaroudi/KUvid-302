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
        this.addComponents(IOHandler.getConfigFiles());
        this.pack(); // Pack the frame around the components
        setLocationRelativeTo(null); // Center the blender frame
        this.setVisible(true);
    }

    private void addComponents(String[] fileNames) {
        //create list
        fileNames = Arrays.stream(fileNames).map(fileName -> fileName.replace(".yaml", "")).toArray(String[]::new);
        configurationFilesList = new JList(fileNames);

        JButton confirmPresetButton = new JButton("Confirm Preset");
        confirmPresetButton.addActionListener(e -> {
            configPreset.getConfigBundleFromFile(configurationFilesList.getSelectedValue().toString());
        });
        //set a selected index
        configurationFilesList.setSelectedIndex(0);

        //add list to panel
        this.getContentPane().add(configurationFilesList);
        this.getContentPane().add(confirmPresetButton);
    }

    @Override
    public void onSelectedPreset(ConfigBundle bundle) {
        // Close the current game-building frame.
        if (bundle == null)
            JOptionPane.showMessageDialog(this, "No Presets Found!", "Error", JOptionPane.ERROR_MESSAGE);
            // Load the bundle into the buildingWindow
        else
            ((BuildingWindow) buildingGameFrame).loadPresetParameters(bundle);
        // Close the frame
        this.dispose();
    }
}
