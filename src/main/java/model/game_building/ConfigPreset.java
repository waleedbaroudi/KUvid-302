package model.game_building;

import utils.IOHandler;

public class ConfigPreset {
    PresetSelectionListener listener;

    public ConfigPreset(PresetSelectionListener listener){
        this.listener = listener;
    }




    public void getConfigBundleFromFile(String filename){
        listener.onSelectedPreset(IOHandler.readConfigFromYaml(filename));
    }


    public interface PresetSelectionListener{
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onSelectedPreset(ConfigBundle bundle);
    }
}
