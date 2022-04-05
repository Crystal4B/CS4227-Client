package hotelsystem.order;

import java.util.ArrayList;
import java.sql.Timestamp;
import hotelsystem.roomFactory.Room;
import hotelsystem.userFactory.UserInterface;

/**
* Interface for set functions for order class
*/
public interface Builder {
    void setOrderID(String orderID);

    void setUser(UserInterface userInterface);

    void setRooms(ArrayList<Room> rooms);

    void addRoom(Room room);

    void removeRoom(int room);

    void setStartDate(Timestamp startDate);

    void setEndDate(Timestamp endDate);

    void setNumberOfDaysBooked();

    void setRateCost();

    void setFinalCost();

    void setNumberOfOccupants();
}
