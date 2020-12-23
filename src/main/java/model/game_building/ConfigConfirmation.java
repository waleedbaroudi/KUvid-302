package model.game_building;

import utils.IOHandler;

public class ConfigConfirmation {
    private final ParametersConfirmationListener confirmationListener;

    public ConfigConfirmation(ParametersConfirmationListener confirmationListener) {
        this.confirmationListener = confirmationListener;
    }

    public void confirm(ConfigBundle bundle) {
        //write the selected config into a temporary file that will be read in Configuration class.
        IOHandler.writeTempConfig(bundle);
        // Send a signal to close the building-mode window (Frame)
        confirmationListener.onConfirmedParameters();

    }

    public interface ParametersConfirmationListener {
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onConfirmedParameters();
    }
}