package order;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import hotelsystem.Room;

public class Director {
    public void constructOrder(Builder builder, ArrayList<Room> rooms){
        builder.setRooms(rooms);
        builder.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        builder.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
    }
}
