package database;

import model.Customer;
import model.Reservation;
import model.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DATA_FOLDER = "data";

    private static final String ROOM_FILE =
            DATA_FOLDER + "/rooms.txt";

    private static final String RESERVATION_FILE =
            DATA_FOLDER + "/reservations.txt";

    public DatabaseManager() {

        createDataFolder();
    }

    // =====================================================
    // CREATE DATA FOLDER
    // =====================================================

    private void createDataFolder() {

        File folder =
                new File(DATA_FOLDER);

        if (!folder.exists()) {

            folder.mkdirs();
        }
    }

    // =====================================================
    // SAVE ROOMS
    // =====================================================

    public void saveRooms(
            List<Room> rooms) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        ROOM_FILE
                                )
                        )
        ) {

            for (Room room : rooms) {

                writer.write(
                        room.getRoomNumber()
                                + "|"
                                + room.getCategory()
                                + "|"
                                + room.getPrice()
                                + "|"
                                + room.isAvailable()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving rooms: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // LOAD ROOMS
    // =====================================================

    public List<Room> loadRooms() {

        List<Room> rooms =
                new ArrayList<>();

        File file =
                new File(ROOM_FILE);

        if (!file.exists()) {

            return rooms;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data =
                        line.split("\\|");

                if (data.length == 4) {

                    int roomNumber =
                            Integer.parseInt(
                                    data[0]
                            );

                    String category =
                            data[1];

                    double price =
                            Double.parseDouble(
                                    data[2]
                            );

                    boolean available =
                            Boolean.parseBoolean(
                                    data[3]
                            );

                    rooms.add(
                            new Room(
                                    roomNumber,
                                    category,
                                    price,
                                    available
                            )
                    );
                }
            }

        } catch (
                IOException |
                NumberFormatException e
        ) {

            System.out.println(
                    "Error loading rooms: "
                            + e.getMessage()
            );
        }

        return rooms;
    }

    // =====================================================
    // SAVE RESERVATIONS
    // =====================================================

    public void saveReservations(
            List<Reservation> reservations) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        RESERVATION_FILE
                                )
                        )
        ) {

            for (
                    Reservation reservation :
                    reservations
            ) {

                writer.write(
                        reservation.getBookingId()
                                + "|"
                                + reservation
                                .getCustomer()
                                .getCustomerId()
                                + "|"
                                + reservation
                                .getCustomer()
                                .getName()
                                + "|"
                                + reservation
                                .getCustomer()
                                .getPhone()
                                + "|"
                                + reservation
                                .getCustomer()
                                .getEmail()
                                + "|"
                                + reservation
                                .getRoom()
                                .getRoomNumber()
                                + "|"
                                + reservation
                                .getCheckIn()
                                + "|"
                                + reservation
                                .getCheckOut()
                                + "|"
                                + reservation
                                .getGuests()
                                + "|"
                                + reservation
                                .getTotalAmount()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving reservations: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // LOAD RESERVATIONS
    // =====================================================

    public List<Reservation> loadReservations(
            List<Room> rooms) {

        List<Reservation> reservations =
                new ArrayList<>();

        File file =
                new File(RESERVATION_FILE);

        if (!file.exists()) {

            return reservations;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while (
                    (line = reader.readLine())
                            != null
            ) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data =
                        line.split("\\|");

                if (data.length >= 10) {

                    String bookingId =
                            data[0];

                    int customerId =
                            Integer.parseInt(
                                    data[1]
                            );

                    String name =
                            data[2];

                    String phone =
                            data[3];

                    String email =
                            data[4];

                    int roomNumber =
                            Integer.parseInt(
                                    data[5]
                            );

                    String checkIn =
                            data[6];

                    String checkOut =
                            data[7];

                    int guests =
                            Integer.parseInt(
                                    data[8]
                            );

                    double totalAmount =
                            Double.parseDouble(
                                    data[9]
                            );

                    Room room = null;

                    for (Room r : rooms) {

                        if (
                                r.getRoomNumber()
                                        == roomNumber
                        ) {

                            room = r;
                            break;
                        }
                    }

                    if (room == null) {
                        continue;
                    }

                    Customer customer =
                            new Customer(
                                    customerId,
                                    name,
                                    phone,
                                    email
                            );

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

                    // Mark room as booked
                    room.setAvailable(false);
                }
            }

        } catch (
                IOException |
                NumberFormatException e
        ) {

            System.out.println(
                    "Error loading reservations: "
                            + e.getMessage()
            );
        }

        return reservations;
    }
}