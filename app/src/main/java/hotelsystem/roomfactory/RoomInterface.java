package hotelsystem.roomfactory;

import hotelsystem.userfactory.UserInterface;

import java.lang.String;
import java.util.ArrayList;

/**
 * @author Jakub Pažej
 * Interface for rooms for abstract factory method implementation.
 */
public interface RoomInterface
{
    /**
     * Sets a room name.
     * @param roomName new name for the room
     */
    void setRoomName(String roomName);

    /**
     * @return a room name as String
     */
    String getRoomName();

    /**
     * Sets a room number.
     * @param roomNumber new room number
     */
    void setRoomNumber(int roomNumber);

    /**
     * @return a room number as int
     */
    int getRoomNumber();

    /**
     * Adds a single occupant
     * @param occupant of type UserInterface
     */
    void addOccupant(UserInterface occupant);

    /**
     * Adds multiple occupants
     * @param occupants of type UserInterface[]
     */
    void addOccupants(UserInterface[] occupants);

    /**
     * Adds multiple occupants
     * @param occupants of type ArrayList<UserInterface>
     */
    void addOccupants(ArrayList<UserInterface> occupants);

    /**
     * Removes a single occupant
     * @param occupant of type UserInterface
     */
    void removeOccupant(UserInterface occupant);

    /**
     * removes multiple occupants
     * @param occupants of type UserInterface[]
     */
    void removeOccupants(UserInterface[] occupants);

    /**
     * Removes multiple occupants
     * @param occupants of type ArrayList<UserInterface>
     */
    void removeOccupants(ArrayList<UserInterface> occupants);

    /**
     * Removes all occupants.
     */
    void removeAllOccupants();

    /**
     * @return Occupants as ArrayList<UserInterface>
     */
    ArrayList<UserInterface>  getOccupants();

    /**
     * @return Room details as String
     */
    String toString();                              // Returns a string with details about the room
}