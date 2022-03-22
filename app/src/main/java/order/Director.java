package order;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.room.*;
import hotelsystem.user.Customer;
import hotelsystem.user.User;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.CreateReservationCommand;
import requestsystem.commands.GetAvailableRoomsCommand;
import userinterface.ReservationUI;

public class Director {

    private Map<String, List<Room>> rooms;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;

    public void setDates(OrderBuilder builder, String in, String out){ 
        this.checkInTime = Timestamp.valueOf(in + " 12:00:00");
        this.checkOutTime = Timestamp.valueOf(out + " 12:00:00");
        builder.setStartDate(this.checkInTime);
        builder.setEndDate(this.checkOutTime);
    }

    public String getAvailableRooms(OrderBuilder builder){
        if(rooms == null){
            CommandInvoker invoker = new CommandInvoker();
            invoker.setCommand(new GetAvailableRoomsCommand(this.checkInTime, this.checkOutTime));
            invoker.execute();
            rooms = invoker.getResponse();
        }
        
        Object[] roomTypes = rooms.keySet().toArray();
        
        String listOfRoomOptions = "";
        for(int i=0; i < roomTypes.length; i++){
            listOfRoomOptions += i + ".\t" + roomTypes[i] + "\n";
        }
        return listOfRoomOptions;
    }

    public void addRoom(Console console, OrderBuilder builder, int option){

        String roomKey = (String)rooms.keySet().toArray()[option];
        List<Room> roomValue = rooms.get(roomKey);

        int index = (int)Math.random()*roomValue.size();
        Room selectedRoom = roomValue.get(index);
        selectedRoom.addOccupants(ReservationUI.addGuests(console,((Standard) selectedRoom).getNumberBeds()));
        builder.addRoom((Standard)selectedRoom);
        
        roomValue.remove(index);
        if(roomValue.size() == 0){
            rooms.remove(roomKey);
        }
        else{
            rooms.put(roomKey, roomValue);
        }
    }

    public void removeRoom(OrderBuilder builder, int option){
        ArrayList<Standard> roomsInBuilder = builder.getRoomsBuilder();
        Room selectedRoom = roomsInBuilder.get(option);
        String roomKey = selectedRoom.getRoomName();

        builder.removeRoom(option);

        if(rooms.containsKey(roomKey)){
            List<Room> roomValue = rooms.get(roomKey);
            roomValue.add(selectedRoom);
            rooms.put(roomKey, roomValue);
        }
        else{
            ArrayList<Room> newRooms = new ArrayList<>();
            newRooms.add(selectedRoom);
            rooms.put(roomKey, newRooms);
        }
    
    }

    public String viewCart(OrderBuilder builder){
        return builder.toString();
    }

    public String viewRoomsInCart(OrderBuilder builder){
        String roomsInCart = "";
        for (int i=0; i < builder.getRoomsBuilder().size(); i++) 
        { 
            roomsInCart += i + ".\t" + builder.getRoomsBuilder().get(i).toString() + "\n";
        }
        return roomsInCart;
    }

    public Order addReservation(Order order){
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(new CreateReservationCommand(order));
        invoker.execute();
        return invoker.getResponse();
    }
}