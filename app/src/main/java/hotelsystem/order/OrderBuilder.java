package hotelsystem.order;

import hotelsystem.roomFactory.Room;
import hotelsystem.userFactory.UserInterface;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;

/**
 * OrderBuilder class provides implementation for builder.
 * @author Jordan Marshall
 */
public class OrderBuilder implements Builder{

    private String orderID;
    private UserInterface user;
    private ArrayList<Room> rooms = new ArrayList<>();
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
    public void setUser(UserInterface user){
        this.user = user;
    }

    @Override
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
        update();
    }

    @Override
    public void addRoom(Room room){
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

    /**
     * Updates set functions based on input from user.
     */
    private void update(){
        setRateCost();
        setNumberOfDaysBooked();
        setNumberOfOccupants();
        setFinalCost();
    }

    /**
     * Orderbuilder object to string function that override default implementation.
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
                "\nNumber of Occupants: " + this.numberOfOccupants +
                "\nStart Date: " + this.startDate +
                "\nEnd Date: " + this.endDate +
                "\nNumber of Days Booked: " + this.numberOfDaysBooked +
                "\nRate Cost: EURO " + this.rateCost +
                "\nTotal Cost: EURO " + this.finalCost +
                "\n";
    }

    /**
     * Gets rooms currently in order builder. This is not final.
     * @return A list of rooms.
     */
    public ArrayList<Room> getRoomsBuilder(){
        return this.rooms;
    }

    /**
     * Gets list of rooms size in order builder. This is not final.
     * @return A size of list of rooms as integer.
     */
    public int getRoomsArrayListSize(){
        return this.rooms.size();
    }

    /**
     * Gets order object. This is final.
     * @return An order object.
     */
    public Order getOrder(){
        return new Order(orderID, user, rooms, startDate, endDate, numberOfDaysBooked, rateCost, finalCost, numberOfOccupants);
    }

}