package gui;

import model.Reservation;
import model.Room;
import service.HotelSystem;
import service.ReservationService;
import service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class DashboardGUI extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color NAVY = new Color(15, 23, 42);
    private final Color NAVY_LIGHT = new Color(30, 41, 59);
    private final Color BLUE = new Color(37, 99, 235);
    private final Color GREEN = new Color(22, 163, 74);
    private final Color RED = new Color(220, 38, 38);
    private final Color ORANGE = new Color(234, 88, 12);

    private final Color BACKGROUND =
            new Color(241, 245, 249);

    private final Color WHITE = Color.WHITE;

    private final Color TEXT =
            new Color(31, 41, 55);

    private final Color MUTED =
            new Color(100, 116, 139);

    // =========================================================
    // SERVICES
    // =========================================================

    private final RoomService roomService;
    private final ReservationService reservationService;

    // =========================================================
    // LABELS FOR DYNAMIC STATISTICS
    // =========================================================

    private JLabel totalRoomsLabel;
    private JLabel availableRoomsLabel;
    private JLabel bookedRoomsLabel;
    private JLabel reservationCountLabel;

    private JPanel roomListPanel;
    private JPanel recentBookingPanel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardGUI() {

        roomService =
                HotelSystem.getRoomService();

        reservationService =
                HotelSystem.getReservationService();

        setTitle(
                "Hotel Reservation System"
        );

        setSize(
                1250,
                760
        );

        setMinimumSize(
                new Dimension(
                        1050,
                        650
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createGUI();
    }

    // =========================================================
    // CREATE GUI
    // =========================================================

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        // -----------------------------------------------------
        // SIDEBAR
        // -----------------------------------------------------

        mainPanel.add(
                createSidebar(),
                BorderLayout.WEST
        );

        // -----------------------------------------------------
        // MAIN CONTENT
        // -----------------------------------------------------

        mainPanel.add(
                createMainContent(),
                BorderLayout.CENTER
        );

        add(mainPanel);

        updateDashboard();
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(
                        230,
                        760
                )
        );

        sidebar.setBackground(
                NAVY
        );

        sidebar.setBorder(
                new EmptyBorder(
                        25,
                        15,
                        20,
                        15
                )
        );

        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        JPanel logoPanel =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        logoPanel.setOpaque(false);

        JLabel logo =
                new JLabel("🏨");

        logo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        32
                )
        );

        JPanel logoText =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        logoText.setOpaque(false);

        JLabel hotelName =
                new JLabel(
                        "GRAND HOTEL"
                );

        hotelName.setForeground(
                WHITE
        );

        hotelName.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        JLabel systemName =
                new JLabel(
                        "Reservation System"
                );

        systemName.setForeground(
                new Color(
                        148,
                        163,
                        184
                )
        );

        systemName.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        10
                )
        );

        logoText.add(hotelName);
        logoText.add(systemName);

        logoPanel.add(
                logo,
                BorderLayout.WEST
        );

        logoPanel.add(
                logoText,
                BorderLayout.CENTER
        );

        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // NAVIGATION
        // -----------------------------------------------------

        JPanel navigation =
                new JPanel();

        navigation.setLayout(
                new BoxLayout(
                        navigation,
                        BoxLayout.Y_AXIS
                )
        );

        navigation.setOpaque(false);

        navigation.setBorder(
                new EmptyBorder(
                        35,
                        0,
                        0,
                        0
                )
        );

        navigation.add(
                createMenuButton(
                        "🏠",
                        "Dashboard",
                        true,
                        () -> {
                        }
                )
        );

        navigation.add(
                Box.createVerticalStrut(8)
        );

        navigation.add(
                createMenuButton(
                        "🛏",
                        "Rooms",
                        false,
                        () -> openRooms()
                )
        );

        navigation.add(
                Box.createVerticalStrut(8)
        );

        navigation.add(
                createMenuButton(
                        "📋",
                        "New Reservation",
                        false,
                        () -> openReservation()
                )
        );

        navigation.add(
                Box.createVerticalStrut(8)
        );

        navigation.add(
                createMenuButton(
                        "💳",
                        "Payment",
                        false,
                        () -> openPayment()
                )
        );

        navigation.add(
                Box.createVerticalStrut(8)
        );

        navigation.add(
                createMenuButton(
                        "🔎",
                        "Booking Details",
                        false,
                        () -> openBookingDetails()
                )
        );

        navigation.add(
                Box.createVerticalStrut(8)
        );

        navigation.add(
                createMenuButton(
                        "❌",
                        "Cancel Booking",
                        false,
                        () -> cancelBooking()
                )
        );

        sidebar.add(
                navigation,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // BOTTOM
        // -----------------------------------------------------

        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );

        bottom.setOpaque(false);

        JButton exitButton =
                createMenuButton(
                        "🚪",
                        "Exit Application",
                        false,
                        () -> exitApplication()
                );

        bottom.add(
                exitButton,
                BorderLayout.CENTER
        );

        sidebar.add(
                bottom,
                BorderLayout.SOUTH
        );

        return sidebar;
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private JPanel createMainContent() {

        JPanel content =
                new JPanel(
                        new BorderLayout()
                );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        28,
                        25,
                        28
                )
        );

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                4
                        )
                );

        titlePanel.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                TEXT
        );

        JLabel subtitle =
                new JLabel(
                        "Welcome back! Here's what's happening at your hotel today."
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                MUTED
        );

        titlePanel.add(title);
        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        JLabel online =
                new JLabel(
                        "●  SYSTEM ONLINE"
                );

        online.setForeground(
                GREEN
        );

        online.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        header.add(
                online,
                BorderLayout.EAST
        );

        content.add(
                header,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // CENTER
        // -----------------------------------------------------

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );

        center.setOpaque(false);

        center.setBorder(
                new EmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        // Statistics
        center.add(
                createStatisticsPanel(),
                BorderLayout.NORTH
        );

        // Main lower section
        JPanel lower =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                18,
                                0
                        )
                );

        lower.setOpaque(false);

        lower.add(
                createRoomAvailabilityPanel()
        );

        lower.add(
                createRecentBookingsPanel()
        );

        center.add(
                lower,
                BorderLayout.CENTER
        );

        content.add(
                center,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // QUICK ACTIONS
        // -----------------------------------------------------

        content.add(
                createQuickActions(),
                BorderLayout.SOUTH
        );

        return content;
    }

    // =========================================================
    // STATISTICS
    // =========================================================

    private JPanel createStatisticsPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        panel.setOpaque(false);

        totalRoomsLabel =
                new JLabel("0");

        availableRoomsLabel =
                new JLabel("0");

        bookedRoomsLabel =
                new JLabel("0");

        reservationCountLabel =
                new JLabel("0");

        panel.add(
                createStatCard(
                        "🏨",
                        "Total Rooms",
                        totalRoomsLabel,
                        BLUE
                )
        );

        panel.add(
                createStatCard(
                        "✓",
                        "Available",
                        availableRoomsLabel,
                        GREEN
                )
        );

        panel.add(
                createStatCard(
                        "🔑",
                        "Occupied",
                        bookedRoomsLabel,
                        ORANGE
                )
        );

        panel.add(
                createStatCard(
                        "📋",
                        "Reservations",
                        reservationCountLabel,
                        new Color(
                                124,
                                58,
                                237
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String iconText,
            String title,
            JLabel value,
            Color accent) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                10,
                                5
                        )
                );

        card.setBackground(
                WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        226,
                                        232,
                                        240
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                15,
                                17,
                                15,
                                17
                        )
                )
        );

        JLabel icon =
                new JLabel(
                        iconText
                );

        icon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        23
                )
        );

        JPanel text =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                2
                        )
                );

        text.setOpaque(false);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                MUTED
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        value.setForeground(
                accent
        );

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        text.add(titleLabel);
        text.add(value);

        card.add(
                icon,
                BorderLayout.WEST
        );

        card.add(
                text,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ROOM AVAILABILITY
    // =========================================================

    private JPanel createRoomAvailabilityPanel() {

        JPanel outer =
                createWhitePanel();

        outer.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        JPanel heading =
                new JPanel(
                        new BorderLayout()
                );

        heading.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Room Availability"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(
                TEXT
        );

        JButton viewAll =
                smallButton(
                        "View All"
                );

        viewAll.addActionListener(
                e -> openRooms()
        );

        heading.add(
                title,
                BorderLayout.WEST
        );

        heading.add(
                viewAll,
                BorderLayout.EAST
        );

        outer.add(
                heading,
                BorderLayout.NORTH
        );

        roomListPanel =
                new JPanel();

        roomListPanel.setLayout(
                new BoxLayout(
                        roomListPanel,
                        BoxLayout.Y_AXIS
                )
        );

        roomListPanel.setOpaque(false);

        JScrollPane scroll =
                new JScrollPane(
                        roomListPanel
                );

        scroll.setBorder(null);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        outer.add(
                scroll,
                BorderLayout.CENTER
        );

        return outer;
    }

    // =========================================================
    // RECENT BOOKINGS
    // =========================================================

    private JPanel createRecentBookingsPanel() {

        JPanel outer =
                createWhitePanel();

        outer.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        JPanel heading =
                new JPanel(
                        new BorderLayout()
                );

        heading.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Recent Reservations"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(
                TEXT
        );

        JButton view =
                smallButton(
                        "View"
                );

        view.addActionListener(
                e -> openBookingDetails()
        );

        heading.add(
                title,
                BorderLayout.WEST
        );

        heading.add(
                view,
                BorderLayout.EAST
        );

        outer.add(
                heading,
                BorderLayout.NORTH
        );

        recentBookingPanel =
                new JPanel();

        recentBookingPanel.setLayout(
                new BoxLayout(
                        recentBookingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        recentBookingPanel.setOpaque(false);

        JScrollPane scroll =
                new JScrollPane(
                        recentBookingPanel
                );

        scroll.setBorder(null);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        outer.add(
                scroll,
                BorderLayout.CENTER
        );

        return outer;
    }

    // =========================================================
    // QUICK ACTIONS
    // =========================================================

    private JPanel createQuickActions() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        panel.setOpaque(false);

        panel.setBorder(
                new EmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

        JLabel label =
                new JLabel(
                        "Quick Actions:"
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT
        );

        panel.add(label);

        JButton newBooking =
                createQuickButton(
                        "+  New Reservation",
                        BLUE
                );

        JButton rooms =
                createQuickButton(
                        "🛏  Manage Rooms",
                        NAVY_LIGHT
                );

        JButton payment =
                createQuickButton(
                        "💳  Payment",
                        GREEN
                );

        newBooking.addActionListener(
                e -> openReservation()
        );

        rooms.addActionListener(
                e -> openRooms()
        );

        payment.addActionListener(
                e -> openPayment()
        );

        panel.add(newBooking);
        panel.add(rooms);
        panel.add(payment);

        return panel;
    }

    // =========================================================
    // UPDATE DASHBOARD
    // =========================================================

    private void updateDashboard() {

        List<Room> rooms =
                roomService.getAllRooms();

        int total =
                rooms.size();

        int available = 0;

        for (Room room : rooms) {

            if (room.isAvailable()) {
                available++;
            }
        }

        int booked =
                total - available;

        int reservations =
                reservationService
                        .getReservations()
                        .size();

        totalRoomsLabel.setText(
                String.valueOf(total)
        );

        availableRoomsLabel.setText(
                String.valueOf(available)
        );

        bookedRoomsLabel.setText(
                String.valueOf(booked)
        );

        reservationCountLabel.setText(
                String.valueOf(reservations)
        );

        updateRoomList();

        updateRecentBookings();
    }

    // =========================================================
    // UPDATE ROOM LIST
    // =========================================================

    private void updateRoomList() {

        roomListPanel.removeAll();

        List<Room> rooms =
                roomService.getAllRooms();

        for (Room room : rooms) {

            roomListPanel.add(
                    createRoomRow(room)
            );

            roomListPanel.add(
                    Box.createVerticalStrut(7)
            );
        }

        roomListPanel.revalidate();

        roomListPanel.repaint();
    }

    // =========================================================
    // ROOM ROW
    // =========================================================

    private JPanel createRoomRow(
            Room room) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        row.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        row.setBorder(
                new EmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );

        JLabel roomNumber =
                new JLabel(
                        "Room "
                                + room.getRoomNumber()
                );

        roomNumber.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        roomNumber.setForeground(
                TEXT
        );

        JPanel middle =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        middle.setOpaque(false);

        JLabel category =
                new JLabel(
                        room.getCategory()
                );

        category.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        category.setForeground(
                TEXT
        );

        JLabel price =
                new JLabel(
                        String.format(
                                "₹%.0f / night",
                                room.getPrice()
                        )
                );

        price.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        price.setForeground(
                MUTED
        );

        middle.add(category);
        middle.add(price);

        JLabel status =
                new JLabel(
                        room.isAvailable()
                                ? "● Available"
                                : "● Booked"
                );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        status.setForeground(
                room.isAvailable()
                        ? GREEN
                        : RED
        );

        row.add(
                roomNumber,
                BorderLayout.WEST
        );

        row.add(
                middle,
                BorderLayout.CENTER
        );

        row.add(
                status,
                BorderLayout.EAST
        );

        return row;
    }

    // =========================================================
    // UPDATE RECENT BOOKINGS
    // =========================================================

    private void updateRecentBookings() {

        recentBookingPanel.removeAll();

        List<Reservation> reservations =
                reservationService
                        .getReservations();

        if (reservations.isEmpty()) {

            JLabel empty =
                    new JLabel(
                            "No reservations yet."
                    );

            empty.setForeground(
                    MUTED
            );

            empty.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            13
                    )
            );

            recentBookingPanel.add(
                    empty
            );

        } else {

            int start =
                    Math.max(
                            0,
                            reservations.size() - 5
                    );

            for (int i =
                    reservations.size() - 1;
                 i >= start;
                 i--) {

                recentBookingPanel.add(
                        createBookingRow(
                                reservations.get(i)
                        )
                );

                recentBookingPanel.add(
                        Box.createVerticalStrut(8)
                );
            }
        }

        recentBookingPanel.revalidate();

        recentBookingPanel.repaint();
    }

    // =========================================================
    // BOOKING ROW
    // =========================================================

    private JPanel createBookingRow(
            Reservation reservation) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        row.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        row.setBorder(
                new EmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );

        JPanel left =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        left.setOpaque(false);

        JLabel name =
                new JLabel(
                        reservation
                                .getCustomer()
                                .getName()
                );

        name.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        name.setForeground(
                TEXT
        );

        JLabel booking =
                new JLabel(
                        reservation
                                .getBookingId()
                                + " • Room "
                                + reservation
                                .getRoom()
                                .getRoomNumber()
                );

        booking.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        booking.setForeground(
                MUTED
        );

        left.add(name);
        left.add(booking);

        JLabel amount =
                new JLabel(
                        String.format(
                                "₹%.0f",
                                reservation
                                        .getTotalAmount()
                        )
                );

        amount.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        amount.setForeground(
                GREEN
        );

        row.add(
                left,
                BorderLayout.CENTER
        );

        row.add(
                amount,
                BorderLayout.EAST
        );

        return row;
    }

    // =========================================================
    // WHITE PANEL
    // =========================================================

    private JPanel createWhitePanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        226,
                                        232,
                                        240
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String icon,
            String text,
            boolean selected,
            Runnable action) {

        JButton button =
                new JButton(
                        icon + "   " + text
                );

        button.setMaximumSize(
                new Dimension(
                        205,
                        45
                )
        );

        button.setPreferredSize(
                new Dimension(
                        205,
                        45
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                selected
                        ? WHITE
                        : new Color(
                                203,
                                213,
                                225
                        )
        );

        button.setBackground(
                selected
                        ? BLUE
                        : NAVY
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        15,
                        0,
                        5
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addActionListener(
                e -> action.run()
        );

        return button;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    private JButton smallButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        button.setForeground(
                BLUE
        );

        button.setBackground(
                WHITE
        );

        button.setFocusPainted(false);

        button.setBorder(
                new LineBorder(
                        new Color(
                                191,
                                219,
                                254
                        ),
                        1,
                        true
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // QUICK ACTION BUTTON
    // =========================================================

    private JButton createQuickButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // OPEN ROOMS
    // =========================================================

    private void openRooms() {

        new RoomGUI().setVisible(true);
    }

    // =========================================================
    // OPEN RESERVATION
    // =========================================================

    private void openReservation() {

        new ReservationGUI().setVisible(true);
    }

    // =========================================================
    // OPEN PAYMENT
    // =========================================================

    private void openPayment() {

        new PaymentGUI().setVisible(true);
    }

    // =========================================================
    // OPEN BOOKING DETAILS
    // =========================================================

    private void openBookingDetails() {

        new BookingDetailsGUI().setVisible(true);
    }

    // =========================================================
    // CANCEL BOOKING
    // =========================================================

    private void cancelBooking() {

        String bookingId =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Booking ID:",
                        "Cancel Reservation",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (bookingId == null ||
                bookingId.trim().isEmpty()) {

            return;
        }

        bookingId =
                bookingId.trim();

        Reservation reservation =
                reservationService
                        .findReservation(
                                bookingId
                        );

        if (reservation == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking ID not found.",
                    "Not Found",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Cancel reservation for "
                                + reservation
                                .getCustomer()
                                .getName()
                                + "?\n\n"
                                + "Booking ID: "
                                + reservation
                                .getBookingId()
                                + "\n"
                                + "Room: "
                                + reservation
                                .getRoom()
                                .getRoomNumber(),
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirm !=
                JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
                reservationService
                        .cancelReservation(
                                bookingId
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation cancelled successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            updateDashboard();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to cancel reservation.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // EXIT
    // =========================================================

    private void exitApplication() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to exit?",
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (result ==
                JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }
}