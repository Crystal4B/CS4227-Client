package order;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hotelsystem.roomFactory.RoomInterface;
import hotelsystem.roomFactory.Room;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.reservations.CreateReservationCommand;
import requestsystem.commands.rooms.GetAvailableRoomsCommand;
import userinterface.ReservationUI;

public class Director {

    private Map<String, List<RoomInterface>> rooms;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;


    /**
     * `setDates` takes in a string representing the check in and check out dates and converts them to a
     * timestamp.
     * 
     * @param builder The builder object that will be used to create the order.
     * @param in The date you want to check in.
     * @param out The date you want to check out.
     * @return If sucessful or not.
     */
    public boolean setDates(OrderBuilder builder, String in, String out){
        try {
            this.checkInTime = Timestamp.valueOf(in + " 12:00:00");
            this.checkOutTime = Timestamp.valueOf(out + " 12:00:00");
            if(checkInTime.before(checkOutTime)){
                builder.setStartDate(this.checkInTime);
                builder.setEndDate(this.checkOutTime);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        } 
    }

    
    /**
     * Get a list of available rooms for the given check in and check out dates
     * 
     * @param builder The OrderBuilder object that is calling this method.
     * @return The list of room types available.
     */
    public boolean getAvailableRooms(OrderBuilder builder){
        if(rooms == null){
            CommandInvoker invoker = new CommandInvoker();
            invoker.setCommand(new GetAvailableRoomsCommand(this.checkInTime, this.checkOutTime));
            invoker.execute();
            rooms = invoker.getResponse();
        }
        
        Object[] roomTypes = rooms.keySet().toArray();
        
        if(roomTypes.length > 0){
            for(int i=0; i < roomTypes.length; i++){
                System.out.println(i + ".\t" +roomTypes[i]);
            }
            return true;
        }
        return false;
    }

    /**
     * Add a room to the order builder
     * 
     * @param console The console object that is used for input.
     * @param builder The OrderBuilder object that is being used to build the order.
     * @param option The index of the room in the list of rooms.
     */

    public void addRoom(Console console, OrderBuilder builder, int option){

        String roomKey = (String)rooms.keySet().toArray()[option];
        List<RoomInterface> roomValue = rooms.get(roomKey);

        int index = new Random().nextInt(roomValue.size());
        RoomInterface selectedRoom = roomValue.get(index);
        selectedRoom.addOccupants(ReservationUI.addGuests(console,((Room) selectedRoom).getNumberBeds()));
        builder.addRoom((Room)selectedRoom);
        
        roomValue.remove(index);
        if(roomValue.size() == 0){
            rooms.remove(roomKey);
        }
        else{
            rooms.put(roomKey, roomValue);
        }
    }

    /**
     * Remove a room from the order builder and add it to the room list
     * 
     * @param builder The OrderBuilder object that is being used to build the order.
     * @param option The index of the room in the order builder's list of rooms.
     */
    public void removeRoom(OrderBuilder builder, int option){
        ArrayList<Room> roomsInBuilder = builder.getRoomsBuilder();
        RoomInterface selectedRoom = roomsInBuilder.get(option);
        String roomKey = selectedRoom.getRoomName();

        builder.removeRoom(option);

        if(rooms.containsKey(roomKey)){
            List<RoomInterface> roomValue = rooms.get(roomKey);
            roomValue.add(selectedRoom);
            rooms.put(roomKey, roomValue);
        }
        else{
            ArrayList<RoomInterface> newRooms = new ArrayList<>();
            newRooms.add(selectedRoom);
            rooms.put(roomKey, newRooms);
        }
    
    }

    /**
     * ViewCart gets details of the builder
     * 
     * @param builder The OrderBuilder object that is used to build the order.
     * @return Builder string.
     */
    public String viewCart(OrderBuilder builder){
        return builder.toString();
    }

    public int viewRoomsInCart(OrderBuilder builder){
        for (int i=0; i < builder.getRoomsBuilder().size(); i++) 
        { 
            System.out.println(i + ".\t" + builder.getRoomsBuilder().get(i).toString() + "\n");
        }
        return builder.getRoomsBuilder().size();
    }

    /**
     * Add a reservation to the database
     * 
     * @param order The order that we want to add a reservation for.
     * @return Order or null
     */
    public Order addReservation(Order order){
        CommandInvoker invoker = new CommandInvoker();
        if(order.getFinalCost() != 0 && order.getNumberOfDaysBooked() !=0){
            invoker.setCommand(new CreateReservationCommand(order));
            invoker.execute();
            return invoker.getResponse();
        }
        return null;
    }
}