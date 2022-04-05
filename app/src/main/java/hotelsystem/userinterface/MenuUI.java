package hotelsystem.userinterface;

import java.util.List;
import java.util.Scanner;

import hotelsystem.order.Order;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.reservations.CancelReservationCommand;
import hotelsystem.requestsystem.commands.reservations.GetReservationsByUserCommand;

public class MenuUI {

    /**
     * This runs the current state specified in UI.java and can also change sub-states.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static int run(Scanner console){
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
        int option;
        try {
            option = console.nextInt();
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

    /**
     * This is a function to view all reservations created by the user.
     * @param console Used to read user input.
     */
    public static Boolean viewReservations(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Reservation Information:\n");
        System.out.println("0. \t Back");
        getUserReservations();      
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = console.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        return option == 0;
    }

    /**
     * This is a function that gets all user reservations.
     * @return A list of orders.
     */
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

    /**
     * This function allows a user to cancel a reservation from list.
     * @param console Used to read user input.
     * @return If operation is sucessful. Changes current state in run function.
     */
    public static boolean cancelUserReservation(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Reservation Information:\n");
        System.out.println("0. \t Back");
        List<Order> reservations = getUserReservations();
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = console.nextInt();
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
