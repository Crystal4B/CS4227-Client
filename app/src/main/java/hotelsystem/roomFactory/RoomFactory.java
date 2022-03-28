/**
 * @author Jakub Pažej
 * Factory method for creating different types of rooms.
 */
package hotelsystem.roomFactory;

public class RoomFactory implements FactoryInterface {
    public static Room createStandard(String roomName, int roomNumber, int numberBeds) {
        return new Room(roomName, roomNumber, numberBeds);
    }

    public static Room createDeluxe(String roomName, int roomNumber, int numberBeds) {
        Room room = new Room(roomName, roomNumber, numberBeds);
        room.removeAllOccupants();
        room.setRoomName(roomName);
        room.setRoomNumber(roomNumber);
        room.setPerks("Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
        room.setNumberBeds(numberBeds);
        room.setPrice(500);
        return room;
    }

    public static Room createVIP(String roomName, int roomNumber, int numberBeds) {
        Room room = new Room(roomName, roomNumber, numberBeds);
        room.removeAllOccupants();
        room.setRoomName(roomName);
        room.setRoomNumber(roomNumber);
        room.setPerks("Private Gym, Private Swimming Pool, Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
        room.setNumberBeds(numberBeds);
        room.setPrice(1000);
        return room;
    }
}