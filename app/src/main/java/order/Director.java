package order;

import java.sql.*;

import hotelsystem.room.*;

public class Director {
    
    public boolean setDatesUsingUI(Builder builder, String checkInDate, String checkOutDate){
        try {
            if(Timestamp.valueOf(checkInDate + " 12:00:00").before(Timestamp.valueOf(checkOutDate + " 12:00:00"))){
                builder.setStartDate(Timestamp.valueOf(checkInDate + " 12:00:00"));
                builder.setEndDate(Timestamp.valueOf(checkOutDate + " 12:00:00"));
                return true;
            }
            else{
                return false;  
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addRoomUsingUI(Builder builder, int option){
        switch (option) {
            case 1: 
                builder.addRoom(new Standard("Test Name", 123, 2));
                return true;
            case 2: 
                builder.addRoom(new Deluxe("Test Name", 123, 2));
                return true;
            case 3: 
                builder.addRoom(new VIP("Test Name", 123, 2));
                return true;
            case 4: 
                return true;
            default:
                return false;
        }
    }

    public void removeRoomUsingUI(Builder builder){
        // TODO: Add Implementation
    }

    public String viewCart(OrderBuilder builder){
        return builder.toString();
    }
}