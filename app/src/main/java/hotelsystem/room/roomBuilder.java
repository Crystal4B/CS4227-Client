/**
 * @author Jakub Pažej
 * Standard implementation of room
 */
package hotelsystem.room;

public class roomBuilder
{
    private static Standard room = new Standard("", 0,0);

    public static Standard buildDeluxe(String roomName, int roomNumber, int numberBeds){
        room.removeAllOccupants();
        room.setRoomName(roomName);
        room.setRoomNumber(roomNumber);
        room.setPerks("Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
        room.setNumberBeds(numberBeds);
        room.setPrice(500);
        return room;
    }

    public static Standard buildVIP(String roomName, int roomNumber, int numberBeds){
        room.removeAllOccupants();
        room.setRoomName(roomName);
        room.setRoomNumber(roomNumber);
        room.setPerks("Private Gym, Private Swimming Pool, Private Sauna, Breakfast delivery to your room, WiFi, TV, Toilet, Shower");
        room.setNumberBeds(numberBeds);
        room.setPrice(1000);
        return room;
    }
}