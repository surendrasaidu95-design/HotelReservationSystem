package service;

import database.DatabaseManager;
import model.Customer;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private List<Reservation> reservations;

    private final RoomService roomService;

    private final DatabaseManager databaseManager;

    public ReservationService(
            RoomService roomService) {

        this.roomService =
                roomService;

        this.databaseManager =
                new DatabaseManager();

        this.reservations =
                new ArrayList<>();
    }

    // =====================================================
    // CREATE RESERVATION
    // =====================================================

    public Reservation createReservation(
            Customer customer,
            Room room,
            String checkIn,
            String checkOut,
            int guests) {

        if (customer == null ||
                room == null ||
                guests <= 0) {

            return null;
        }

        if (!room.isAvailable()) {

            return null;
        }

        long nights =
                calculateNights(
                        checkIn,
                        checkOut
                );

        if (nights <= 0) {

            return null;
        }

        boolean booked =
                roomService.bookRoom(
                        room.getRoomNumber()
                );

        if (!booked) {

            return null;
        }

        String bookingId =
                generateBookingId();

        double totalAmount =
                room.getPrice() * nights;

        Reservation reservation =
                new Reservation(
                        bookingId,
                        customer,
                        room,
                        checkIn,
                        checkOut,
                        guests,
                        totalAmount
                );

        reservations.add(
                reservation
        );

        saveData();

        return reservation;
    }

    // =====================================================
    // CALCULATE NIGHTS
    // =====================================================

    private long calculateNights(
            String checkIn,
            String checkOut) {

        try {

            LocalDate start =
                    LocalDate.parse(
                            checkIn
                    );

            LocalDate end =
                    LocalDate.parse(
                            checkOut
                    );

            return ChronoUnit.DAYS.between(
                    start,
                    end
            );

        } catch (Exception e) {

            return -1;
        }
    }

    // =====================================================
    // GENERATE BOOKING ID
    // =====================================================

    private String generateBookingId() {

        int maxNumber = 1000;

        for (Reservation reservation :
                reservations) {

            String id =
                    reservation.getBookingId();

            if (id == null) {
                continue;
            }

            try {

                if (id.startsWith("B")) {

                    int number =
                            Integer.parseInt(
                                    id.substring(1)
                            );

                    if (number > maxNumber) {

                        maxNumber = number;
                    }
                }

            } catch (NumberFormatException ignored) {
            }
        }

        return "B" + (maxNumber + 1);
    }

    // =====================================================
    // GET RESERVATIONS
    // =====================================================

    public List<Reservation> getReservations() {

        return reservations;
    }

    // =====================================================
    // LOAD RESERVATIONS
    // =====================================================

    public void setReservations(
            List<Reservation> loadedReservations) {

        if (loadedReservations == null) {

            return;
        }

        reservations =
                new ArrayList<>(
                        loadedReservations
                );
    }

    // =====================================================
    // FIND RESERVATION
    // =====================================================

    public Reservation findReservation(
            String bookingId) {

        if (bookingId == null ||
                bookingId.trim().isEmpty()) {

            return null;
        }

        for (Reservation reservation :
                reservations) {

            if (reservation
                    .getBookingId()
                    .equalsIgnoreCase(
                            bookingId.trim()
                    )) {

                return reservation;
            }
        }

        return null;
    }

    // =====================================================
    // CANCEL RESERVATION
    // =====================================================

    public boolean cancelReservation(
            String bookingId) {

        Reservation reservation =
                findReservation(
                        bookingId
                );

        if (reservation == null) {

            return false;
        }

        roomService.releaseRoom(
                reservation
                        .getRoom()
                        .getRoomNumber()
        );

        reservations.remove(
                reservation
        );

        saveData();

        return true;
    }

    // =====================================================
    // SAVE DATA
    // =====================================================

    private void saveData() {

        databaseManager.saveRooms(
                roomService.getAllRooms()
        );

        databaseManager.saveReservations(
                reservations
        );
    }
}