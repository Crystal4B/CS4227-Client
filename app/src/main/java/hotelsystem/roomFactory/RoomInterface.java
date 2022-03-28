/**
 * @author Jakub Pažej
 * Interface for rooms for abstract factory method implementation.
 */
package hotelsystem.roomFactory;

import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.UserInterface;

import java.lang.String;
import java.util.ArrayList;

public interface RoomInterface
{
    public abstract void setRoomName(String roomName);
    public abstract String getRoomName();
    public abstract void setRoomNumber(int roomNumber);
    public abstract int getRoomNumber();
    public abstract void addOccupant(UserInterface occupant);
    public abstract void addOccupants(UserInterface[] occupants);
    public abstract void addOccupants(ArrayList<UserInterface> occupants);
    public abstract void removeOccupant(UserInterface occupant);
    public abstract void removeOccupants(UserInterface[] occupants);
    public abstract void removeOccupants(ArrayList<UserInterface> occupants);
    public abstract void removeAllOccupants();
    public abstract ArrayList<UserInterface>  getOccupants();
    public abstract String toString();                              // Returns a string with details about the room
}