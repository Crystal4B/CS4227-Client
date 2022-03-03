package order;

import java.util.ArrayList;
import java.sql.Timestamp;

import hotelsystem.room.Room;

public class Director {
    public void constructOrder(Builder builder, ArrayList<Room> rooms){
        builder.setStartDate(Timestamp.valueOf("2022-12-1 00:00:00"));
        builder.setEndDate(Timestamp.valueOf("2022-12-5 00:00:00"));
        builder.setRooms(rooms);
    }
}
