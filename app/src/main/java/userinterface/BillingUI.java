package userinterface;

import java.io.Console;

import billingsystem.BillingCard;
import billingsystem.BillingCash;
import order.Order;

public class BillingUI {

    private static Order order;

    public static int run(Console console) {
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Pay with Card");
        System.out.println("2. Use Voucher");
        System.out.println("3. Use Coupon Code");
        System.out.println("4. Pay on Arrival");
        System.out.println("5. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1: 
                while(true){
                    if(payByCard(console)){
                        break;
                    }
                }
                return returnToMenu();
            case 2:
                return UI.BILLING_STATE;
            case 3: 
                return UI.BILLING_STATE;
            case 4: 
            while(true){
                if(payOnArrival(console)){
                    break;
                }
            }
                return returnToMenu();
            case 5: 
                return returnToMenu();
            default:
                return UI.BILLING_STATE;
        }
    }

    public static int returnToMenu(){ 
        if(LoginUI.userType.equals("Customer")){
            return UI.MENU_STATE;
        }
        else if(LoginUI.userType.equals("Staff")){
            return UI.STAFF_MENU;
        }
		return UI.MENU_STATE;
    }

    public static Boolean payOnArrival(Console console){
        System.out.println("\n Reservation will be paid for at Reception");
        BillingCash bill = new BillingCash();
        bill.PaymentSend(order); 
        bill.SendEmail(order, 0);
        return true;
        
    }

    public static Boolean payByCard(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println(" Name on Card :");
        String cardName = console.readLine();
        System.out.println(" Card Number :");
        String cardNum = console.readLine();
        System.out.println(" Expiry Date   ( MM/YY ) :");
        int cardDate = Integer.parseInt(console.readLine());
        System.out.println(" CSV :");
        int csv = Integer.parseInt(console.readLine());
        BillingCard bill = new BillingCard();
        if (bill.PaymentSend(cardNum, cardName, cardDate, csv, order)) {
            bill.SendEmail(order, 0);
            return true;
        }
        else {
            System.out.println(" Invalid Card, Please Try Again");
            return false;
        }
    }

    public static void setFinalOrder(Order finalOrder){
        order = finalOrder;
    }

    public static Order getFinalOrder(){
        return order;
    }

}
