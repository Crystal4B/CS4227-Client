package order;

import java.util.ArrayList;
import java.util.Date;
import hotelsystem.Room;

public interface Builder {
    void setRooms(ArrayList<Room> rooms);
    void addRoom(Room room);
    void removeRoom(Room room);
    void setStartDate(Date startDate);
    void setEndDate(Date endDate);
    void setFinalCost();
    void setNumberOfOccupants();
}
