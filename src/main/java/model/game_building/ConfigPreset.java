package model.game_building;

import utils.IOHandler;

import java.io.IOException;

public class ConfigPreset {
    PresetSelectionListener listener;

    public ConfigPreset(PresetSelectionListener listener) {
        this.listener = listener;
    }


    public void getConfigBundleFromFile(String filename) throws IOException {
        ConfigBundle bundle = IOHandler.readConfigFromYaml(filename);
        if (bundle == null)
            listener.onPresetsFailure("could not load preset configurations!");
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
        void onPresetsFailure(String message);
    }
}
