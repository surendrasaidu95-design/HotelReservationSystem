package model;

public class Payment {

    private String paymentId;
    private String bookingId;
    private double amount;
    private String method;
    private String status;

    public Payment(
            String paymentId,
            String bookingId,
            double amount,
            String method,
            String status) {

        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}