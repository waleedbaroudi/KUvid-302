package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.game_building.ConfigBundle;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class IOHandler {

    public static void writeConfigToYAML(ConfigBundle bundle, String fileName) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy--HH-mm");
        Date currentData = new Date();
        System.out.println(formatter.format(currentData));
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(System.getProperty("user.dir") + "/configurations/" + fileName + "-" + formatter.format(currentData) + ".yaml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the names of the files inside the configuration directory
     *
     * @return a list of strings containing the names, potentially null
     */
    public static String[] getConfigFiles() {
        File configDirectory = new File(System.getProperty("user.dir") + "/configurations/");
        return configDirectory.list();
    }

    /**
     * Returns a ConfigBundle that is loaded from the YAML configuration file with the given name
     *
     * @param fileName
     * @return a new configBundle read from the YAML file
     */
    public static ConfigBundle readConfigFromYaml(String fileName) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            return mapper.readValue(new File(System.getProperty("user.dir") + "/configurations/" + fileName + ".yaml"), ConfigBundle.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
