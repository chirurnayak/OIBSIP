package com.oibsip.reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CancellationFrame extends JFrame {

    private JTextField pnrField;

    private JTextArea bookingDetails;

    private JButton fetchButton;
    private JButton cancelButton;

    private long currentPNR = -1;

    public CancellationFrame() {

        setTitle("Cancel Train Reservation");
        setSize(550, 450);
        setLocationRelativeTo(null);

        createGUI();

        setVisible(true);
    }

    private void createGUI() {

        JPanel topPanel = new JPanel(new FlowLayout());

        JLabel pnrLabel = new JLabel("Enter PNR:");

        pnrField = new JTextField(15);

        fetchButton = new JButton("Fetch");

        topPanel.add(pnrLabel);
        topPanel.add(pnrField);
        topPanel.add(fetchButton);

        bookingDetails = new JTextArea();

        bookingDetails.setEditable(false);
        bookingDetails.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane =
                new JScrollPane(bookingDetails);

        cancelButton = new JButton("Confirm Cancellation");

        cancelButton.setEnabled(false);

        JPanel bottomPanel = new JPanel();

        bottomPanel.add(cancelButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        fetchButton.addActionListener(e -> fetchBooking());

        cancelButton.addActionListener(e -> cancelBooking());
    }

    private void fetchBooking() {

        String pnrText =
                pnrField.getText().trim();

        if (pnrText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a PNR number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!pnrText.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "PNR must contain only numbers.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        long pnr =
                Long.parseLong(pnrText);

        String sql =
                "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setLong(1, pnr);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

    currentPNR = rs.getLong("pnr");

    String details =
            "BOOKING DETAILS\n"
            + "==============================\n\n"
            + "PNR Number   : " + rs.getLong("pnr") + "\n"
            + "Passenger    : " + rs.getString("passenger_name") + "\n"
            + "Train Number : " + rs.getInt("train_number") + "\n"
            + "Train Name   : " + rs.getString("train_name") + "\n"
            + "Class        : " + rs.getString("class_type") + "\n"
            + "Journey Date : " + rs.getString("journey_date") + "\n"
            + "Source       : " + rs.getString("source") + "\n"
            + "Destination  : " + rs.getString("destination") + "\n\n"
            + "==============================";

    bookingDetails.setText(details);

    cancelButton.setEnabled(true);

} else {

                currentPNR = -1;
                cancelButton.setEnabled(false);
                bookingDetails.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "No booking found for PNR: " + pnr,
                        "Not Found",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error fetching booking:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cancelBooking() {

        if (currentPNR == -1) {
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel this booking?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM reservations WHERE pnr = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setLong(1, currentPNR);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully!"
                );

                bookingDetails.setText("");
                pnrField.setText("");

                currentPNR = -1;

                cancelButton.setEnabled(false);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cancelling booking:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}