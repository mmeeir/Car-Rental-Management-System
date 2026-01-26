package edu.aitu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.fxiqwoctcbhjnrcmiwye";
    private static final String PASSWORD = "OOP_IT-2511";

    private DataBaseConnection(){

    }
    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found. Add the JAR file to your libraries!");
        }
        return DriverManager.getConnection("jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require", "postgres.fxiqwoctcbhjnrcmiwye", "OOP_IT-2511");
    }
}
