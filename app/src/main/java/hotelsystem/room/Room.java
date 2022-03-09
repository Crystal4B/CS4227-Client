/**
 * @author Jakub Pažej
 * Abstract factory class for room implementation
 */
package hotelsystem.room;

import hotelsystem.user.User;
import java.lang.String;
import java.util.ArrayList;

public abstract class Room
{
    protected String roomName;                                     // Name of room as String
    protected int roomNumber;                                      // Room number and number of beds in it as int
    protected ArrayList<String> occupants;                         // List of users using the room

    public abstract void setRoomName(String roomName);
    public abstract String getRoomName();
    public abstract void setRoomNumber(int roomNumber);
    public abstract int getRoomNumber();
    public abstract void addOccupant(String occupant);
    public abstract void addOccupants(String[] occupants);
    public abstract void addOccupants(ArrayList<String> occupants);
    public abstract void removeOccupant(String occupant);
    public abstract void removeOccupants(String[] occupants);
    public abstract void removeOccupants(ArrayList<String> occupants);
    public abstract ArrayList<String>  getOccupants();
    public abstract String toString();                              // Returns a string with details about the room
}