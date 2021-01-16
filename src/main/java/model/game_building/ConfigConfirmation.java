package model.game_building;

import model.game_running.listeners.ParametersConfirmationListener;
import services.utils.IOHandler;

import java.io.IOException;

public class ConfigConfirmation {
    private final ParametersConfirmationListener confirmationListener;

    public ConfigConfirmation(ParametersConfirmationListener confirmationListener) {
        this.confirmationListener = confirmationListener;
    }

    public void confirm(ConfigBundle bundle, boolean isSaving) {
        //write the selected config into a temporary file that will be read in Configuration class.
        try {
            IOHandler.writeToYAML(bundle);
            if (isSaving) {

                IOHandler.writeToYAML(bundle, "preset", "configurations");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        // Send a signal to close the building-mode window (Frame)
        confirmationListener.onConfirmedParameters();

    }

}