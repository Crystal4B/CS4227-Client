package userinterface;

import java.io.Console;
import java.util.ArrayList;

import hotelsystem.roomFactory.RoomInterface;
import login.LoginAdapter;
import login.Signup;
import hotelsystem.roomFactory.Room;
import hotelsystem.roomFactory.RoomFactory;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.rooms.CreateRoomCommand;
import requestsystem.commands.rooms.GetAllRoomsCommand;
import requestsystem.commands.rooms.RemoveRoomCommand;

public class StaffUI {
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Create Reservation");
        System.out.println("2. \t View Reservations");
        System.out.println("3. \t Manage Rooms");
        System.out.println("4. \t Add Staff Account");
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
                while(true){
                    if(signupStaffAccount(console)) {
                        break;
                    }
                }
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
        ArrayList<RoomInterface> roomFactories = invoker.getResponse();
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
                RoomInterface selectedAddRoomInterface = (Room)addRoom(console);
                if(selectedAddRoomInterface != null){
                    invoker.setCommand(new CreateRoomCommand((Room) selectedAddRoomInterface));
                    invoker.execute();
                }
                break;
            case 2:
                if(roomFactories.size() == 0){
                    break;
                }
                RoomInterface selectedRemoveRoomInterface = (Room)removeRoom(console, roomFactories);
                if(selectedRemoveRoomInterface != null){
                    invoker.setCommand(new RemoveRoomCommand((Room) selectedRemoveRoomInterface));
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

    public static RoomInterface addRoom(Console console){
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
                return RoomFactory.createStandard(-1, 2);
            default:
                return addRoom(console);
        }
    }

    public static RoomInterface removeRoom(Console console, ArrayList<RoomInterface> roomFactories){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following rooms to remove:");
        System.out.println("\n0.\t Back\n");
        for (int i = 0; i < roomFactories.size(); i++)
        {   
            System.out.println((i+1) + ".\t" + roomFactories.get(i).toString());
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
        else if(option >= 0 && option < roomFactories.size()){
            return roomFactories.get(option);
        }
        return removeRoom(console, roomFactories);
    }

    public static boolean signupStaffAccount(Console console) {
        System.out.println("Please enter email for new User");
        String email = console.readLine();
        System.out.println("Please enter username for new User");
        String username = console.readLine();
        System.out.println("Please enter temporary password");
        String password = String.valueOf(console.readPassword());
        LoginAdapter signup = new LoginAdapter(new Signup());
        signup.setName(username);
        signup.setType("Staff");
        signup.login(email, password);
        return true;
    }
}
