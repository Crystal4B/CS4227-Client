//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem;

import java.util.ArrayList;

public class Standard extends Room
{
    String defaultPerks = "WiFi, TV, Toilet, Shower";
    boolean taken = false;
    ArrayList<Person> occupants = new ArrayList<Person>();

    Standard(String roomName, int roomNumber, int numberBeds)
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
    public void setOccupant(Person person) {
        occupants.add(person);
    }

    @Override
    public void setOccupants(Person[] people) {
        for (Person person : people)
        {
            occupants.add(person);
        }
    }

    @Override
    public void setOccupants(ArrayList<Person> people) {
        for (Person person : people)
        {
            occupants.add(person);
        }
    }

    @Override
    public void removeOccupant(Person person) {
        occupants.remove(person);
    }

    @Override
    public void removeOccupants(Person[] people) {
        for (Person person : people)
        {
            occupants.remove(person);
        }
    }

    @Override
    public void removeOccupants(ArrayList<Person> people) {
        for (Person person : people)
        {
            occupants.remove(person);
        }
    }

    @Override
    public ArrayList<Person> getOccupants() {
        return occupants;
    }
}