package com.oibsip.reservation;

import java.sql.*;

public class Database {

    private static final String URL = "jdbc:sqlite:reservation.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {

        String createTable = """
                CREATE TABLE IF NOT EXISTS reservations (
                    pnr INTEGER PRIMARY KEY AUTOINCREMENT,
                    passenger_name TEXT NOT NULL,
                    train_number INTEGER NOT NULL,
                    train_name TEXT NOT NULL,
                    class_type TEXT NOT NULL,
                    journey_date TEXT NOT NULL,
                    source TEXT NOT NULL,
                    destination TEXT NOT NULL
                )
                """;

        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            stmt.execute(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}