package order;

import java.sql.Timestamp;
import java.util.ArrayList;

import hotelsystem.Room;

public class OrderBuilder implements Builder{

    private ArrayList<Room> rooms;
    private Timestamp startDate;
    private Timestamp endDate;
    private double finalCost;
    private int numberOfOccupants;

    @Override
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
        setFinalCost();
        setNumberOfOccupants();
    }

    @Override
    public void addRoom(Room room){
        this.rooms.add(room);
        setFinalCost();
        setNumberOfOccupants();
    }

    @Override
    public void removeRoom(Room room){
        this.rooms.remove(room);
        setFinalCost();
        setNumberOfOccupants();
    }

    @Override
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;  
    }

    @Override
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setFinalCost() {
        double totalCost = 0.0;
        for(Room r : this.rooms){
            totalCost += r.getPrice();
        }
        this.finalCost = totalCost;
    }

    @Override
    public void setNumberOfOccupants() {
        int totalOccupants = 0;
        for(Room r : this.rooms){
            totalOccupants += r.getOccupants().size();
        }
        this.numberOfOccupants = totalOccupants;
    }

    public Order getOrder(){
        return new Order(rooms, startDate, endDate, finalCost, numberOfOccupants);
    }
    
}