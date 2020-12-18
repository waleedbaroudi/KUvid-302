package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.game_building.ConfigBundle;

import java.io.File;
import java.io.IOException;

public class IOHandler {

    public static void writeConfigToYAML(ConfigBundle bundle) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(System.getProperty("user.dir") + "/configurations/config1.yaml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
