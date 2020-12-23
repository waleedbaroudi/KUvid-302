package model.game_building;

import utils.IOHandler;

public class ConfigPreset {
    PresetSelectionListener listener;

    public ConfigPreset(PresetSelectionListener listener) {
        this.listener = listener;
    }


    public void getConfigBundleFromFile(String filename) {
        ConfigBundle bundle = IOHandler.readConfigFromYaml(filename);
        if (bundle == null)
            listener.onNoPresetsFound("could not load preset configurations!");
        else
            listener.onSelectedPreset(bundle);
    }


    public interface PresetSelectionListener {
        /**
         * this method is called after game parameters get checked and proved valid.
         */
        void onSelectedPreset(ConfigBundle bundle);

        /**
         * if the user has no presets saved.
         */
        void onNoPresetsFound(String message);
    }
}
