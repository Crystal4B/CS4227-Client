package order;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Objects;

import hotelsystem.roomFactory.Room;
import hotelsystem.userFactory.UserInterface;

/**
 * Order class provides get functions for order objects.
 * @author Jordan Marshall
 */
public class Order {

    private final String orderID;
    private final UserInterface userInterface;
    private final ArrayList<Room> rooms;
    private final Timestamp startDate;
    private final Timestamp endDate;
    private final long numberOfDaysBooked;
    private final double rateCost;
    private final double finalCost;
    private final int numberOfOccupants;
    
    /**
     * Constructor for order object
     * @param orderID  A string of charaters to make up an order ID.
     * @param userInterface A user object that creates orders.
     * @param rooms A list of rooms in order.
     * @param startDate A check-in date for order.
     * @param endDate A check-out date for order.
     * @param numberOfDaysBooked The total amount of days booked for order. This is check-out minus check-in date.
     * @param rateCost This is the rate cost of a single day of order.
     * @param finalCost This is the final cost of the order.
     * @param numberOfOccupants This is the total number of occupants staying as part of the order.
     */
    public Order(String orderID, UserInterface userInterface, ArrayList<Room> rooms, Timestamp startDate, Timestamp endDate, long numberOfDaysBooked, double rateCost, double finalCost, int numberOfOccupants){
        this.orderID = orderID;
        this.userInterface = userInterface;
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDaysBooked = numberOfDaysBooked;
        this.rateCost = rateCost;
        this.finalCost = finalCost;
        this.numberOfOccupants = numberOfOccupants;
    }   

    /**
     * This is a function to get order ID of order object.
     * @return An order ID as string.
     */
    public String getOrderID(){
        return orderID;
    }

    /**
     * This is a function to get the user that creates an order.
     * @return A user object.
     */
    public UserInterface getUser(){
        return userInterface;
    }
 
    /**
     * This is a function that gets a list of rooms from the order object.
     * @return A list of rooms.
     */
    public ArrayList<Room> getRooms(){
        return rooms;
    }

    /**
     * This is a function to get check-in date on order.
     * @return A start date timestamp object.
     */
    public Timestamp getStartDate(){
        return startDate;
    }

    /**
     * This is a function to get check-out date on an order.
     * @return A end date timestamp object.
     */
    public Timestamp getEndDate(){
        return endDate;
    }

    /**
     * This is a function to get the total number of days booked on an order.
     * @return The total number of days booked as an integer.
     */
    public long getNumberOfDaysBooked(){
        return numberOfDaysBooked;
    }

    /**
     * This is a function to get cost of a single day of the order. 
     * @return The rate cost as double in EURO.
     */
    public double getRateCost(){
        return rateCost;
    }

    /**
     * This is a function to get totoal cost of the order. 
     * @return The final cost as double in EURO.
     */
    public double getFinalCost(){
        return finalCost;
    }

    /**
     * This is a function to get total number of occupants staying.
     * @return The total number of occupants as integer.
     */
    public int getNumberOfOccupants(){
        return numberOfOccupants;
    }

    /**
     * Order object to string function that override default implementation
     * @return An object as string.
     */
    @Override
    public String toString() {
        StringBuilder roomsDetails = new StringBuilder();
        for(Room r : this.rooms){
            roomsDetails.append("\t").append(r.toString()).append("\n");
        }

        return  "Rooms: \n" +
                roomsDetails +
                "\nNumber of Occupants: " + getNumberOfOccupants() + 
                "\nStart Date: " + getStartDate() + 
                "\nEnd Date: " + getEndDate() + 
                "\nNumber of Days Booked: " + getNumberOfDaysBooked() + 
                "\nRate Cost: EURO " + getRateCost() + 
                "\nTotal Cost: EURO " + getFinalCost() + 
                "\n";
    }

    /**
     * Order object equals function that override default implementation. Compares two order objects.
     */
    @Override
    public boolean equals(Object obj){
        if((obj instanceof Order order)){
            return Objects.equals(this.orderID, order.getOrderID()) &&
                    this.userInterface == order.getUser() &&
                    this.rooms == order.getRooms() &&
                    this.startDate == order.getStartDate() &&
                    this.endDate == order.getEndDate() &&
                    this.numberOfDaysBooked == order.getNumberOfDaysBooked() &&
                    this.rateCost == order.getRateCost() &&
                    this.finalCost == order.getFinalCost() &&
                    this.numberOfOccupants == order.getNumberOfOccupants();
        }
        return false;
    }

}
