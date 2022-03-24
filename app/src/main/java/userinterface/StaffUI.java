package userinterface;

import java.io.Console;
import java.util.ArrayList;

import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.CreateRoomCommand;
import requestsystem.commands.GetAllRoomsCommand;
import requestsystem.commands.RemoveRoomCommand;

public class StaffUI {
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Create Reservation");
        System.out.println("2. \t View Reservations");
        System.out.println("3. \t Manage Rooms");
        System.out.println("4. \t Manage Staff");
        System.out.println("5. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = -1;
        try {
            option = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return UI.STAFF_MENU;
        }
        switch (option) {
            case 1:
                return UI.RESERVATION_STATE;
            case 2: 
                return UI.STAFF_MENU;
            case 3:  
                while(true){
                    if(manageRooms(console)){
                        break;
                    }
                }
                return UI.STAFF_MENU;
            case 4:  
                return UI.STAFF_MENU;
            case 5:  
                return UI.EXIT;
            default:
                return UI.STAFF_MENU;
        }
    }

    public static Boolean manageRooms(Console console){
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(new GetAllRoomsCommand());
        invoker.execute();
        ArrayList<Room> rooms = invoker.getResponse();
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Add Room");
        System.out.println("2. \t Remove Room");
        System.out.println("3. \t Back");
        int option = Integer.parseInt(console.readLine());
        System.out.println("\n####################################################\n");
        switch (option) {
            case 1:
                Room selectedAddRoom = (Standard)addRoom(console);
                if(selectedAddRoom != null){
                    invoker.setCommand(new CreateRoomCommand((Standard)selectedAddRoom));
                    invoker.execute();
                }
                break;
            case 2:
                if(rooms.size() == 0){
                    break;
                }
                Room selectedRemoveRoom = (Standard)removeRoom(console, rooms);
                if(selectedRemoveRoom != null){
                    invoker.setCommand(new RemoveRoomCommand((Standard)selectedRemoveRoom));
                    invoker.execute();
                }
                break;
            case 3:
                return true;
            default:
                return false;        
        }
        return false;
    }

    public static Room addRoom(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following rooms to add:");
        System.out.println("0. \t Back\n");
        System.out.println("1. \t Standard Room");
        int option = -1;
        try {
            option = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return addRoom(console);
        }
        System.out.println("\n####################################################\n");
        System.out.println(option);
        switch (option) {
            case 0:
                return null;
            case 1:
                return new Standard("Standard", -1, 2);
            default:
                return addRoom(console);
        }
    }

    public static Room removeRoom(Console console, ArrayList<Room> rooms){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following rooms to remove:");
        System.out.println("\n0.\t Back\n");
        for (int i=0; i < rooms.size(); i++) 
        {   
            System.out.println((i+1) + ".\t" + rooms.get(i).toString());
        }
        int option = -2;
        try {
            option = Integer.parseInt(console.readLine())-1;
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return addRoom(console);
        }
        System.out.println("\n####################################################\n");
        if(option == -1){
            return null;
        }
        else if(option >= 0 && option < rooms.size()){
            return rooms.get(option);
        }
        return removeRoom(console, rooms);
    }
}
