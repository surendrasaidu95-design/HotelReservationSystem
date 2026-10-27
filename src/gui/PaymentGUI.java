package gui;

import model.Payment;
import service.HotelSystem;
import service.PaymentService;
import service.ReservationService;
import model.Reservation;

import javax.swing.*;
import java.awt.*;

public class PaymentGUI extends JFrame {

    private final PaymentService paymentService;
    private final ReservationService reservationService;

    private JTextField bookingField;
    private JTextField amountField;
    private JComboBox<String> methodBox;

    public PaymentGUI() {

        paymentService =
                HotelSystem.getPaymentService();

        reservationService =
                HotelSystem.getReservationService();

        setTitle("Hotel Reservation - Payment");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50
                )
        );

        JLabel title =
                new JLabel("Payment");

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(30)
        );

        bookingField =
                addField(
                        panel,
                        "Booking ID:"
                );

        amountField =
                addField(
                        panel,
                        "Amount:"
                );

        JLabel methodLabel =
                new JLabel("Payment Method:");

        methodLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        panel.add(methodLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        methodBox =
                new JComboBox<>(
                        new String[]{
                                "UPI",
                                "Card",
                                "Cash"
                        }
                );

        methodBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        panel.add(methodBox);

        panel.add(
                Box.createVerticalStrut(25)
        );

        JButton payButton =
                new JButton("Process Payment");

        payButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(payButton);

        payButton.addActionListener(
                e -> processPayment()
        );

        add(panel);
    }

    private JTextField addField(
            JPanel panel,
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JTextField field =
                new JTextField();

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        panel.add(label);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(field);

        panel.add(
                Box.createVerticalStrut(15)
        );

        return field;
    }

    private void processPayment() {

        String bookingId =
                bookingField.getText().trim();

        String amountText =
                amountField.getText().trim();

        if (bookingId.isEmpty()
                || amountText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Booking ID and amount.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            double amount =
                    Double.parseDouble(amountText);

            Reservation reservation =
                    reservationService.findReservation(
                            bookingId
                    );

            if (reservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking ID not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String method =
                    (String) methodBox.getSelectedItem();

            Payment payment =
                    paymentService.processPayment(
                            bookingId,
                            amount,
                            method
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Payment Successful!\n\n"
                            + "Payment ID: "
                            + payment.getPaymentId()
                            + "\nBooking ID: "
                            + payment.getBookingId()
                            + "\nAmount: ₹"
                            + payment.getAmount()
                            + "\nMethod: "
                            + payment.getMethod()
                            + "\nStatus: "
                            + payment.getStatus(),
                    "Payment Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            bookingField.setText("");
            amountField.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}