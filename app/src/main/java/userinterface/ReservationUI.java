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
                addRoomToCart(console);
                break;
            case 2:
                setDetails(console);
                break;
            case 3: 
                System.out.println("Remove Room");
                break;
            case 4: 
                viewOrder(console);
                break;
            case 5: 
                return UI.MENU_STATE;
            default:
                return UI.RESERVATION_STATE;
        }
    }

    public static void setDetails(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please enter check-in date (YYYY-MM-DD) @ 12:00:");
        String checkInDate = console.readLine();
        System.out.println("Please enter check-out date (YYYY-MM-DD) @ 12:00:");
        String checkOutDate = console.readLine();
        System.out.println("\n####################################################\n");
        if(!director.setDatesUsingUI(builder, checkInDate, checkOutDate)){
            setDetails(console);
        }
    }

    public static void addRoomToCart(Console console){
        Order order = builder.getOrder();
        if(order.getStartDate() != null && order.getEndDate() != null){
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
                run(console);
            }
            else{
                addRoomToCart(console);
            }
        }
        else{
            setDetails(console);
            addRoomToCart(console);
        }
    }

    public static void viewOrder(Console console){
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
                run(console);
            default:
                viewOrder(console);
        }
    }
}
