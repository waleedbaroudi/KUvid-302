package model.game_building;

public class ConfigConfirmation {
    private ParametersConfirmationListener listener;
    public ConfigConfirmation(ParametersConfirmationListener listener){
        this.listener = listener;
    }

    public void confirm(ConfigBundle bundle){
        Configuration.getInstance().setConfig(bundle);
        listener.onConfirmedParameters();

    }


    public interface ParametersConfirmationListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onConfirmedParameters();
    }
}