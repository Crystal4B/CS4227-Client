/**
 * @author Jakub Pažej
 * Standard implementation of room
 */
package hotelsystem.roomFactory;

import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.Guest;
import hotelsystem.userFactory.Staff;
import hotelsystem.userFactory.UserInterface;

public class RoomFactory {
    public static RoomInterface createRoom(String type, String roomName, int roomNumber, int numberBeds) {
        Standard room = new Standard("", 0, 0);

        if (type.equals("standard")) {
            return new Standard(roomName, roomNumber, numberBeds);
        }
        else if (type.equals("deluxe")) {
            room.removeAllOccupants();
            room.setRoomName(roomName);
            room.setRoomNumber(roomNumber);
            room.setPerks("Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
            room.setNumberBeds(numberBeds);
            room.setPrice(500);
            return room;
        }
        else if (type.equals("vip")) {
            room.removeAllOccupants();
            room.setRoomName(roomName);
            room.setRoomNumber(roomNumber);
            room.setPerks("Private Gym, Private Swimming Pool, Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
            room.setNumberBeds(numberBeds);
            room.setPrice(1000);
            return room;
        }
        return null;
    }
}