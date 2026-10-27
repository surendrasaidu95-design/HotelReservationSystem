package service;

import database.DatabaseManager;
import model.Reservation;
import model.Room;

import java.util.List;

public class HotelSystem {

    private static final DatabaseManager databaseManager =
            new DatabaseManager();

    private static final RoomService roomService =
            new RoomService();

    private static final ReservationService reservationService =
            new ReservationService(roomService);

    private static final PaymentService paymentService =
            new PaymentService();

    private HotelSystem() {
    }

    // =====================================================
    // INITIALIZE SYSTEM
    // =====================================================

    public static void initialize() {

        // Load saved rooms
        List<Room> savedRooms =
                databaseManager.loadRooms();

        if (!savedRooms.isEmpty()) {

            roomService.setRooms(savedRooms);
        }

        // Load saved reservations
        List<Reservation> savedReservations =
                databaseManager.loadReservations(
                        roomService.getAllRooms()
                );

        reservationService.setReservations(
                savedReservations
        );
    }

    // =====================================================
    // SAVE ALL DATA
    // =====================================================

    public static void saveData() {

        databaseManager.saveRooms(
                roomService.getAllRooms()
        );

        databaseManager.saveReservations(
                reservationService.getReservations()
        );
    }

    // =====================================================
    // GET ROOM SERVICE
    // =====================================================

    public static RoomService getRoomService() {
        return roomService;
    }

    // =====================================================
    // GET RESERVATION SERVICE
    // =====================================================

    public static ReservationService getReservationService() {
        return reservationService;
    }

    // =====================================================
    // GET PAYMENT SERVICE
    // =====================================================

    public static PaymentService getPaymentService() {
        return paymentService;
    }

    // =====================================================
    // GET DATABASE MANAGER
    // =====================================================

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}