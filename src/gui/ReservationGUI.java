package gui;

import model.Customer;
import model.Reservation;
import model.Room;
import service.HotelSystem;
import service.ReservationService;
import service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationGUI extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;

    private JComboBox<String> roomTypeCombo;
    private JComboBox<String> roomNumberCombo;

    private JTextField checkInField;
    private JTextField checkOutField;

    private JButton checkInButton;
    private JButton checkOutButton;

    private JSpinner guestsSpinner;

    private JLabel priceLabel;
    private JLabel nightsLabel;
    private JLabel totalLabel;

    private final RoomService roomService;
    private final ReservationService reservationService;

    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd");

    private Date checkInDate;
    private Date checkOutDate;

    public ReservationGUI() {

        roomService =
                HotelSystem.getRoomService();

        reservationService =
                HotelSystem.getReservationService();

        setTitle("Hotel Reservation - New Reservation");

        setSize(900, 720);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(false);

        initializeDates();

        createGUI();

        loadRoomTypes();

        updateRoomNumbers();

        updatePrice();
    }

    // =====================================================
    // INITIALIZE DATES
    // =====================================================

    private void initializeDates() {

        Calendar calendar =
                Calendar.getInstance();

        checkInDate =
                calendar.getTime();

        calendar.add(
                Calendar.DAY_OF_MONTH,
                1
        );

        checkOutDate =
                calendar.getTime();
    }

    // =====================================================
    // CREATE GUI
    // =====================================================

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // -------------------------------------------------
        // HEADER
        // -------------------------------------------------

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                new Color(31, 41, 55)
        );

        header.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "Create Reservation"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // -------------------------------------------------
        // FORM
        // -------------------------------------------------

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setBackground(
                Color.WHITE
        );

        form.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 224, 230)
                        ),
                        new EmptyBorder(
                                20,
                                30,
                                20,
                                30
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        8,
                        7,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // Customer name
        addLabel(
                form,
                gbc,
                "Customer Name",
                0
        );

        nameField =
                createTextField();

        addComponent(
                form,
                gbc,
                nameField,
                0
        );

        // Phone
        addLabel(
                form,
                gbc,
                "Phone Number",
                1
        );

        phoneField =
                createTextField();

        addComponent(
                form,
                gbc,
                phoneField,
                1
        );

        // Email
        addLabel(
                form,
                gbc,
                "Email",
                2
        );

        emailField =
                createTextField();

        addComponent(
                form,
                gbc,
                emailField,
                2
        );

        // Room Type
        addLabel(
                form,
                gbc,
                "Room Type",
                3
        );

        roomTypeCombo =
                new JComboBox<>();

        roomTypeCombo.setPreferredSize(
                new Dimension(
                        400,
                        35
                )
        );

        roomTypeCombo.addActionListener(
                e -> {

                    updateRoomNumbers();

                    updatePrice();
                }
        );

        addComponent(
                form,
                gbc,
                roomTypeCombo,
                3
        );

        // Room Number
        addLabel(
                form,
                gbc,
                "Room Number",
                4
        );

        roomNumberCombo =
                new JComboBox<>();

        roomNumberCombo.setPreferredSize(
                new Dimension(
                        400,
                        35
                )
        );

        roomNumberCombo.addActionListener(
                e -> updatePrice()
        );

        addComponent(
                form,
                gbc,
                roomNumberCombo,
                4
        );

        // Check-in
        addLabel(
                form,
                gbc,
                "Check-in Date",
                5
        );

        JPanel checkInPanel =
                createDatePanel(true);

        addComponent(
                form,
                gbc,
                checkInPanel,
                5
        );

        // Check-out
        addLabel(
                form,
                gbc,
                "Check-out Date",
                6
        );

        JPanel checkOutPanel =
                createDatePanel(false);

        addComponent(
                form,
                gbc,
                checkOutPanel,
                6
        );

        // Guests
        addLabel(
                form,
                gbc,
                "Number of Guests",
                7
        );

        guestsSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                1,
                                1,
                                20,
                                1
                        )
                );

        guestsSpinner.setPreferredSize(
                new Dimension(
                        400,
                        35
                )
        );

        addComponent(
                form,
                gbc,
                guestsSpinner,
                7
        );

        // Room price
        addLabel(
                form,
                gbc,
                "Room Price",
                8
        );

        priceLabel =
                createInfoLabel();

        addComponent(
                form,
                gbc,
                priceLabel,
                8
        );

        // Nights
        addLabel(
                form,
                gbc,
                "Number of Nights",
                9
        );

        nightsLabel =
                createInfoLabel();

        addComponent(
                form,
                gbc,
                nightsLabel,
                9
        );

        // Total
        addLabel(
                form,
                gbc,
                "Total Amount",
                10
        );

        totalLabel =
                new JLabel(
                        "₹0.00"
                );

        totalLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        totalLabel.setForeground(
                new Color(22, 101, 52)
        );

        addComponent(
                form,
                gbc,
                totalLabel,
                10
        );

        mainPanel.add(
                form,
                BorderLayout.CENTER
        );

        // -------------------------------------------------
        // BUTTONS
        // -------------------------------------------------

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                12
                        )
                );

        buttons.setBackground(
                new Color(245, 247, 250)
        );

        JButton createButton =
                createButton(
                        "Create Reservation",
                        new Color(22, 101, 52)
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        new Color(75, 85, 99)
                );

        JButton refreshButton =
                createButton(
                        "Refresh Rooms",
                        new Color(37, 99, 235)
                );

        createButton.addActionListener(
                e -> createReservation()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        refreshButton.addActionListener(
                e -> {

                    loadRoomTypes();

                    updateRoomNumbers();

                    updatePrice();

                    JOptionPane.showMessageDialog(
                            this,
                            "Room information refreshed.",
                            "Refresh",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );

        buttons.add(createButton);
        buttons.add(clearButton);
        buttons.add(refreshButton);

        mainPanel.add(
                buttons,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =====================================================
    // DATE PANEL
    // =====================================================

    private JPanel createDatePanel(
            boolean checkIn) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        panel.setOpaque(false);

        JTextField field =
                new JTextField();

        field.setEditable(false);

        field.setPreferredSize(
                new Dimension(
                        300,
                        35
                )
        );

        JButton button =
                new JButton("📅");

        button.setPreferredSize(
                new Dimension(
                        55,
                        35
                )
        );

        if (checkIn) {

            checkInField = field;

            checkInButton = button;

            checkInField.setText(
                    dateFormat.format(
                            checkInDate
                    )
            );

            button.addActionListener(
                    e -> {

                        Date selected =
                                showCalendarDialog(
                                        checkInDate,
                                        "Select Check-in Date"
                                );

                        if (selected != null) {

                            checkInDate =
                                    selected;

                            if (!checkOutDate.after(
                                    checkInDate
                            )) {

                                Calendar cal =
                                        Calendar.getInstance();

                                cal.setTime(
                                        checkInDate
                                );

                                cal.add(
                                        Calendar.DAY_OF_MONTH,
                                        1
                                );

                                checkOutDate =
                                        cal.getTime();
                            }

                            checkInField.setText(
                                    dateFormat.format(
                                            checkInDate
                                    )
                            );

                            checkOutField.setText(
                                    dateFormat.format(
                                            checkOutDate
                                    )
                            );

                            updatePrice();
                        }
                    }
            );

        } else {

            checkOutField = field;

            checkOutButton = button;

            checkOutField.setText(
                    dateFormat.format(
                            checkOutDate
                    )
            );

            button.addActionListener(
                    e -> {

                        Date selected =
                                showCalendarDialog(
                                        checkOutDate,
                                        "Select Check-out Date"
                                );

                        if (selected != null) {

                            if (!selected.after(
                                    checkInDate
                            )) {

                                JOptionPane.showMessageDialog(
                                        this,
                                        "Check-out date must be after check-in date.",
                                        "Invalid Date",
                                        JOptionPane.WARNING_MESSAGE
                                );

                                return;
                            }

                            checkOutDate =
                                    selected;

                            checkOutField.setText(
                                    dateFormat.format(
                                            checkOutDate
                                    )
                            );

                            updatePrice();
                        }
                    }
            );
        }

        panel.add(
                field,
                BorderLayout.CENTER
        );

        panel.add(
                button,
                BorderLayout.EAST
        );

        return panel;
    }

    // =====================================================
    // CALENDAR DIALOG
    // =====================================================

    private Date showCalendarDialog(
            Date initialDate,
            String title) {

        Calendar calendar =
                Calendar.getInstance();

        calendar.setTime(
                initialDate
        );

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        JComboBox<String> monthCombo =
                new JComboBox<>();

        String[] months = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };

        for (String month : months) {
            monthCombo.addItem(month);
        }

        monthCombo.setSelectedIndex(
                calendar.get(
                        Calendar.MONTH
                )
        );

        JSpinner yearSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                calendar.get(
                                        Calendar.YEAR
                                ),
                                2026,
                                2100,
                                1
                        )
                );

        JPanel top =
                new JPanel();

        top.add(
                new JLabel("Month:")
        );

        top.add(
                monthCombo
        );

        top.add(
                new JLabel("Year:")
        );

        top.add(
                yearSpinner
        );

        JPanel daysPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                7,
                                3,
                                3
                        )
                );

        String[] dayNames = {
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"
        };

        for (String day : dayNames) {

            JLabel label =
                    new JLabel(
                            day,
                            SwingConstants.CENTER
                    );

            label.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            12
                    )
            );

            daysPanel.add(label);
        }

        final Date[] selectedDate = {
                null
        };

        Runnable buildCalendar =
                () -> {

                    daysPanel.removeAll();

                    for (String day : dayNames) {

                        JLabel label =
                                new JLabel(
                                        day,
                                        SwingConstants.CENTER
                                );

                        label.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.BOLD,
                                        12
                                )
                        );

                        daysPanel.add(label);
                    }

                    int month =
                            monthCombo
                                    .getSelectedIndex();

                    int year =
                            (Integer)
                                    yearSpinner
                                            .getValue();

                    Calendar first =
                            Calendar.getInstance();

                    first.clear();

                    first.set(
                            year,
                            month,
                            1
                    );

                    int firstDay =
                            first.get(
                                    Calendar.DAY_OF_WEEK
                            );

                    int maxDay =
                            first.getActualMaximum(
                                    Calendar.DAY_OF_MONTH
                            );

                    for (int i = 1;
                         i < firstDay;
                         i++) {

                        daysPanel.add(
                                new JLabel("")
                        );
                    }

                    for (int day = 1;
                         day <= maxDay;
                         day++) {

                        final int selectedDay =
                                day;

                        JButton dayButton =
                                new JButton(
                                        String.valueOf(
                                                day
                                        )
                                );

                        dayButton.setFocusPainted(
                                false
                        );

                        dayButton.addActionListener(
                                e -> {

                                    Calendar selected =
                                            Calendar.getInstance();

                                    selected.clear();

                                    selected.set(
                                            year,
                                            month,
                                            selectedDay
                                    );

                                    selectedDate[0] =
                                            selected.getTime();
                                }
                        );

                        daysPanel.add(
                                dayButton
                        );
                    }

                    daysPanel.revalidate();

                    daysPanel.repaint();
                };

        monthCombo.addActionListener(
                e -> buildCalendar.run()
        );

        yearSpinner.addChangeListener(
                e -> buildCalendar.run()
        );

        buildCalendar.run();

        panel.add(
                top,
                BorderLayout.NORTH
        );

        panel.add(
                daysPanel,
                BorderLayout.CENTER
        );

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        title,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result ==
                JOptionPane.OK_OPTION) {

            return selectedDate[0];
        }

        return null;
    }

    // =====================================================
    // LOAD ROOM TYPES
    // =====================================================

    private void loadRoomTypes() {

        String current =
                roomTypeCombo
                        .getSelectedItem() == null
                        ? null
                        : roomTypeCombo
                        .getSelectedItem()
                        .toString();

        roomTypeCombo.removeAllItems();

        roomTypeCombo.addItem(
                "Standard"
        );

        roomTypeCombo.addItem(
                "Deluxe"
        );

        roomTypeCombo.addItem(
                "Suite"
        );

        if (current != null) {

            roomTypeCombo.setSelectedItem(
                    current
            );
        }
    }

    // =====================================================
    // UPDATE ROOM NUMBERS
    // =====================================================

    private void updateRoomNumbers() {

        String type =
                (String)
                        roomTypeCombo
                                .getSelectedItem();

        roomNumberCombo.removeAllItems();

        if (type == null) {
            return;
        }

        List<Room> rooms =
                roomService.searchByCategory(
                        type
                );

        for (Room room : rooms) {

            roomNumberCombo.addItem(
                    String.valueOf(
                            room.getRoomNumber()
                    )
            );
        }

        if (roomNumberCombo.getItemCount()
                == 0) {

            roomNumberCombo.addItem(
                    "No rooms available"
            );
        }
    }

    // =====================================================
    // UPDATE PRICE
    // =====================================================

    private void updatePrice() {

        String roomText =
                (String)
                        roomNumberCombo
                                .getSelectedItem();

        if (roomText == null ||
                roomText.equals(
                        "No rooms available"
                )) {

            priceLabel.setText(
                    "₹0.00 / night"
            );

            nightsLabel.setText(
                    "0"
            );

            totalLabel.setText(
                    "₹0.00"
            );

            return;
        }

        try {

            int roomNumber =
                    Integer.parseInt(
                            roomText
                    );

            Room room =
                    roomService.findRoom(
                            roomNumber
                    );

            if (room == null) {
                return;
            }

            long milliseconds =
                    checkOutDate.getTime()
                            - checkInDate.getTime();

            long nights =
                    milliseconds
                            / (
                                    1000L
                                            * 60
                                            * 60
                                            * 24
                            );

            if (nights <= 0) {
                nights = 1;
            }

            double total =
                    room.getPrice()
                            * nights;

            priceLabel.setText(
                    String.format(
                            "₹%.2f / night",
                            room.getPrice()
                    )
            );

            nightsLabel.setText(
                    String.valueOf(
                            nights
                    )
            );

            totalLabel.setText(
                    String.format(
                            "₹%.2f",
                            total
                    )
            );

        } catch (Exception e) {

            priceLabel.setText(
                    "₹0.00 / night"
            );

            nightsLabel.setText(
                    "0"
            );

            totalLabel.setText(
                    "₹0.00"
            );
        }
    }

    // =====================================================
    // CREATE RESERVATION
    // =====================================================

    private void createReservation() {

        String name =
                nameField.getText()
                        .trim();

        String phone =
                phoneField.getText()
                        .trim();

        String email =
                emailField.getText()
                        .trim();

        if (name.isEmpty()) {

            showError(
                    "Please enter customer name."
            );

            return;
        }

        if (!phone.matches(
                "\\d{10}"
        )) {

            showError(
                    "Phone number must contain exactly 10 digits."
            );

            return;
        }

        if (email.isEmpty() ||
                !email.matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
                )) {

            showError(
                    "Please enter a valid email address."
            );

            return;
        }

        String roomText =
                (String)
                        roomNumberCombo
                                .getSelectedItem();

        if (roomText == null ||
                roomText.equals(
                        "No rooms available"
                )) {

            showError(
                    "No available room is selected."
            );

            return;
        }

        int roomNumber;

        try {

            roomNumber =
                    Integer.parseInt(
                            roomText
                    );

        } catch (NumberFormatException e) {

            showError(
                    "Invalid room number."
            );

            return;
        }

        Room room =
                roomService.findRoom(
                        roomNumber
                );

        if (room == null) {

            showError(
                    "Room not found."
            );

            return;
        }

        if (!room.isAvailable()) {

            showError(
                    "This room is already booked."
            );

            updateRoomNumbers();

            return;
        }

        if (!checkOutDate.after(
                checkInDate
        )) {

            showError(
                    "Check-out date must be after check-in date."
            );

            return;
        }

        int guests =
                (Integer)
                        guestsSpinner
                                .getValue();

        String checkIn =
                dateFormat.format(
                        checkInDate
                );

        String checkOut =
                dateFormat.format(
                        checkOutDate
                );

        Customer customer =
                new Customer(
                        1001,
                        name,
                        phone,
                        email
                );

        Reservation reservation =
                reservationService.createReservation(
                        customer,
                        room,
                        checkIn,
                        checkOut,
                        guests
                );

        if (reservation == null) {

            showError(
                    "Unable to create reservation."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,

                "Reservation Successful!\n\n"
                        + "Booking ID: "
                        + reservation.getBookingId()
                        + "\n"
                        + "Customer: "
                        + name
                        + "\n"
                        + "Room: "
                        + room.getRoomNumber()
                        + "\n"
                        + "Room Type: "
                        + room.getCategory()
                        + "\n"
                        + "Check-in: "
                        + checkIn
                        + "\n"
                        + "Check-out: "
                        + checkOut
                        + "\n"
                        + "Guests: "
                        + guests
                        + "\n"
                        + "Total: ₹"
                        + String.format(
                                "%.2f",
                                reservation
                                        .getTotalAmount()
                        ),

                "Reservation Confirmed",

                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        nameField.setText("");

        phoneField.setText("");

        emailField.setText("");

        guestsSpinner.setValue(1);

        initializeDates();

        checkInField.setText(
                dateFormat.format(
                        checkInDate
                )
        );

        checkOutField.setText(
                dateFormat.format(
                        checkOutDate
                )
        );

        updateRoomNumbers();

        updatePrice();

        nameField.requestFocus();
    }

    // =====================================================
    // TEXT FIELD
    // =====================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setPreferredSize(
                new Dimension(
                        400,
                        35
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        return field;
    }

    // =====================================================
    // LABEL
    // =====================================================

    private void addLabel(
            JPanel panel,
            GridBagConstraints gbc,
            String text,
            int row) {

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.weightx = 0.3;

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        panel.add(
                label,
                gbc
        );
    }

    // =====================================================
    // COMPONENT
    // =====================================================

    private void addComponent(
            JPanel panel,
            GridBagConstraints gbc,
            JComponent component,
            int row) {

        gbc.gridx = 1;

        gbc.gridy = row;

        gbc.weightx = 0.7;

        panel.add(
                component,
                gbc
        );
    }

    // =====================================================
    // INFO LABEL
    // =====================================================

    private JLabel createInfoLabel() {

        JLabel label =
                new JLabel("0");

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        return label;
    }

    // =====================================================
    // BUTTON
    // =====================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        190,
                        42
                )
        );

        button.setBackground(
                color
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(
                false
        );

        return button;
    }

    // =====================================================
    // ERROR
    // =====================================================

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}