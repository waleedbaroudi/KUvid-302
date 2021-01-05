package services.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOHandler {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static final Logger logger = Logger.getLogger(IOHandler.class.getName());

    /**
     * writes an object to a YAML file.
     *
     * @param obj   the object to be saved.
     * @param fileName the base name of the file of the saved object.
     */
    public static void writeToYAML(Object obj, String fileName) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy--HH-mm");
        Date currentData = new Date();
        String name = fileName + "-" + formatter.format(currentData) + ".yaml";
        try {
            mapper.writeValue(new File(System.getProperty("user.dir") + "/configurations/" + name), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writes the selected object into a temporary file.
     *
     * @param obj the temporary bundle to be written to the file.
     */
    public static void writeToYAML(Object obj) {
        try {
            mapper.writeValue(new File(System.getProperty("user.dir") + "/configurations/temp.yaml"), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an Object that is loaded from the YAML file with the given name
     *
     * @param fileName name of the YAML file to be read.
     * @return a new object read from the YAML file
     */
    public static <T> T readFromYaml(String fileName, Class<T> tClass) throws IOException {
        return mapper.readValue(new File(System.getProperty("user.dir") + "/configurations/" + fileName + ".yaml"), tClass);
    }

    /**
     * Return the names of the files inside the configuration directory
     *
     * @return a list of strings containing the names, potentially null
     */
    public static String[] getFilesInDirectory() {
        File directory = new File(System.getProperty("user.dir") + "/configurations/");
        return directory.list();
    }

    /**
     * makes preset file names more readable. needed when displaying preset options for the player.
     *
     * @param name preset file raw name
     * @return prettified name
     */
    public static String prettifyFileName(String name) {
        String[] nameComponents = name.split("-");
        try {
            return String.format("%-10s\t%s/%s/%s %s:%s",
                    nameComponents[0],
                    nameComponents[1],
                    nameComponents[2],
                    nameComponents[3], //skipping 4th index because it's empty
                    nameComponents[5],
                    nameComponents[6].substring(0, 2));
        } catch (IndexOutOfBoundsException e) {
            logger.warn("[IOHandler] prettifyFileName: Unrecognized name format. only removed extension (.yaml)");
            return name.replace(".yaml", "");
        }
    }

    /**
     * converts a prettified file name to its raw format so that it can be recognized by the read method.
     *
     * @param prettyName prettified name
     * @return raw format file name
     */
    public static String prettyToProperFileName(String prettyName) {
        String[] nameComponents = prettyName.split("\\s+");
        try {
            String[] dateComponents = nameComponents[1].split("/");
            String[] timeComponents = nameComponents[2].split(":");
            return String.format("%s-%s-%s-%s--%s-%s", nameComponents[0],
                    dateComponents[0],
                    dateComponents[1],
                    dateComponents[2],
                    timeComponents[0],
                    timeComponents[1]);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("[IOHandler] prettyToProperFileName: Unrecognized name format. returning without modification");
            return prettyName;
        }
    }
}
