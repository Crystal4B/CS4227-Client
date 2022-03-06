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
    protected ArrayList<User> occupants;                           // List of users using the room

    public abstract void setRoomName(String roomName);
    public abstract String getRoomName();
    public abstract void setRoomNumber(int roomNumber);
    public abstract int getRoomNumber();
    public abstract void addOccupant(User user);
    public abstract void addOccupants(User[] people);
    public abstract void addOccupants(ArrayList<User> people);
    public abstract void removeOccupant(User user);
    public abstract void removeOccupants(User[] people);
    public abstract void removeOccupants(ArrayList<User> people);
    public abstract ArrayList<User>  getOccupants();
    public abstract String toString();                              // Returns a string with details about the room
}