package com.oibsip.reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservationFrame extends JFrame {

    private JTextField passengerNameField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JComboBox<String> classTypeBox;
    private JTextField journeyDateField;
    private JTextField sourceField;
    private JTextField destinationField;

    public ReservationFrame() {

        setTitle("Online Train Reservation System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();

        setVisible(true);
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
                "Train Reservation System",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 24));

        mainPanel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        );

        passengerNameField = new JTextField();

        trainNumberField = new JTextField();

        trainNameField = new JTextField();
        trainNameField.setEditable(false);

        classTypeBox = new JComboBox<>(
                new String[]{
                        "Sleeper",
                        "AC 3 Tier",
                        "AC 2 Tier",
                        "AC First Class",
                        "General"
                }
        );

        journeyDateField = new JTextField();

        sourceField = new JTextField();

        destinationField = new JTextField();

        JButton bookButton = new JButton("Book Ticket");
        JButton cancelButton = new JButton("Cancel Booking");

        formPanel.add(new JLabel("Passenger Name:"));
        formPanel.add(passengerNameField);

        formPanel.add(new JLabel("Train Number:"));
        formPanel.add(trainNumberField);

        formPanel.add(new JLabel("Train Name:"));
        formPanel.add(trainNameField);

        formPanel.add(new JLabel("Class Type:"));
        formPanel.add(classTypeBox);

        formPanel.add(new JLabel("Journey Date (DD-MM-YYYY):"));
        formPanel.add(journeyDateField);

        formPanel.add(new JLabel("Source Station:"));
        formPanel.add(sourceField);

        formPanel.add(new JLabel("Destination Station:"));
        formPanel.add(destinationField);

        formPanel.add(bookButton);
        formPanel.add(cancelButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        trainNumberField.addActionListener(e -> loadTrainName());

        bookButton.addActionListener(e -> bookTicket());

        cancelButton.addActionListener(e -> {
            new CancellationFrame();
        });
    }

    private void loadTrainName() {

        String trainNumber = trainNumberField.getText().trim();

        if (trainNumber.isEmpty()) {
            trainNameField.setText("");
            return;
        }

        if (!trainNumber.matches("\\d+")) {

            trainNameField.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must contain only numbers.",
                    "Invalid Train Number",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        switch (trainNumber) {

            case "12627" ->
                    trainNameField.setText("Karnataka Express");

            case "12649" ->
                    trainNameField.setText("Sampark Kranti Express");

            case "16515" ->
                    trainNameField.setText("Karwar Express");

            case "16575" ->
                    trainNameField.setText("Gomateshwara Express");

            case "12051" ->
                    trainNameField.setText("Jan Shatabdi Express");

            default ->
                    trainNameField.setText("Special Express");
        }
    }

    private boolean validateDate(String date) {

        SimpleDateFormat format =
                new SimpleDateFormat("dd-MM-yyyy");

        format.setLenient(false);

        try {
            format.parse(date);
            return true;

        } catch (ParseException e) {
            return false;
        }
    }

    private void bookTicket() {

        String passengerName =
                passengerNameField.getText().trim();

        String trainNumberText =
                trainNumberField.getText().trim();

        String trainName =
                trainNameField.getText().trim();

        String classType =
                (String) classTypeBox.getSelectedItem();

        String journeyDate =
                journeyDateField.getText().trim();

        String source =
                sourceField.getText().trim();

        String destination =
                destinationField.getText().trim();

        // Empty field validation

        if (passengerName.isEmpty()
                || trainNumberText.isEmpty()
                || trainName.isEmpty()
                || journeyDate.isEmpty()
                || source.isEmpty()
                || destination.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Passenger name validation

        if (!passengerName.matches("[a-zA-Z ]+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passenger name should contain only letters.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Train number validation

        if (!trainNumberText.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must be numeric.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Date validation

        if (!validateDate(journeyDate)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date. Use DD-MM-YYYY.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (source.equalsIgnoreCase(destination)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Source and destination cannot be the same.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int trainNumber =
                Integer.parseInt(trainNumberText);

        String sql = """
                INSERT INTO reservations
                (passenger_name, train_number, train_name,
                 class_type, journey_date, source, destination)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                             sql,
                             java.sql.Statement.RETURN_GENERATED_KEYS
                     )) {

            ps.setString(1, passengerName);
            ps.setInt(2, trainNumber);
            ps.setString(3, trainName);
            ps.setString(4, classType);
            ps.setString(5, journeyDate);
            ps.setString(6, source);
            ps.setString(7, destination);

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {

                long pnr = keys.getLong(1);

                String message =
                        """
                        BOOKING CONFIRMED!

                        PNR Number: %d
                        Passenger: %s
                        Train: %d - %s
                        Class: %s
                        Journey Date: %s
                        From: %s
                        To: %s
                        """.formatted(
                                pnr,
                                passengerName,
                                trainNumber,
                                trainName,
                                classType,
                                journeyDate,
                                source,
                                destination
                        );

                JOptionPane.showMessageDialog(
                        this,
                        message,
                        "Booking Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error while booking ticket:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        passengerNameField.setText("");
        trainNumberField.setText("");
        trainNameField.setText("");
        journeyDateField.setText("");
        sourceField.setText("");
        destinationField.setText("");
    }
}