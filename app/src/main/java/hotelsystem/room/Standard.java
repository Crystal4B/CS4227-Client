//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.room;

import hotelsystem.user.User;

import java.util.ArrayList;

public class Standard extends Room
{
    String defaultPerks = "WiFi, TV, Toilet, Shower";
    boolean taken = false;
    ArrayList<User> occupants = new ArrayList<User>();

    public Standard(String roomName, int roomNumber, int numberBeds)
    {
        setRoomName(roomName);
        setPerks(defaultPerks);
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
    public void setPerks(String perks)
    {
        this.perks = perks;
    }

    @Override
    public String getPerks()
    {
        return perks;
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
    public void setNumberBeds(int numberBeds)
    {
        this.numberBeds = numberBeds;
    }

    @Override
    public int getNumberBeds()
    {
        return numberBeds;
    }

    @Override
    public void setPrice(double price)
    {
        this.price=price;
    }

    @Override
    public double getPrice()
    {
        return price;
    }

    @Override
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public boolean getTaken() {
        return taken;
    }

    @Override
    public void setOccupant(User user) {
        occupants.add(user);
    }

    @Override
    public void setOccupants(User[] people) {
        for (User user : people)
        {
            occupants.add(user);
        }
    }

    @Override
    public void setOccupants(ArrayList<User> people) {
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
}