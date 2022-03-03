package order;

import java.util.ArrayList;
import java.sql.Timestamp;
import hotelsystem.room.Room;

public interface Builder {
    void setRooms(ArrayList<Room> rooms);
    void addRoom(Room room);
    void removeRoom(Room room);
    void setStartDate(Timestamp startDate);
    void setEndDate(Timestamp endDate);
    void setNumberOfDaysBooked();
    void setRateCost();
    void setFinalCost();
    void setNumberOfOccupants();
}
