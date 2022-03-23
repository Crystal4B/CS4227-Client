package userinterface;

import java.io.Console;
import java.util.List;

import order.Order;
import requestsystem.commands.*;

public class MenuUI {
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Reserve Room(s)");
        System.out.println("2. \t Cancel Reservation");
        System.out.println("3. \t View Reservation Information");
        System.out.println("4. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1: 
                return UI.RESERVATION_STATE;
            case 2: 
                System.out.println("Cancel");
                return UI.MENU_STATE;
            case 3: 
            while(true){
                if(viewReservations(console)){
                    break;
                }
            }
            return UI.MENU_STATE;
            case 4: 
                return UI.EXIT;
            default:
                return UI.MENU_STATE;
        }
    }

    public static Boolean viewReservations(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Reservation Information:\n");
        System.out.println(getUserReservations());
        System.out.println("1. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1:
                return true;
            default:
                return false;
        }
    }

    public static String getUserReservations(){
        CommandInvoker invoker = new CommandInvoker();
        System.out.print(LoginUI.getUser().getId());
        System.out.print(LoginUI.getUser().getEmail());
        System.out.print(LoginUI.getUser().getUserType());
        invoker.setCommand(new GetReservationsByUserCommand(LoginUI.getUser()));
        invoker.execute();
        List<Order> reservations = invoker.getResponse();
        String reservationList = "";
        for (int i=0; i < reservations.size(); i++) 
        { 
            reservationList += reservations.get(i).toString() + "\n";
        }
        return reservationList;
        
    }

    public void cancelUserReservation(){
        CommandInvoker invoker = new CommandInvoker();
        // invoker.setCommand(new CancelReservationCommand());
        // invoker.execute();
        // invoker.getResponse();
    }
}
