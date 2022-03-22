package userinterface;

import java.io.Console;
import java.util.ArrayList;

import hotelsystem.user.Guest;
import hotelsystem.user.User;
import order.*;

public class ReservationUI {

    private static OrderBuilder builder = new OrderBuilder();
    private static Director director = new Director();
    private static Order finalOrder;
    
    public static int run(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Add Room");
        System.out.println("2. \t Remove Room");
        System.out.println("3. \t View Order Cart");
        System.out.println("4. \t Finalise Order");
        System.out.println("5. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1: 
                while(true){
                    if(addRoomToCart(console)){
                        break;
                    }
                }
                return UI.RESERVATION_STATE;
            case 2: 
                while(true){
                    if(removeRoomFromCart(console)){
                        break;
                    }
                }
                return UI.RESERVATION_STATE;
            case 3: 
                while(true){
                    if(viewOrder(console)){
                        break;
                    }
                }
                return UI.RESERVATION_STATE;
            case 4: 
                Order order = builder.getOrder();
                finalOrder = director.addReservation(order);
                BillingUI.setFinalOrder(finalOrder);
                return UI.BILLING_STATE;
            case 5: 
                if(LoginUI.userType.equals("Customer")){
                    return UI.MENU_STATE;
                }
                else if(LoginUI.userType.equals("Staff")){
                    return UI.STAFF_MENU;
            }
            default:
                return UI.RESERVATION_STATE;
        }
    }

    public static Boolean addRoomToCart(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please enter check-in date (YYYY-MM-DD) @ 12:00:");
        String checkInDate = console.readLine();
        System.out.println("Please enter check-out date (YYYY-MM-DD) @ 12:00:");
        String checkOutDate = console.readLine();
        director.setDates(builder, checkInDate, checkOutDate);
        System.out.println("Please select one of the following options:");
        System.out.println(director.getAvailableRooms(builder));
        int roomTypeOption = Integer.parseInt(console.readLine());
        director.addRoom(console, builder, roomTypeOption);
        System.out.println("\n####################################################\n");
        return true;
    }

    public static Boolean removeRoomFromCart(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select a room to remove:\n");
        System.out.println(director.viewRoomsInCart(builder));
        int roomOption = Integer.parseInt(console.readLine());
        director.removeRoom(builder, roomOption);
        System.out.println("\n####################################################\n");
        return true;

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

    public static ArrayList<User> addGuests(Console console, int roomSize){
        ArrayList<User> guests = new ArrayList<>();
        for(int i=0; i < roomSize; i++){
            System.out.println("Please the first name of guest " + (i+1));
            String firstName = console.readLine();
            System.out.println("Please the last name of guest " + (i+1));
            String lastName = console.readLine();
            Guest guest = new Guest(firstName, lastName, -1);
            guests.add(guest);
        }
        return guests;
    }
}
