package order;

import java.sql.Timestamp;

import java.util.ArrayList;
import hotelsystem.Room;

public class Order {

    private final ArrayList<Room> rooms;
    private final Timestamp startDate;
    private final Timestamp endDate;
    private final double finalCost;
    private final int numberOfOccupants;
    
    public Order(ArrayList<Room> rooms, Timestamp startDate, Timestamp endDate, double finalCost, int numberOfOccupants){
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public double getFinalCost(){
        return finalCost;
    }

    public int getNumberOfOccupants(){
        return numberOfOccupants;
    }

    @Override
    public String toString() {
        return "Number of Occupants: " + getNumberOfOccupants() + "\tStart Date: " + getStartDate() + "\tEnd Date: " + getEndDate() + "\tCost: EURO " + getFinalCost() ;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Order)){
            return false;
        }
        return true;
    }

}
