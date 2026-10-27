package gui;

import model.Reservation;
import service.HotelSystem;
import service.ReservationService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class BookingDetailsGUI extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color NAVY =
            new Color(15, 23, 42);

    private final Color NAVY_LIGHT =
            new Color(30, 41, 59);

    private final Color BLUE =
            new Color(37, 99, 235);

    private final Color GREEN =
            new Color(22, 163, 74);

    private final Color RED =
            new Color(220, 38, 38);

    private final Color TEXT =
            new Color(31, 41, 55);

    private final Color MUTED =
            new Color(100, 116, 139);

    private final Color BACKGROUND =
            new Color(241, 245, 249);

    private final Color WHITE =
            Color.WHITE;

    // =========================================================
    // COMPONENTS
    // =========================================================

    private JComboBox<String> searchTypeCombo;

    private JTextField searchField;

    private JPanel resultPanel;

    private JLabel resultStatusLabel;

    private JLabel bookingIdValue;
    private JLabel customerNameValue;
    private JLabel phoneValue;
    private JLabel emailValue;

    private JLabel roomNumberValue;
    private JLabel roomTypeValue;
    private JLabel roomPriceValue;

    private JLabel checkInValue;
    private JLabel checkOutValue;

    private JLabel guestsValue;
    private JLabel totalValue;

    private JLabel emptyIconLabel;
    private JLabel emptyTextLabel;

    private JButton copyButton;
    private JButton clearButton;

    private final ReservationService reservationService;

    private String currentBookingId = "";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BookingDetailsGUI() {

        reservationService =
                HotelSystem.getReservationService();

        setTitle(
                "Hotel Reservation - Booking Details"
        );

        setSize(
                1100,
                760
        );

        setMinimumSize(
                new Dimension(
                        950,
                        650
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createGUI();
    }

    // =========================================================
    // MAIN GUI
    // =========================================================

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        // Header
        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        // Center content
        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );

        centerPanel.setOpaque(false);

        centerPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        centerPanel.add(
                createSearchPanel(),
                BorderLayout.NORTH
        );

        resultPanel =
                createResultPanel();

        centerPanel.add(
                resultPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        showEmptyState();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                NAVY
        );

        header.setBorder(
                new EmptyBorder(
                        20,
                        28,
                        20,
                        28
                )
        );

        // Left side
        JPanel titlePanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        titlePanel.setOpaque(false);

        JLabel icon =
                new JLabel("🏨");

        icon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        34
                )
        );

        JLabel title =
                new JLabel(
                        "Booking Details"
                );

        title.setForeground(
                WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Find and manage guest reservations"
                );

        subtitle.setForeground(
                new Color(
                        203,
                        213,
                        225
                )
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                3
                        )
                );

        textPanel.setOpaque(false);

        textPanel.add(title);
        textPanel.add(subtitle);

        titlePanel.add(
                icon,
                BorderLayout.WEST
        );

        titlePanel.add(
                textPanel,
                BorderLayout.CENTER
        );

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        // Right side
        JLabel status =
                new JLabel(
                        "● SYSTEM ONLINE"
                );

        status.setForeground(
                new Color(
                        134,
                        239,
                        172
                )
        );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        header.add(
                status,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // SEARCH PANEL
    // =========================================================

    private JPanel createSearchPanel() {

        JPanel outer =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        outer.setBackground(
                WHITE
        );

        outer.setBorder(
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
                                20,
                                22,
                                20,
                                22
                        )
                )
        );

        JLabel heading =
                new JLabel(
                        "Find a Reservation"
                );

        heading.setForeground(
                TEXT
        );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        JLabel help =
                new JLabel(
                        "Search using booking ID, customer name, phone number or email"
                );

        help.setForeground(
                MUTED
        );

        help.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        JPanel headingPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                2
                        )
                );

        headingPanel.setOpaque(false);

        headingPanel.add(heading);
        headingPanel.add(help);

        outer.add(
                headingPanel,
                BorderLayout.NORTH
        );

        JPanel searchRow =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchRow.setOpaque(false);

        // Search type
        searchTypeCombo =
                new JComboBox<>(
                        new String[]{
                                "Booking ID",
                                "Customer Name",
                                "Phone Number",
                                "Email"
                        }
                );

        searchTypeCombo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        searchTypeCombo.setPreferredSize(
                new Dimension(
                        175,
                        43
                )
        );

        searchRow.add(
                searchTypeCombo,
                BorderLayout.WEST
        );

        // Search field
        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        203,
                                        213,
                                        225
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                0,
                                14,
                                0,
                                14
                        )
                )
        );

        searchField.setToolTipText(
                "Enter your search value"
        );

        searchRow.add(
                searchField,
                BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                7,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton searchButton =
                createActionButton(
                        "🔍  Search",
                        BLUE,
                        WHITE
                );

        clearButton =
                createActionButton(
                        "✕  Clear",
                        new Color(
                                71,
                                85,
                                105
                        ),
                        WHITE
                );

        JButton showAllButton =
                createActionButton(
                        "▦  Show All",
                        NAVY_LIGHT,
                        WHITE
                );

        searchButton.addActionListener(
                e -> searchReservations()
        );

        clearButton.addActionListener(
                e -> clearSearch()
        );

        showAllButton.addActionListener(
                e -> showAllReservations()
        );

        searchField.addActionListener(
                e -> searchReservations()
        );

        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(clearButton);

        searchRow.add(
                buttonPanel,
                BorderLayout.EAST
        );

        outer.add(
                searchRow,
                BorderLayout.CENTER
        );

        return outer;
    }

    // =========================================================
    // RESULT PANEL
    // =========================================================

    private JPanel createResultPanel() {

        JPanel outer =
                new JPanel(
                        new BorderLayout()
                );

        outer.setBackground(
                WHITE
        );

        outer.setBorder(
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
                                20,
                                22,
                                20,
                                22
                        )
                )
        );

        // Result header
        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        JPanel statusPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        statusPanel.setOpaque(false);

        resultStatusLabel =
                new JLabel(
                        "RESERVATION"
                );

        resultStatusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        resultStatusLabel.setForeground(
                MUTED
        );

        statusPanel.add(
                resultStatusLabel
        );

        top.add(
                statusPanel,
                BorderLayout.WEST
        );

        copyButton =
                createActionButton(
                        "📋  Copy Booking ID",
                        new Color(
                                241,
                                245,
                                249
                        ),
                        TEXT
                );

        copyButton.setVisible(false);

        copyButton.addActionListener(
                e -> copyBookingId()
        );

        top.add(
                copyButton,
                BorderLayout.EAST
        );

        outer.add(
                top,
                BorderLayout.NORTH
        );

        // Cards
        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                15,
                                15
                        )
                );

        cards.setOpaque(false);

        cards.add(
                createCustomerCard()
        );

        cards.add(
                createRoomCard()
        );

        cards.add(
                createStayCard()
        );

        cards.add(
                createPaymentCard()
        );

        outer.add(
                cards,
                BorderLayout.CENTER
        );

        return outer;
    }

    // =========================================================
    // CUSTOMER CARD
    // =========================================================

    private JPanel createCustomerCard() {

        JPanel card =
                createCard();

        JPanel content =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                0,
                                6
                        )
                );

        content.setOpaque(false);

        customerNameValue =
                createValueLabel(
                        "—"
                );

        phoneValue =
                createValueLabel(
                        "—"
                );

        emailValue =
                createValueLabel(
                        "—"
                );

        bookingIdValue =
                createValueLabel(
                        "—"
                );

        content.add(
                createInfoRow(
                        "👤 Customer",
                        customerNameValue
                )
        );

        content.add(
                createInfoRow(
                        "📞 Phone",
                        phoneValue
                )
        );

        content.add(
                createInfoRow(
                        "✉ Email",
                        emailValue
                )
        );

        content.add(
                createInfoRow(
                        "🎫 Booking ID",
                        bookingIdValue
                )
        );

        card.add(
                createCardTitle(
                        "👤  Guest Information"
                ),
                BorderLayout.NORTH
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ROOM CARD
    // =========================================================

    private JPanel createRoomCard() {

        JPanel card =
                createCard();

        JPanel content =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                8
                        )
                );

        content.setOpaque(false);

        roomNumberValue =
                createValueLabel(
                        "—"
                );

        roomTypeValue =
                createValueLabel(
                        "—"
                );

        roomPriceValue =
                createValueLabel(
                        "—"
                );

        content.add(
                createInfoRow(
                        "🚪 Room Number",
                        roomNumberValue
                )
        );

        content.add(
                createInfoRow(
                        "🛏 Room Type",
                        roomTypeValue
                )
        );

        content.add(
                createInfoRow(
                        "💵 Price",
                        roomPriceValue
                )
        );

        card.add(
                createCardTitle(
                        "🛏  Room Information"
                ),
                BorderLayout.NORTH
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // STAY CARD
    // =========================================================

    private JPanel createStayCard() {

        JPanel card =
                createCard();

        JPanel content =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                8
                        )
                );

        content.setOpaque(false);

        checkInValue =
                createValueLabel(
                        "—"
                );

        checkOutValue =
                createValueLabel(
                        "—"
                );

        guestsValue =
                createValueLabel(
                        "—"
                );

        content.add(
                createInfoRow(
                        "📅 Check-in",
                        checkInValue
                )
        );

        content.add(
                createInfoRow(
                        "📅 Check-out",
                        checkOutValue
                )
        );

        content.add(
                createInfoRow(
                        "👥 Guests",
                        guestsValue
                )
        );

        card.add(
                createCardTitle(
                        "📅  Stay Information"
                ),
                BorderLayout.NORTH
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // PAYMENT CARD
    // =========================================================

    private JPanel createPaymentCard() {

        JPanel card =
                createCard();

        JPanel content =
                new JPanel(
                        new GridBagLayout()
                );

        content.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 1;

        gbc.anchor =
                GridBagConstraints.WEST;

        totalValue =
                new JLabel(
                        "₹0.00"
                );

        totalValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        totalValue.setForeground(
                GREEN
        );

        content.add(
                totalValue,
                gbc
        );

        gbc.gridy++;

        JLabel amountText =
                new JLabel(
                        "Total Reservation Amount"
                );

        amountText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        amountText.setForeground(
                MUTED
        );

        gbc.insets =
                new Insets(
                        5,
                        0,
                        0,
                        0
                );

        content.add(
                amountText,
                gbc
        );

        gbc.gridy++;

        JLabel status =
                new JLabel(
                        "● Reservation Active"
                );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        status.setForeground(
                GREEN
        );

        gbc.insets =
                new Insets(
                        18,
                        0,
                        0,
                        0
                );

        content.add(
                status,
                gbc
        );

        card.add(
                createCardTitle(
                        "💰  Payment Summary"
                ),
                BorderLayout.NORTH
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // EMPTY STATE
    // =========================================================

    private void showEmptyState() {

        resultStatusLabel.setText(
                "READY TO SEARCH"
        );

        resultStatusLabel.setForeground(
                MUTED
        );

        copyButton.setVisible(false);

        setValuesToDash();

        // Add a small prompt in title area
        // while keeping cards visible.
    }

    private void setValuesToDash() {

        bookingIdValue.setText("—");

        customerNameValue.setText("—");

        phoneValue.setText("—");

        emailValue.setText("—");

        roomNumberValue.setText("—");

        roomTypeValue.setText("—");

        roomPriceValue.setText("—");

        checkInValue.setText("—");

        checkOutValue.setText("—");

        guestsValue.setText("—");

        totalValue.setText("₹0.00");
    }

    // =========================================================
    // SEARCH RESERVATIONS
    // =========================================================

    private void searchReservations() {

        String searchText =
                searchField.getText().trim();

        if (searchText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter something to search.",
                    "Search Required",
                    JOptionPane.WARNING_MESSAGE
            );

            searchField.requestFocus();

            return;
        }

        String searchType =
                (String)
                        searchTypeCombo
                                .getSelectedItem();

        List<Reservation> reservations =
                reservationService
                        .getReservations();

        Reservation found =
                null;

        for (Reservation reservation :
                reservations) {

            boolean match =
                    false;

            if (searchType.equals(
                    "Booking ID"
            )) {

                match =
                        reservation
                                .getBookingId()
                                .equalsIgnoreCase(
                                        searchText
                                );
            }

            else if (searchType.equals(
                    "Customer Name"
            )) {

                match =
                        reservation
                                .getCustomer()
                                .getName()
                                .toLowerCase()
                                .contains(
                                        searchText
                                                .toLowerCase()
                                );
            }

            else if (searchType.equals(
                    "Phone Number"
            )) {

                match =
                        reservation
                                .getCustomer()
                                .getPhone()
                                .contains(
                                        searchText
                                );
            }

            else if (searchType.equals(
                    "Email"
            )) {

                match =
                        reservation
                                .getCustomer()
                                .getEmail()
                                .toLowerCase()
                                .contains(
                                        searchText
                                                .toLowerCase()
                                );
            }

            if (match) {

                found =
                        reservation;

                break;
            }
        }

        if (found == null) {

            showNotFound();

            return;
        }

        displayReservation(
                found
        );
    }

    // =========================================================
    // DISPLAY RESERVATION
    // =========================================================

    private void displayReservation(
            Reservation reservation) {

        currentBookingId =
                reservation.getBookingId();

        resultStatusLabel.setText(
                "● RESERVATION FOUND"
        );

        resultStatusLabel.setForeground(
                GREEN
        );

        copyButton.setVisible(true);

        bookingIdValue.setText(
                reservation.getBookingId()
        );

        customerNameValue.setText(
                reservation
                        .getCustomer()
                        .getName()
        );

        phoneValue.setText(
                reservation
                        .getCustomer()
                        .getPhone()
        );

        emailValue.setText(
                reservation
                        .getCustomer()
                        .getEmail()
        );

        roomNumberValue.setText(
                String.valueOf(
                        reservation
                                .getRoom()
                                .getRoomNumber()
                )
        );

        roomTypeValue.setText(
                reservation
                        .getRoom()
                        .getCategory()
        );

        roomPriceValue.setText(
                String.format(
                        "₹%.2f / night",
                        reservation
                                .getRoom()
                                .getPrice()
                )
        );

        checkInValue.setText(
                reservation.getCheckIn()
        );

        checkOutValue.setText(
                reservation.getCheckOut()
        );

        guestsValue.setText(
                String.valueOf(
                        reservation.getGuests()
                )
        );

        totalValue.setText(
                String.format(
                        "₹%.2f",
                        reservation
                                .getTotalAmount()
                )
        );
    }

    // =========================================================
    // NOT FOUND
    // =========================================================

    private void showNotFound() {

        resultStatusLabel.setText(
                "● RESERVATION NOT FOUND"
        );

        resultStatusLabel.setForeground(
                RED
        );

        copyButton.setVisible(false);

        currentBookingId = "";

        setValuesToDash();

        JOptionPane.showMessageDialog(
                this,
                "No reservation found for:\n\n"
                        + searchField
                        .getText()
                        .trim(),
                "No Reservation Found",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // SHOW ALL
    // =========================================================

    private void showAllReservations() {

        List<Reservation> reservations =
                reservationService
                        .getReservations();

        if (reservations.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "There are currently no reservations.",
                    "No Reservations",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        // Create selection dialog
        String[] choices =
                new String[
                        reservations.size()
                ];

        for (int i = 0;
             i < reservations.size();
             i++) {

            Reservation r =
                    reservations.get(i);

            choices[i] =
                    r.getBookingId()
                            + "  •  "
                            + r.getCustomer()
                            .getName()
                            + "  •  Room "
                            + r.getRoom()
                            .getRoomNumber();
        }

        String selected =
                (String)
                        JOptionPane.showInputDialog(
                                this,
                                "Select a reservation:",
                                "All Reservations",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                choices,
                                choices[0]
                        );

        if (selected == null) {
            return;
        }

        for (Reservation r :
                reservations) {

            if (selected.startsWith(
                    r.getBookingId()
            )) {

                displayReservation(r);

                break;
            }
        }
    }

    // =========================================================
    // COPY BOOKING ID
    // =========================================================

    private void copyBookingId() {

        if (currentBookingId.isEmpty()) {
            return;
        }

        StringSelection selection =
                new StringSelection(
                        currentBookingId
                );

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        selection,
                        null
                );

        JOptionPane.showMessageDialog(
                this,
                "Booking ID copied:\n"
                        + currentBookingId,
                "Copied",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearSearch() {

        searchField.setText("");

        searchTypeCombo.setSelectedIndex(
                0
        );

        currentBookingId = "";

        showEmptyState();

        searchField.requestFocus();
    }

    // =========================================================
    // CARD
    // =========================================================

    private JPanel createCard() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

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
                                17,
                                18,
                                17,
                                18
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // CARD TITLE
    // =========================================================

    private JLabel createCardTitle(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        label.setForeground(
                TEXT
        );

        return label;
    }

    // =========================================================
    // INFO ROW
    // =========================================================

    private JPanel createInfoRow(
            String title,
            JLabel value) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        panel.setOpaque(false);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        titleLabel.setForeground(
                MUTED
        );

        panel.add(
                titleLabel,
                BorderLayout.WEST
        );

        panel.add(
                value,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // VALUE LABEL
    // =========================================================

    private JLabel createValueLabel(
            String text) {

        JLabel label =
                new JLabel(text);

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

        return label;
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private JButton createActionButton(
            String text,
            Color background,
            Color foreground) {

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
                foreground
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                background,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                8,
                                13,
                                8,
                                13
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}