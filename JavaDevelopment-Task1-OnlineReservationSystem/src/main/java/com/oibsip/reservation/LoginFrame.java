package com.oibsip.reservation;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Train Reservation System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("Train Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        panel.add(titleLabel);
        panel.add(new JLabel());

        panel.add(usernameLabel);
        panel.add(usernameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (username.equals("admin") && password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!"
            );

            dispose();

            new ReservationFrame();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}