package edu.aitu.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String user;
    private static String password;




    static {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);


            url = props.getProperty("DB_URL");
            user = props.getProperty("DB_USER");
            password = props.getProperty("DB_PASSWORD");
            Class.forName("org.postgresql.Driver");

            if (url == null || user == null || password == null) {
                throw new RuntimeException("Ошибка: Один из параметров (URL, USER, PASSWORD) не найден в config.properties!");
            }
        } catch (Exception e) {
            throw new RuntimeException("CRITICAL ERROR", e);
        }
    }

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
