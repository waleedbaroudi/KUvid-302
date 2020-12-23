package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.game_building.ConfigBundle;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class IOHandler {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * writes a config to a YAML file. called when the player wants to save a configuration set.
     *
     * @param bundle   the config to be saved.
     * @param fileName the base name of the file of the saved config.
     */
    public static void writeConfigToYAML(ConfigBundle bundle, String fileName) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy--HH-mm");
        Date currentData = new Date();
        String name = fileName + "-" + formatter.format(currentData) + ".yaml";
        try {
            mapper.writeValue(new File(System.getProperty("user.dir") + "/configurations/" + name), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writes the selected config into a temporary file to be loaded in the Configuration class.
     *
     * @param bundle the temporary bundle to be written to the file.
     */
    public static void writeTempConfig(ConfigBundle bundle) {
        try {
            mapper.writeValue(new File(System.getProperty("user.dir") + "/configurations/temp.yaml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a ConfigBundle that is loaded from the YAML configuration file with the given name
     *
     * @param fileName
     * @return a new configBundle read from the YAML file
     */
    public static ConfigBundle readConfigFromYaml(String fileName) {
        try {
            return mapper.readValue(new File(System.getProperty("user.dir") + "/configurations/" + fileName + ".yaml"), ConfigBundle.class);
        } catch (IOException e) {
            return null;
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
     * makes preset file names more readable. needed when displaying preset options for the player.
     *
     * @param name preset file raw name
     * @return prettified name
     * @see model.game_building.ConfigPreset
     */
    public static String prettifyFileName(String name) {
        String[] nameComponents = name.split("-");
        return String.format("%-10s\t%d/%d/%d %d:%d",
                nameComponents[0],
                Integer.valueOf(nameComponents[1]),
                Integer.valueOf(nameComponents[2]),
                Integer.valueOf(nameComponents[3]), //skipping 4th index because it's empty
                Integer.valueOf(nameComponents[5]),
                Integer.valueOf(nameComponents[6].substring(0, 2)));
    }

    /**
     * converts a prettified file name to its raw format so that it can be recognized by the read method.
     *
     * @param prettyName prettified name
     * @return raw format file name
     */
    public static String prettyToProperFileName(String prettyName) {
        String[] nameComponents = prettyName.split("\\s+");
        String[] dateComponents = nameComponents[1].split("/");
        String[] timeComponents = nameComponents[2].split(":");
        return String.format("%s-%s-%s-%s--%s-%s", nameComponents[0],
                dateComponents[0],
                dateComponents[1],
                dateComponents[2],
                timeComponents[0],
                timeComponents[1]);
    }
}
