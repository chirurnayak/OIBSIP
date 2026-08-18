package com.oibsip.reservation;

public class Main {

    public static void main(String[] args) {

        Database.initializeDatabase();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}