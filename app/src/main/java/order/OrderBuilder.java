package order;

import java.util.ArrayList;
import java.util.Date;

import hotelsystem.Room;

public class OrderBuilder implements Builder{

    private ArrayList<Room> rooms;
    private Date startDate;
    private Date endDate;
    private double finalCost;
    private int numberOfOccupants;

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;  
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setFinalCost(ArrayList<Room> rooms) {
        double totalCost = 0.0;
        for(Room r : rooms){
            totalCost += r.getPrice();
        }
        this.finalCost = totalCost;
    }

    @Override
    public void setNumberOfOccupants(ArrayList<Room> rooms) {
        int totalOccupants = 0;
        for(Room r : rooms){
            totalOccupants += r.getOccupants().size();
        }
        this.numberOfOccupants = totalOccupants;
    }

    public Order getOrder(){
        return new Order(startDate, endDate, finalCost, numberOfOccupants);
    }
    
}