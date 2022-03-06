/**
 * @author Jakub Pažej
 * Implementation of room for service and staff rooms like kitchens,storage rooms etc.
 */
package hotelsystem.room;

import java.util.ArrayList;
import hotelsystem.user.User;

public class Service extends Room
{
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<User> occupants = new ArrayList<User>();

    public Service(String roomName, int roomNumber)
    {
        setRoomName(roomName);
        setRoomNumber(roomNumber);
    }

    @Override
    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    @Override
    public String getRoomName()
    {
        return roomName;
    }

    @Override
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    @Override
    public int getRoomNumber()
    {
        return roomNumber;
    }

    @Override
    public void addOccupant(User user) {
        occupants.add(user);
    }

    @Override
    public void addOccupants(User[] people) {
        for (User user : people)
        {
            occupants.add(user);
        }
    }

    @Override
    public void addOccupants(ArrayList<User> people) {
        for (User user : people)
        {
            occupants.add(user);
        }
    }

    @Override
    public void removeOccupant(User user) {
        occupants.remove(user);
    }

    @Override
    public void removeOccupants(User[] people) {
        for (User user : people)
        {
            occupants.remove(user);
        }
    }

    @Override
    public void removeOccupants(ArrayList<User> people) {
        for (User user : people)
        {
            occupants.remove(user);
        }
    }

    @Override
    public ArrayList<User> getOccupants() {
        return occupants;
    }

    @Override
    public String toString() {
        return "Room Name: "+roomName+" ; Room Number: "+roomNumber;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void addItems(String[] items) {
        for (String item : items)
        {
            this.items.add(item);
        }
    }

    public void setOccupants(ArrayList<String> items) {
        for (String item : items)
        {
            this.items.add(item);
        }
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public void removeItems(String[] items) {
        for (String item : items)
        {
            this.items.remove(item);
        }
    }

    public void removeItems(ArrayList<String> items) {
        for (String item : items)
        {
            this.items.remove(item);
        }
    }

    public ArrayList<String> getItems() {
        return items;
    }
}