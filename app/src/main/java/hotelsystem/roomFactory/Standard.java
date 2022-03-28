/**
 * @author Jakub Pažej
 * Standard implementation of room
 */
package hotelsystem.roomFactory;

import hotelsystem.userFactory.UserFactory;

import java.util.ArrayList;

public class Standard implements RoomInterface
{
    protected String roomName;                                     // Name of room as String
    protected int roomNumber;                                      // Room number and number of beds in it as int
    private String perks = "WiFi, TV, Toilet, Shower";
    private boolean taken = false;
    private int numberBeds;
    private double price = 200.0;
    private ArrayList<UserFactory> occupants = new ArrayList<UserFactory>();

    public Standard(String roomName, int roomNumber, int numberBeds)
    {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.numberBeds = numberBeds;
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
    public void addOccupant(UserFactory user) {
        occupants.add(user);
    }

    @Override
    public void addOccupants(UserFactory[] people) {
        for (UserFactory userFactory : people)
        {
            occupants.add(userFactory);
        }
    }

    @Override
    public void addOccupants(ArrayList<UserFactory> people) {
        for (UserFactory userFactory : people)
        {
            occupants.add(userFactory);
        }
    }

    @Override
    public void removeOccupant(UserFactory userFactory) {
        occupants.remove(userFactory);
    }

    @Override
    public void removeOccupants(UserFactory[] people) {
        for (UserFactory userFactory : people)
        {
            occupants.remove(userFactory);
        }
    }

    @Override
    public void removeOccupants(ArrayList<UserFactory> people) {
        for (UserFactory userFactory : people)
        {
            occupants.remove(userFactory);
        }
    }

    @Override
    public void removeAllOccupants() {
        occupants.clear();
    }

    @Override
    public ArrayList<UserFactory> getOccupants() {
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