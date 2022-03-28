package order;

import java.sql.Timestamp;

import java.util.ArrayList;
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

    public String getOrderID(){
        return orderID;
    }

    public UserInterface getUser(){
        return userInterface;
    }
 
    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public Timestamp getStartDate(){
        return startDate;
    }

    public Timestamp getEndDate(){
        return endDate;
    }

    public long getNumberOfDaysBooked(){
        return numberOfDaysBooked;
    }

    public double getRateCost(){
        return rateCost;
    }

    public double getFinalCost(){
        return finalCost;
    }

    public int getNumberOfOccupants(){
        return numberOfOccupants;
    }

    @Override
    public String toString() {
        String roomsDetails = "";
        for(Room r : this.rooms){
            roomsDetails += "\t" + r.toString() + "\n";
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

    @Override
    public boolean equals(Object obj){
        if((obj instanceof Order)){
            Order order = (Order)obj;
            if(
                this.orderID == order.getOrderID() &&
                this.userInterface == order.getUser() &&
                this.rooms == order.getRooms() &&
                this.startDate == order.getStartDate() &&
                this.endDate == order.getEndDate() &&
                this.numberOfDaysBooked == order.getNumberOfDaysBooked() &&
                this.rateCost == order.getRateCost() &&
                this.finalCost == order.getFinalCost() &&
                this.numberOfOccupants == order.getNumberOfOccupants()
            ){
            return true;
            }
        }
        return false;
    }

}
