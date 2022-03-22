package userinterface;

import java.io.Console;

import order.Order;

public class BillingUI {

    private static Order order;

    public static int run(Console console) {
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t ...");
        System.out.println("2. \t ...");
        System.out.println("3. \t ...");
        System.out.println("4. \t ...");
        System.out.println("5. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1: 
                return UI.BILLING_STATE;
            case 2:
                return UI.BILLING_STATE;
            case 3: 
                return UI.BILLING_STATE;
            case 4: 
                return UI.BILLING_STATE;
            case 5: 
                return UI.RESERVATION_STATE;
            default:
                return UI.BILLING_STATE;
        }
    }

    public static void setFinalOrder(Order finalOrder){
        order = finalOrder;
    }

    public static Order getFinalOrder(){
        return order;
    }

}
