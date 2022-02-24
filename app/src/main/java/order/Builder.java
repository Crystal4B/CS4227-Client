package order;

import java.util.Date;

public interface Builder {
    void setStartDate(Date startDate);
    void setEndDate(Date endDate);
    void setFinalCost(int finalCost);
    void setNumberOfOccupants(int numberOfOccupants);
}
