package hotelsystem.roomFactory;

/**
 * @author Jakub Pažej
 * Factory method for creating different types of rooms.
 */

public class RoomFactory implements FactoryInterface {
    public static Room createStandard(int roomNumber, int numberBeds) {
        Room room = new Room(roomNumber, numberBeds);
        room.setRoomName("Deluxe");
        return room;
    }

    public static Room createDeluxe(int roomNumber, int numberBeds) {
        Room room = new Room(roomNumber, numberBeds);
        room.removeAllOccupants();
        room.setRoomName("Deluxe");
        room.setRoomNumber(roomNumber);
        room.setPerks("Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
        room.setNumberBeds(numberBeds);
        room.setPrice(500);
        return room;
    }

    public static Room createVIP(int roomNumber, int numberBeds) {
        Room room = new Room(roomNumber, numberBeds);
        room.removeAllOccupants();
        room.setRoomName("VIP");
        room.setRoomNumber(roomNumber);
        room.setPerks("Private Gym, Private Swimming Pool, Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
        room.setNumberBeds(numberBeds);
        room.setPrice(1000);
        return room;
    }
}