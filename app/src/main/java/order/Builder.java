package order;

import java.util.ArrayList;
import java.sql.Timestamp;
import hotelsystem.roomFactory.Standard;
import hotelsystem.userFactory.UserFactory;

public interface Builder {
    void setOrderID(String orderID);
    void setUser(UserFactory userFactory);
    void setRooms(ArrayList<Standard> rooms);
    void addRoom(Standard room);
    void removeRoom(int room);
    void setStartDate(Timestamp startDate);
    void setEndDate(Timestamp endDate);
    void setNumberOfDaysBooked();
    void setRateCost();
    void setFinalCost();
    void setNumberOfOccupants();
}
