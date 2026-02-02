package edu.aitu.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.mrivkaqomaorywhzsntk";


    private static final String PASSWORD = loadPassword();

    private static String loadPassword() {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
            String value = props.getProperty("DB_PASSWORD");

            if (value == null || value.isBlank()) {
                throw new RuntimeException("Ошибка: DB_PASSWORD не найден в файле config.properties!");
            }
            return value;
        } catch (IOException e) {

            throw new RuntimeException("Критическая ошибка: Не удалось загрузить config.properties", e);
        }
    }

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
