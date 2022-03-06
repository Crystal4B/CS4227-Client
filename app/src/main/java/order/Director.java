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
                return true;
            default:
                return false;
        }
    }

    public boolean removeRoomUsingUI(OrderBuilder builder, int option){
        if(option == 0){
            return true;
        }
        else if(option > 0 && option < builder.getRoomsArrayListSize()+1){
            builder.removeRoom(option-1);
            return true;
        }
        return false;
    }

    public String viewCart(OrderBuilder builder){
        return builder.toString();
    }

    public String viewRoomsInCart(OrderBuilder builder){
        String roomsInCart = "";
        roomsInCart += "0.\t Back\n\n";
        for (int i=0; i < builder.getRoomsBuilder().size(); i++) 
        { 
            roomsInCart += (i+1) + ".\t" + builder.getRoomsBuilder().get(i).toString() + "\n";
        }
        return roomsInCart;
    }
}