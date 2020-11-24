package model.game_building;

public class ConfigConfirmation {
    private ParametersConfirmationListener confirmationListener;
    public ConfigConfirmation(ParametersConfirmationListener confirmationListener){
        this.confirmationListener = confirmationListener;
    }

    public void confirm(ConfigBundle bundle){
        Configuration.getInstance().setConfig(bundle);
        confirmationListener.onConfirmedParameters();

    }


    public interface ParametersConfirmationListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onConfirmedParameters();
    }
}