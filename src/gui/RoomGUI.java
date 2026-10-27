package gui;

import model.Room;
import service.HotelSystem;
import service.RoomService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomGUI extends JFrame {

    private final RoomService roomService;
    private final JTextArea roomArea;

    public RoomGUI() {

        roomService = HotelSystem.getRoomService();

        setTitle("Hotel Reservation System - Rooms");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel title =
                new JLabel("Room Management");

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        mainPanel.add(title, BorderLayout.NORTH);

        roomArea = new JTextArea();

        roomArea.setFont(
                new Font("Monospaced", Font.PLAIN, 15)
        );

        roomArea.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(roomArea);

        mainPanel.add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel buttons = new JPanel();

        JButton all =
                new JButton("All Rooms");

        JButton standard =
                new JButton("Standard");

        JButton deluxe =
                new JButton("Deluxe");

        JButton suite =
                new JButton("Suite");

        JButton refresh =
                new JButton("Refresh");

        buttons.add(all);
        buttons.add(standard);
        buttons.add(deluxe);
        buttons.add(suite);
        buttons.add(refresh);

        mainPanel.add(
                buttons,
                BorderLayout.SOUTH
        );

        all.addActionListener(e ->
                showAllRooms()
        );

        standard.addActionListener(e ->
                showCategory("Standard")
        );

        deluxe.addActionListener(e ->
                showCategory("Deluxe")
        );

        suite.addActionListener(e ->
                showCategory("Suite")
        );

        refresh.addActionListener(e ->
                showAllRooms()
        );

        add(mainPanel);

        showAllRooms();
    }

    private void showAllRooms() {

        roomArea.setText("");

        List<Room> rooms =
                roomService.getAllRooms();

        roomArea.append(
                "ROOM NO.     CATEGORY       PRICE       STATUS\n"
        );

        roomArea.append(
                "------------------------------------------------------\n"
        );

        for (Room room : rooms) {

            roomArea.append(
                    String.format(
                            "%-12d %-14s ₹%-10.2f %s%n",
                            room.getRoomNumber(),
                            room.getCategory(),
                            room.getPrice(),
                            room.isAvailable()
                                    ? "Available"
                                    : "Booked"
                    )
            );
        }
    }

    private void showCategory(String category) {

        roomArea.setText("");

        List<Room> rooms =
                roomService.searchByCategory(category);

        roomArea.append(
                "AVAILABLE "
                        + category.toUpperCase()
                        + " ROOMS\n"
        );

        roomArea.append(
                "------------------------------------------------------\n"
        );

        if (rooms.isEmpty()) {

            roomArea.append(
                    "No rooms available.\n"
            );

            return;
        }

        for (Room room : rooms) {

            roomArea.append(
                    "Room: "
                            + room.getRoomNumber()
                            + " | Price: ₹"
                            + room.getPrice()
                            + " | Available\n"
            );
        }
    }
}