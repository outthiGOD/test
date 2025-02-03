package com.example.history.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // URL для подключения к базе данных PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123321";
    private static final String SCHEMA = "thirst";

    private static Connection connection;

    // Метод для получения соединения
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Соединение с базой данных установлено.");

                // Установить схему после подключения
                try (Statement statement = connection.createStatement()) {
                    statement.execute("SET search_path TO " + SCHEMA);
                    System.out.println("Схема установлена: " + SCHEMA);
                } catch (SQLException e) {
                    System.err.println("Ошибка при установке схемы: " + e.getMessage());
                    throw e;
                }

            } catch (SQLException e) {
                System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    // Метод для закрытия соединения
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }
}