package hotelsystem.roomfactory;

import hotelsystem.userfactory.UserInterface;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Jakub Pažej
 * Standard implementation of room.
 */
public class Room implements RoomInterface
{
    protected String roomName;                                     // Name of room as String
    protected int roomNumber;                                      // Room number and number of beds in it as int
    private String perks = "WiFi, TV, Toilet, Shower";
    private boolean taken = false;
    private int numberBeds;
    private double price = 200.0;
    private ArrayList<UserInterface> occupants = new ArrayList<>();

	/**
	 * Simple room constructor
	 * @param roomNumber the id of the room
	 * @param numberBeds in the room
	 */
    public Room(int roomNumber, int numberBeds)
    {
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

	/**
	 * Simple Setter for the taken variable
	 * @param taken whether a room is taken or not
	 */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

	/**
	 * Simple getter for the taken variable
	 * @return taken as a boolean
	 */
    public boolean getTaken() {
        return taken;
    }

    @Override
    public void addOccupant(UserInterface user) {
        occupants.add(user);
    }

    @Override
    public void addOccupants(UserInterface[] people) {
        Collections.addAll(occupants, people);
    }

    @Override
    public void addOccupants(ArrayList<UserInterface> people) {
        occupants.addAll(people);
    }

    @Override
    public void removeOccupant(UserInterface userInterface) {
        occupants.remove(userInterface);
    }

    @Override
    public void removeOccupants(UserInterface[] people) {
        for (UserInterface userInterface : people)
        {
            occupants.remove(userInterface);
        }
    }

    @Override
    public void removeOccupants(ArrayList<UserInterface> people) {
        for (UserInterface userInterface : people)
        {
            occupants.remove(userInterface);
        }
    }

    @Override
    public void removeAllOccupants() {
        occupants.clear();
    }

    @Override
    public ArrayList<UserInterface> getOccupants() {
        return occupants;
    }

    @Override
    public String toString() {
        return "Room Name - "+roomName+" ; Room Number - "+roomNumber+" ; Beds - "+numberBeds+" ; Price - "+price+" ; Taken - "+taken;
    }

	/**
	 * Simple setter for the perks variable
	 * @param perks new perks as a string
	 */
    public void setPerks(String perks)
    {
        this.perks = perks;
    }

	/**
	 * Simple perk getter
	 * @return perks as a string
	 */
    public String getPerks()
    {
        return perks;
    }

	/**
	 * Simple number of beds setter
	 * @param numberBeds in the room as int
	 */
    public void setNumberBeds(int numberBeds)
    {
        this.numberBeds = numberBeds;
    }

	/**
	 * Simple getter for number of beds
	 * @return the number of beds as an int
	 */
    public int getNumberBeds()
    {
        return numberBeds;
    }

	/**
	 * Simple price setter
	 * @param price new price of room
	 */
    public void setPrice(double price)
    {
        this.price=price;
    }

	/**
	 * Simple getter for price
	 * @return price of room as double
	 */
    public double getPrice()
    {
        return price;
    }
}