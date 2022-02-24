package order;

import java.util.ArrayList;
import java.util.Date;

import hotelsystem.Room;

public class Director {
    public void constructOrder(Builder builder, ArrayList<Room> rooms){
        builder.setStartDate(new Date());
        builder.setEndDate(new Date());
        builder.setFinalCost(rooms);
        builder.setNumberOfOccupants(rooms);
    }
}
