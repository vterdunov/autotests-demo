package com.autotests.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + CONFIG_FILE, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }
}
