package order;

import java.sql.Timestamp;

import java.util.ArrayList;
import hotelsystem.Room;

public class Order {

    private final ArrayList<Room> rooms;
    private final Timestamp startDate;
    private final Timestamp endDate;
    private final long numberOfDaysBooked;
    private final double rateCost;
    private final double finalCost;
    private final int numberOfOccupants;
    
    public Order(ArrayList<Room> rooms, Timestamp startDate, Timestamp endDate, long numberOfDaysBooked, double rateCost, double finalCost, int numberOfOccupants){
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDaysBooked = numberOfDaysBooked;
        this.rateCost = rateCost;
        this.finalCost = finalCost;
        this.numberOfOccupants = numberOfOccupants;
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
        if(!(obj instanceof Order)){
            return false;
        }
        return true;
    }

}
