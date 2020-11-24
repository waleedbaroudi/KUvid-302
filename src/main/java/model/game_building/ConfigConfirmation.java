package model.game_building;

public class ConfigConfirmation {
    private ParametersConfirmationListener confirmationListener;
    public ConfigConfirmation(ParametersConfirmationListener confirmationListener){
        this.confirmationListener = confirmationListener;
    }

    public void confirm(ConfigBundle bundle){
        Configuration.getInstance().setConfig(bundle);
        // Send a signal to close the building-mode window (Frame)
        confirmationListener.onConfirmedParameters();

        // TODO: Create the game session

    }


    public interface ParametersConfirmationListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onConfirmedParameters();
    }
}