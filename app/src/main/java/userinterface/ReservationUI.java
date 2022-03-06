package userinterface;

import java.io.Console;
import order.*;

public class ReservationUI {

    private static OrderBuilder builder = new OrderBuilder();
    private static Director director = new Director();
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Add Room");
        System.out.println("2. \t Set Check-In and Check-Out dates");
        System.out.println("3. \t Remove Room");
        System.out.println("4. \t View Order Cart");
        System.out.println("5. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1: 
                while(!addRoomToCart(console)){}
                return UI.RESERVATION_STATE;
            case 2:
                while(!setDetails(console)){}
                return UI.RESERVATION_STATE;
            case 3: 
                while(!removeRoomFromCart(console)){}
                return UI.RESERVATION_STATE;
            case 4: 
                while(!viewOrder(console)){}
                return UI.RESERVATION_STATE;
            case 5: 
                return UI.MENU_STATE;
            default:
                return UI.RESERVATION_STATE;
        }
    }

    public static Boolean addRoomToCart(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Standard Room");
        System.out.println("2. \t Deluxe Room");
        System.out.println("3. \t VIP Room");
        System.out.println("4. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        if(director.addRoomUsingUI(builder, option)){
            return true;
        }
        return false;
    }

    public static Boolean setDetails(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please enter check-in date (YYYY-MM-DD) @ 12:00:");
        String checkInDate = console.readLine();
        System.out.println("Please enter check-out date (YYYY-MM-DD) @ 12:00:");
        String checkOutDate = console.readLine();
        System.out.println("\n####################################################\n");
        if(director.setDatesUsingUI(builder, checkInDate, checkOutDate)){
            return true;
        }
        return false;
    }

    public static Boolean removeRoomFromCart(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select a room to remove:\n");
        System.out.println(director.viewRoomsInCart(builder));
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        if(director.removeRoomUsingUI(builder, option)){
            return true;
        }
        return false;
    }

    public static Boolean viewOrder(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Order Cart Information:\n");
        System.out.println(director.viewCart(builder));
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
}
