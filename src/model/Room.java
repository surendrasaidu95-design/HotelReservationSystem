package model;

public class Room {

    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category,
                double price, boolean available) {

        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {

        return roomNumber + " | "
                + category + " | ₹"
                + price + " | "
                + (available ? "Available" : "Booked");
    }
}