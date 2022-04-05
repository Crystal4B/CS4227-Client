package hotelsystem.order;

import java.util.ArrayList;
import java.sql.Timestamp;
import hotelsystem.roomfactory.Room;
import hotelsystem.userfactory.UserInterface;

/**
 * This is an interface for builder concrete classes. Implementation is in concrete classes.
 * @author Jordan Marshall
 */
public interface Builder {

    /**
     * This is a function to set the order ID parameter in the builder class.
     * @param orderID A string of charaters to make up an order ID.
     */
    void setOrderID(String orderID);

    /**
     * This is a function to set the user parameter in the builder class.
     * @param userInterface A user that is going to be set.
     */
    void setUser(UserInterface userInterface);

    /**
     * This is a function to set the rooms parameter in the builder class.
     * @param rooms. A list of rooms to be set as parameter.
     */
    void setRooms(ArrayList<Room> rooms);

    /**
     * This is a function to add a single room to the builder rooms arraylist parameter.
     * @param room A room object that can be added to existing rooms list.
     */
    void addRoom(Room room);

    /**
     * This is a function to remove a single room from the builder rooms arraylist parameter.
     * @param room An integer option from list on menu that can be removed from existing rooms list.
     */
    void removeRoom(int room);

    /**
     * This is a function to set start date on order/reservation. This represents check-in date of reservation.
     * @param startDate A timestamp object to represent check-in date of reservation.
     */
    void setStartDate(Timestamp startDate);

    /**
     * This is a function to set end date on order/reservation. This represents check-out date of reservation.
     * @param endDate A timestamp object to represent check-out date of reservation.
     */
    void setEndDate(Timestamp endDate);

    /**
     * This is a function to set number of days booked in builder. Triggered using update function.
     */
    void setNumberOfDaysBooked();

    /**
     * This is a fucntion to set rate cost in builder. Triggered using update function.
     */
    void setRateCost();

    /**
     * This is a function to set final cost in builder. Triggered using update function.
     */
    void setFinalCost();

    /**
     * This is a function to set number of occupants in builder. Triggered using update function.
     */
    void setNumberOfOccupants();
}
