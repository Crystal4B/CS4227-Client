package order;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;

import hotelsystem.room.Standard;
import hotelsystem.user.User;

public class OrderBuilder implements Builder{

    private String orderID;
    private User user;
    private ArrayList<Standard> rooms = new ArrayList<>();
    private Timestamp startDate;
    private Timestamp endDate;
    private long numberOfDaysBooked;
    private double rateCost;
    private double finalCost;
    private int numberOfOccupants;

    @Override
    public void setOrderID(String orderID){
        this.orderID = orderID;
    }

    @Override
    public void setUser(User user){
        this.user = user;
    }

    @Override
    public void setRooms(ArrayList<Standard> rooms) {
        this.rooms = rooms;
        update();
    }

    @Override
    public void addRoom(Standard room){
        this.rooms.add(room);
        update();
    }

    @Override
    public void removeRoom(int room){
        this.rooms.remove(room);
        update();
    }

    @Override
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        update();
    }

    @Override
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        update();
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
        for(Standard r : this.rooms){
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
        for(Standard r : this.rooms){
            totalOccupants += r.getOccupants().size();
        }
        this.numberOfOccupants = totalOccupants;
    }

    private void update(){
        setRateCost();
        setNumberOfDaysBooked();
        setNumberOfOccupants();
        setFinalCost();
    }

    @Override
    public String toString() {
        String roomsDetails = "";
        for(Standard r : this.rooms){
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

    public ArrayList<Standard> getRoomsBuilder(){
        return this.rooms;
    }

    public int getRoomsArrayListSize(){
        return this.rooms.size();
    }

    public Order getOrder(){
        return new Order(orderID, user, rooms, startDate, endDate, numberOfDaysBooked, rateCost, finalCost, numberOfOccupants);
    }
    
}