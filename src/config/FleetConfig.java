package config;

import java.io.FileInputStream;
import java.util.Properties;

public class FleetConfig {

    private static FleetConfig instance;
    private String companyName;

    private FleetConfig() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/config.properties"));
            companyName = props.getProperty("company.name");
        } catch (Exception e) {
            companyName = "Default Rental Company";
        }
    }

    public static FleetConfig getInstance() {
        if (instance == null) {
            instance = new FleetConfig();
        }
        return instance;
    }

    public String getCompanyName() {
        return companyName;
    }
}
