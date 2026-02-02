package edu.aitu.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FleetConfig {
    private static FleetConfig instance;
    private Properties properties;


    private FleetConfig() {
        properties = new Properties();
        try {

            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Config file not found, using defaults.");
        }
    }

    public static FleetConfig getInstance() {
        if (instance == null) {
            instance = new FleetConfig();
        }
        return instance;
    }

    public String getCompanyName() {
        return properties.getProperty("company.name", "AITU Car Rental");
    }
}
