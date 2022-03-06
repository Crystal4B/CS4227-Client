/**
 * @author Jakub Pažej
 * Standard implementation of room
 */
package hotelsystem.room;

import java.util.ArrayList;
import hotelsystem.user.User;

public class Standard extends Room
{
    private String perks = "WiFi, TV, Toilet, Shower";
    private boolean taken = false;
    private int numberBeds;
    private double price;
    private ArrayList<User> occupants = new ArrayList<User>();

    public Standard(String roomName, int roomNumber, int numberBeds)
    {
        setRoomName(roomName);
        setPerks(perks);
        setRoomNumber(roomNumber);
        setNumberBeds(numberBeds);
        setPrice(200.00);
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

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean getTaken() {
        return taken;
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
        return "Room Name: "+roomName+" ; Room Number: "+roomNumber+" ; Beds: "+numberBeds+" ; Price: "+price+" ; Taken?: "+taken;
    }

    public void setPerks(String perks)
    {
        this.perks = perks;
    }

    public String getPerks()
    {
        return perks;
    }

    public void setNumberBeds(int numberBeds)
    {
        this.numberBeds = numberBeds;
    }

    public int getNumberBeds()
    {
        return numberBeds;
    }

    public void setPrice(double price)
    {
        this.price=price;
    }

    public double getPrice()
    {
        return price;
    }
}