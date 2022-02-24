package order;

import java.util.ArrayList;
import java.util.Date;
import hotelsystem.Room;

public interface Builder {
    void setStartDate(Date startDate);
    void setEndDate(Date endDate);
    void setFinalCost(ArrayList<Room> rooms);
    void setNumberOfOccupants(ArrayList<Room> rooms);
}
