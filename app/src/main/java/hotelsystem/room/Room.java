//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.room;

import hotelsystem.user.Person;

import java.lang.String;
import java.util.ArrayList;

public abstract class Room
{
    protected String roomName, perks;                              // name of room as String
    protected int roomNumber, numberBeds;                          // room number and number of beds in it as int
    protected double price;
    protected boolean taken;
    protected ArrayList<Person> occupants;

    public abstract void setRoomName(String roomName);
    public abstract String getRoomName();
    public abstract void setPerks(String perks);
    public abstract String getPerks();
    public abstract void setRoomNumber(int roomNumber);
    public abstract int getRoomNumber();
    public abstract void setNumberBeds(int numberBeds);
    public abstract int getNumberBeds();
    public abstract void setPrice(double price);
    public abstract double getPrice();
    public abstract void setTaken(boolean taken);
    public abstract boolean getTaken();
    public abstract void setOccupant(Person person);
    public abstract void setOccupants(Person[] people);
    public abstract void setOccupants(ArrayList<Person> people);
    public abstract void removeOccupant(Person person);
    public abstract void removeOccupants(Person[] people);
    public abstract void removeOccupants(ArrayList<Person> people);
    public abstract ArrayList<Person>  getOccupants();
}