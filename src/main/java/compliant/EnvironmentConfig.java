package main.java.compliant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentConfig {
    
    private static EnvironmentConfig instance;
    private Properties properties;

    private EnvironmentConfig() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this method is  synchronised so there ino issue with multiple threads.
    // When the first comesd through then it will lock the getInstance method and wait until it is completed before the next tread is allowed
    public static synchronized EnvironmentConfig getInstance() {
        if (instance == null) {
            instance = new EnvironmentConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
