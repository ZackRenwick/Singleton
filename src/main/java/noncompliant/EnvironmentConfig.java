package main.java.noncompliant;

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

    // this method is not synchronised so there is an issue if multiple threads come through
    // our singleton could be instantiated multiple times
    public static EnvironmentConfig getInstance() {
        if (instance == null) {
            instance = new EnvironmentConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
