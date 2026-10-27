package model;

public class Reservation {

    private String bookingId;
    private Customer customer;
    private Room room;
    private String checkIn;
    private String checkOut;
    private int guests;
    private double totalAmount;

    public Reservation(
            String bookingId,
            Customer customer,
            Room room,
            String checkIn,
            String checkOut,
            int guests,
            double totalAmount) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
        this.totalAmount = totalAmount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}