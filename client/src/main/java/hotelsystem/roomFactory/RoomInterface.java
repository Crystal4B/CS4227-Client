package hotelsystem.roomFactory;

import hotelsystem.userFactory.UserInterface;

import java.lang.String;
import java.util.ArrayList;

/**
 * @author Jakub Pažej
 * Interface for rooms for abstract factory method implementation.
 */

public interface RoomInterface
{
    void setRoomName(String roomName);
    String getRoomName();
    void setRoomNumber(int roomNumber);
    int getRoomNumber();
    void addOccupant(UserInterface occupant);
    void addOccupants(UserInterface[] occupants);
    void addOccupants(ArrayList<UserInterface> occupants);
    void removeOccupant(UserInterface occupant);
    void removeOccupants(UserInterface[] occupants);
    void removeOccupants(ArrayList<UserInterface> occupants);
    void removeAllOccupants();
    ArrayList<UserInterface>  getOccupants();
    String toString();                              // Returns a string with details about the room
}