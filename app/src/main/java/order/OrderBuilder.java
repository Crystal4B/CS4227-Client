package order;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;

import hotelsystem.room.Room;

public class OrderBuilder implements Builder{

    private ArrayList<Room> rooms = new ArrayList<>();
    private Timestamp startDate;
    private Timestamp endDate;
    private long numberOfDaysBooked;
    private double rateCost;
    private double finalCost;
    private int numberOfOccupants;

    @Override
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
        setRateCost();
        setNumberOfOccupants();
        setFinalCost();
    }

    @Override
    public void addRoom(Room room){
        this.rooms.add(room);
        setRateCost();
        setNumberOfOccupants();
        setFinalCost();
    }

    @Override
    public void removeRoom(Room room){
        this.rooms.remove(room);
        setRateCost();
        setNumberOfOccupants();
        setFinalCost();
    }

    @Override
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        setRateCost();
        setNumberOfDaysBooked();
        setFinalCost();
    }

    @Override
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        setRateCost();
        setNumberOfDaysBooked();
        setFinalCost();
    }

    @Override
    public void setNumberOfDaysBooked() {
        if(this.startDate != null && this.endDate != null){
            this.numberOfDaysBooked = Duration.between(this.startDate.toLocalDateTime(), this.endDate.toLocalDateTime()).toDays();
        }
    }

    @Override
    public void setRateCost() {
        double totalCost = 0.0;
        for(Room r : this.rooms){
            totalCost += r.getPrice();
        }
        this.rateCost = totalCost;
    }

    @Override
    public void setFinalCost() {
        this.finalCost = this.rateCost * this.numberOfDaysBooked;
    }

    @Override
    public void setNumberOfOccupants() {
        int totalOccupants = 0;
        for(Room r : this.rooms){
            totalOccupants += r.getOccupants().size();
        }
        this.numberOfOccupants = totalOccupants;
    }

    @Override
    public String toString() {
        String roomsDetails = "";
        for(Room r : this.rooms){
            roomsDetails += "\t" + r.toString() + "\n";
        }

        return  "Rooms: \n" +
                roomsDetails +
                "\nNumber of Occupants: " + this.numberOfOccupants + 
                "\nStart Date: " + this.startDate + 
                "\nEnd Date: " + this.endDate + 
                "\nNumber of Days Booked: " + this.numberOfDaysBooked + 
                "\nRate Cost: EURO " + this.rateCost + 
                "\nTotal Cost: EURO " + this.finalCost + 
                "\n";
    }

    public Order getOrder(){
        return new Order(rooms, startDate, endDate, numberOfDaysBooked, rateCost, finalCost, numberOfOccupants);
    }
    
}