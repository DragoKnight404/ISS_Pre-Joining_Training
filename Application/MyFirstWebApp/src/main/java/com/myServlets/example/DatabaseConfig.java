package com.myServlets.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfig {

    // The Pool Object
    private static HikariDataSource dataSource;

    // Static block runs once when the class is loaded
    static {
        try {
            HikariConfig config = new HikariConfig();

            // Database Credentials (Match your Docker setup)
            config.setJdbcUrl("jdbc:mysql://localhost:3306/user_auth");
            config.setUsername("root");
            config.setPassword("root");

            // Pool Settings
            config.setMaximumPoolSize(10); // Max 10 connections active at once
            config.setMinimumIdle(2);      // Always keep 2 ready
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            dataSource = new HikariDataSource(config);
            System.out.println("Database Connection Pool Initialized Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // This method is what the Servlet will call to get a connection
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}