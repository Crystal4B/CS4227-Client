package userinterface;

import java.io.Console;

public class StaffUI {
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Create Reservation");
        System.out.println("2. \t View Reservations");
        System.out.println("3. \t Add Rooms");
        System.out.println("4. \t Remove Rooms");
        System.out.println("5. \t Add Staff");
        System.out.println("6. \t Remove Staff");
        System.out.println("7. \t View Staff");
        System.out.println("8. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1:
                return UI.RESERVATION_STATE;
            case 2: 
                return UI.STAFF_MENU;
            case 3:  
                return UI.STAFF_MENU;
            case 4:  
                return UI.STAFF_MENU;
            case 5:  
                return UI.STAFF_MENU;
            case 6:  
                return UI.STAFF_MENU;
            case 7:  
                return UI.STAFF_MENU;
            case 8:  
                return UI.EXIT;
            default:
                return UI.STAFF_MENU;
        }
    }
}
