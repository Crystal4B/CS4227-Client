package order;

import java.util.Date;

public class Director {
    public void constructOrder(Builder builder){
        builder.setStartDate(new Date());
        builder.setEndDate(new Date());
        builder.setFinalCost(500);
        builder.setNumberOfOccupants(5);
    }
}
