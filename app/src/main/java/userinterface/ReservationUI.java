package userinterface;

import java.io.Console;
import java.sql.Timestamp;

import hotelsystem.room.*;
import order.*;

public class ReservationUI {

    private static OrderBuilder builder = new OrderBuilder();
    
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
                return 1;
        }
        return 2;
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
        try {
            if(Timestamp.valueOf(checkInDate + " 12:00:00").before(Timestamp.valueOf(checkOutDate + " 12:00:00"))){
                builder.setStartDate(Timestamp.valueOf(checkInDate + " 12:00:00"));
                builder.setEndDate(Timestamp.valueOf(checkOutDate + " 12:00:00"));
            }
            else{
                setDetails(console);
                return ;  
            }
        } catch (Exception e) {
            setDetails(console);
            return ;
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
            switch (option) {
                case 1: 
                    builder.addRoom(new Standard("Test Name", 123, 2));
                    break;
                case 2: 
                    builder.addRoom(new Deluxe("Test Name", 123, 2));
                    break;
                case 3: 
                    builder.addRoom(new VIP("Test Name", 123, 2));
                    break;
                case 4: 
                    run(console);
            }
            
        }else{
            setDetails(console);
            addRoomToCart(console);
        }
    }

    public static void viewOrder(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Order Cart Information:\n");
		Order order = builder.getOrder();
        System.out.println(order.toString());
        System.out.println("1. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1: 
                run(console);
            default:
                run(console);
        }
    }
}
