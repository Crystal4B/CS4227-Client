package userinterface;

import java.io.Console;

public class MenuUI {
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Reserve Room(s)");
        System.out.println("2. \t Cancel Booking");
        System.out.println("3. \t View Booking Information");
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
                System.out.println("View");
                return UI.MENU_STATE;
            case 4: 
                return UI.EXIT;
            default:
                return UI.MENU_STATE;
        }
    }
}
