package service;

import model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private List<Room> rooms;

    public RoomService() {

        rooms =
                new ArrayList<>();

        addDefaultRooms();
    }

    private void addDefaultRooms() {

        rooms.add(
                new Room(
                        101,
                        "Standard",
                        2500,
                        true
                )
        );

        rooms.add(
                new Room(
                        102,
                        "Standard",
                        2500,
                        true
                )
        );

        rooms.add(
                new Room(
                        103,
                        "Standard",
                        2500,
                        true
                )
        );

        rooms.add(
                new Room(
                        201,
                        "Deluxe",
                        4000,
                        true
                )
        );

        rooms.add(
                new Room(
                        202,
                        "Deluxe",
                        4000,
                        true
                )
        );

        rooms.add(
                new Room(
                        203,
                        "Deluxe",
                        4000,
                        true
                )
        );

        rooms.add(
                new Room(
                        301,
                        "Suite",
                        7000,
                        true
                )
        );

        rooms.add(
                new Room(
                        302,
                        "Suite",
                        7000,
                        true
                )
        );
    }

    public void setRooms(
            List<Room> loadedRooms) {

        if (loadedRooms == null ||
                loadedRooms.isEmpty()) {

            return;
        }

        rooms =
                new ArrayList<>(
                        loadedRooms
                );
    }

    public List<Room> getAllRooms() {

        return rooms;
    }

    public List<Room> getAvailableRooms() {

        List<Room> available =
                new ArrayList<>();

        for (Room room : rooms) {

            if (room.isAvailable()) {

                available.add(room);
            }
        }

        return available;
    }

    public List<Room> searchByCategory(
            String category) {

        List<Room> result =
                new ArrayList<>();

        for (Room room : rooms) {

            if (room.getCategory()
                    .equalsIgnoreCase(
                            category
                    )
                    && room.isAvailable()) {

                result.add(room);
            }
        }

        return result;
    }

    public Room findRoom(
            int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber()
                    == roomNumber) {

                return room;
            }
        }

        return null;
    }

    public boolean bookRoom(
            int roomNumber) {

        Room room =
                findRoom(
                        roomNumber
                );

        if (room != null &&
                room.isAvailable()) {

            room.setAvailable(false);

            return true;
        }

        return false;
    }

    public boolean releaseRoom(
            int roomNumber) {

        Room room =
                findRoom(
                        roomNumber
                );

        if (room != null) {

            room.setAvailable(true);

            return true;
        }

        return false;
    }
}