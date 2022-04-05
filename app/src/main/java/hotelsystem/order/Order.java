package hotelsystem.order;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Objects;

import hotelsystem.roomFactory.Room;
import hotelsystem.userFactory.UserInterface;

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
     * @param orderID
     * @param userInterface
     * @param rooms
     * @param startDate
     * @param endDate
     * @param numberOfDaysBooked
     * @param rateCost
     * @param finalCost
     * @param numberOfOccupants
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
     * Get order ID.
     * @return An order ID as string.
     */
    public String getOrderID(){
        return orderID;
    }

    /**
     * Get order user.
     * @return A user object.
     */
    public UserInterface getUser(){
        return userInterface;
    }
 
    /**
     * Get list of rooms in order.
     * @return A list of rooms.
     */
    public ArrayList<Room> getRooms(){
        return rooms;
    }

    /**
     * Get start date.
     * @return A start date timestamp object.
     */
    public Timestamp getStartDate(){
        return startDate;
    }

    /**
     * Get end date.
     * @return A end date timestamp object.
     */
    public Timestamp getEndDate(){
        return endDate;
    }

    /**
     * Get number of days booked.
     * @return The total number of days booked as an integer.
     */
    public long getNumberOfDaysBooked(){
        return numberOfDaysBooked;
    }

    /**
     * Get rate cost. Rate of a single day
     * @return The rate cost as double.
     */
    public double getRateCost(){
        return rateCost;
    }

    /**
     * Get final cost. Cost of total stay.
     * @return The final cost as double.
     */
    public double getFinalCost(){
        return finalCost;
    }

    /**
     * Get number of occupants.
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
