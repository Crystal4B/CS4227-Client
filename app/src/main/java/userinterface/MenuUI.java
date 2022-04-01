package userinterface;

import java.io.Console;
import java.util.List;

import order.Order;
import requestsystem.commands.*;
import requestsystem.commands.reservations.CancelReservationCommand;
import requestsystem.commands.reservations.GetReservationsByUserCommand;

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
        int option = -1;
        try {
            option = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return UI.MENU_STATE;
        }
        switch (option) {
            case 1: 
                return UI.RESERVATION_STATE;
            case 2: 
            while(true){
                if(cancelUserReservation(console)){
                    break;
                }
            }
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
        System.out.println("0. \t Back");
        getUserReservations();      
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = -1;
        try {
            option = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        switch (option) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    public static List<Order> getUserReservations(){
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(new GetReservationsByUserCommand(LoginUI.getUser()));
        invoker.execute();
        List<Order> reservations = invoker.getResponse();
        for (int i=0; i < reservations.size(); i++) 
        { 
            System.out.println((i+1) + ". \t" + reservations.get(i).toString() + "\n");
        }
        return reservations;
        
    }

    public static boolean cancelUserReservation(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Reservation Information:\n");
        System.out.println("0. \t Back");
        List<Order> reservations = getUserReservations();
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = -1;
        try {
            option = Integer.parseInt(console.readLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        if(option == 0){
            return true;
        }
        else if(option > 0 && option < reservations.size()+1){
            CommandInvoker invoker = new CommandInvoker();
            invoker.setCommand(new CancelReservationCommand(reservations.get(option-1)));
            invoker.execute();
            return true;
        }
        return false;
    }
}
