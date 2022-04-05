package hotelsystem.order;

import hotelsystem.roomFactory.Room;
import hotelsystem.userFactory.UserInterface;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;

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

    /**
     * Set order ID in order builder.
     * @param orderID An order ID as string.
     */
    @Override
    public void setOrderID(String orderID){
        this.orderID = orderID;
    }

    /**
     * Set user in order builder.
     * @param user A user object
     */
    @Override
    public void setUser(UserInterface user){
        this.user = user;
    }

    /**
     * Set rooms in order builder.
     * @param rooms A list of rooms.
     */
    @Override
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
        update();
    }

    /**
     * Add a room to order builder.
     * @param room A room object.
     */
    @Override
    public void addRoom(Room room){
        this.rooms.add(room);
        update();
    }

    /**
     * Remove a room to order builder.
     * @param room An integer option of room to remove from rooms list.
     */
    @Override
    public void removeRoom(int room){
        this.rooms.remove(room);
        update();
    }

    /**
     * Set start date in order builder.
     * @param startDate A start date of order as timestamp object.
     */
    @Override
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        update();
    }

    /**
     * Set end date in order builder.
     * @param endDate A end date of order as timestamp object.
     */
    @Override
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        update();
    }

    /**
     * Set number of days booked in order builder. Triggered using update function.
     */
    @Override
    public void setNumberOfDaysBooked() {
        if(this.startDate != null && this.endDate != null){
            this.numberOfDaysBooked = Duration.between(this.startDate.toLocalDateTime(), this.endDate.toLocalDateTime()).toDays();
        }
    }

    /**
     * Set rate cost in order builder. Triggered using update function.
     */
    @Override
    public void setRateCost() {
        double totalCost = 0.0;
        for(Room r : this.rooms){
            totalCost += r.getPrice();
        }
        this.rateCost = totalCost;
    }

    /**
     * Set final cost in order builder. Triggered using update function.
     */
    @Override
    public void setFinalCost() {
        this.finalCost = this.rateCost * this.numberOfDaysBooked;
    }

    /**
     * Set number of occupants in order builder. Triggered using update function.
     */
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