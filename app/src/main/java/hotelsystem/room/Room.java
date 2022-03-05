//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.room;

import hotelsystem.user.User;

import java.lang.String;
import java.util.ArrayList;

public abstract class Room
{
    protected String roomName, perks;                              // name of room as String
    protected int roomNumber, numberBeds;                          // room number and number of beds in it as int
    protected double price;
    protected boolean taken;
    protected ArrayList<User> occupants;

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
    public abstract void setOccupant(User user);
    public abstract void setOccupants(User[] people);
    public abstract void setOccupants(ArrayList<User> people);
    public abstract void removeOccupant(User user);
    public abstract void removeOccupants(User[] people);
    public abstract void removeOccupants(ArrayList<User> people);
    public abstract ArrayList<User>  getOccupants();
    public abstract String toString();
}