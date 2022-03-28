/**
 * @author Jakub Pažej
 * Abstract factory class for room implementation
 */
package hotelsystem.roomFactory;

import hotelsystem.userFactory.UserFactory;

import java.lang.String;
import java.util.ArrayList;

public interface RoomInterface
{
    public abstract void setRoomName(String roomName);
    public abstract String getRoomName();
    public abstract void setRoomNumber(int roomNumber);
    public abstract int getRoomNumber();
    public abstract void addOccupant(UserFactory occupant);
    public abstract void addOccupants(UserFactory[] occupants);
    public abstract void addOccupants(ArrayList<UserFactory> occupants);
    public abstract void removeOccupant(UserFactory occupant);
    public abstract void removeOccupants(UserFactory[] occupants);
    public abstract void removeOccupants(ArrayList<UserFactory> occupants);
    public abstract void removeAllOccupants();
    public abstract ArrayList<UserFactory>  getOccupants();
    public abstract String toString();                              // Returns a string with details about the room
}