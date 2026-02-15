package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {

        String env = System.getProperty("env", "qa"); // default = qa
        String fileName = "config-" + env + ".properties";

        try {
            FileInputStream fis = new FileInputStream(fileName);
            properties.load(fis);
            System.out.println("Loaded config file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        String systemValue = System.getProperty(key);

        if (systemValue != null) {
            return systemValue;
        }
        return properties.getProperty(key);
    }
}
