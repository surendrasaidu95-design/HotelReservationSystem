package service;

import model.Payment;

public class PaymentService {

    private int paymentCounter = 1000;

    public Payment processPayment(
            String bookingId,
            double amount,
            String method) {

        String paymentId =
                "P" + (++paymentCounter);

        return new Payment(
                paymentId,
                bookingId,
                amount,
                method,
                "SUCCESSFUL"
        );
    }
}