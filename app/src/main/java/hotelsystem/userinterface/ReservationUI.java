package hotelsystem.userinterface;

import java.util.ArrayList;
import java.util.Scanner;

import hotelsystem.order.Director;
import hotelsystem.order.Order;
import hotelsystem.order.OrderBuilder;
import hotelsystem.userfactory.Guest;
import hotelsystem.userfactory.UserInterface;

/**
 * ReservationUI handles the reservation state of the user interface
 * @author Jordan Marshall
 */
public class ReservationUI {

    private static OrderBuilder builder = new OrderBuilder();
    private static Director director = new Director();
    private static Order finalOrder;
    
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
        System.out.println("1. \t Add Room");
        System.out.println("2. \t Remove Room");
        System.out.println("3. \t View Order Cart");
        System.out.println("4. \t Finalise Order");
        System.out.println("5. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return UI.RESERVATION_STATE;
        }
        builder.setUser(LoginUI.getUser());
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
                if(finalOrder != null){
                    BillingUI.setFinalOrder(finalOrder);
                    return UI.BILLING_STATE;
                }
                return UI.RESERVATION_STATE;
            case 5: 
                if(LoginUI.getUser().getUserType().equals("Customer")){
                    return UI.MENU_STATE;
                }
                else if(LoginUI.getUser().getUserType().equals("Staff")){
                    return UI.STAFF_MENU;
            }
            default:
                return UI.RESERVATION_STATE;
        }
    }

    /**
     * This function allows users to add a room with details provided to this order/reservation.
     * @param console Used to read user input.
     * @return If operation is sucessful. Changes current state in run function.
     */
    public static Boolean addRoomToCart(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please enter check-in date (YYYY-MM-DD) @ 12:00:");
        String checkInDate = console.nextLine();
        System.out.println("Please enter check-out date (YYYY-MM-DD) @ 12:00:");
        String checkOutDate = console.nextLine();
        if(!director.setDates(builder, checkInDate, checkOutDate)){
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        System.out.println("Please select one of the following options:");
        if(!director.getAvailableRooms(builder)){
            System.out.println("No Rooms Available, Please try another date");
            return true;
        }
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        director.addRoom(console, builder, option);
        System.out.println("\n####################################################\n");
        return true;
    }

    /**
     * This function allows users to remove a chosen room from this order/reservation.
     * @param console Used to read user input.
     * @return If operation is sucessful. Changes current state in run function.
     */
    public static Boolean removeRoomFromCart(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select a room to remove:\n");
        int numberOfRoomsInCart = director.viewRoomsInCart(builder);
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        if(option >= 0 || option < numberOfRoomsInCart ){
            director.removeRoom(builder, option);
            System.out.println("\n####################################################\n");
            return true;
        }
        return false;

    }

    /**
     * This is a function to view a the current state of the order/reservation. This lists out all rooms added along with additional information on the order.
     * @param console Used to read user input.
     * @return If operation is sucessful. Changes current state in run function.
     */
    public static Boolean viewOrder(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Order Cart Information:\n");
        System.out.println(director.viewCart(builder));
        System.out.println("1. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
        return option == 1;
    }

    /**
     * This is a function called when creating a room to add guest names to room object.
     * @param console Used to read user input.
     * @param roomSize Number of occupants in room.
     * @return A list of users staying in room.
     */
    public static ArrayList<UserInterface> addGuests(Scanner console, int roomSize){
        ArrayList<UserInterface> guests = new ArrayList<>();
        for(int i=0; i < roomSize; i++){
            System.out.println("Please the first name of guest " + (i+1));
            String firstName = console.nextLine();
            System.out.println("Please the last name of guest " + (i+1));
            String lastName = console.nextLine();
            Guest guest = new Guest(firstName, lastName, -1);
            guests.add(guest);
        }
        return guests;
    }
}
