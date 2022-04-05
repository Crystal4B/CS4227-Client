package hotelsystem.userinterface;

import java.util.Scanner;

import hotelsystem.billingsystem.BillingCard;
import hotelsystem.billingsystem.BillingCash;
import hotelsystem.billingsystem.BillingCashless;
import hotelsystem.billingsystem.CouponVisitor;
import hotelsystem.order.Order;

/**
 * @author Aleksandr Jakusevs
 * @author Eoin McDonough
 * @author Jordan Marshall
 * UI for billing and payments
 */
public class BillingUI {
    private static CouponVisitor a = new CouponVisitor();

    private static Order order;

    /**
     * This runs the current state specified in UI.java and can also change sub-states.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static int run(Scanner console) {
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Pay with Card");
        System.out.println("2. \t Use Voucher");
        System.out.println("3. \t Use Coupon Code");
        System.out.println("4. \t Pay on Arrival");
        System.out.println("5. \t Back");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return UI.BILLING_STATE;
        }
        switch (option) {
            case 1: 
                while(true){
                    if(payByCard(console)){
                        break;
                    }
                }
                return returnToMenu();
            case 2:
                while(true){
                    if(voucherCode(console)){
                        break;
                    }
                }
                return returnToMenu();
            case 3: 
                while(true){
                    if(couponCode(console)){
                        break;
                    }
                }
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

    /**
     * Method to allow an input of a coupon code as well as checking of the coupon code
     * @param console Used to read user input.
     * @return boolean depending on the validity of the coupon.
     */
    public static Boolean couponCode(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println(" Please enter coupon code :");
        String codeNum = console.nextLine();
        a.CodeSet(codeNum);
        if (a.CouponInput()!=0.0) {
            System.out.println(" Applying discount of :");
            System.out.println(a.PercentConverter(a.CouponInput()) );
            return true;
        }
        else {
            System.out.println(" Invalid Coupon Code, Please Try Again");
            return false;
        }
    }

    /**
     * Method to allow an input of a voucher code as well as checking of the voucher code
     * @param console Used to read user input.
     * @return boolean depending on the validity of the voucher.
     */
    public static Boolean voucherCode(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println(" Please enter voucher code :");
        String codeNum = console.nextLine();
        BillingCashless bill = new BillingCashless();
        a.CodeSet(codeNum);
        if (bill.AcceptCouponVisitorCode(a)!=0.0) {
            bill.SendEmail(order);
            return true;
        }
        else {
            System.out.println(" Invalid Voucher Code, Please Try Again");
            return false;
        }
    }

    /**
     * Checks for which menu to send the user back to
     * @return int to signify which menu to return to
     */

    public static int returnToMenu(){ 
        if(LoginUI.getUser().getUserType().equals("Customer")){
            return UI.MENU_STATE;
        }
        else if(LoginUI.getUser().getUserType().equals("Staff")){
            return UI.STAFF_MENU;
        }
		return UI.MENU_STATE;
    }

    public static Boolean payOnArrival(Scanner console){
        System.out.println("\n Reservation will be paid for at Reception");
        BillingCash bill = new BillingCash();
        bill.PaymentSend(order); 
        bill.SendEmail(order);
        return true;
        
    }

    public static Boolean payByCard(Scanner console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println(" Name on Card :");
        String cardName = console.nextLine();
        System.out.println(" Card Number :");
        String cardNum = console.nextLine();
        System.out.println(" Expiry Date   ( MM/YY ) :");
        String cardDate = String.valueOf(console.nextLine());
        cardDate = cardDate.replace("/", ""); 
        System.out.println(" CVC :");
        int date = Integer.parseInt(cardDate);
        int cvc = Integer.parseInt(console.nextLine());
        BillingCard bill = new BillingCard();
        if (bill.PaymentSend(cardNum, cardName, date, cvc, order)) {
            bill.SendEmail(order);
            return true;
        }
        else {
            System.out.println(" Invalid Card, Please Try Again");
            return false;
        }
    }

    /**
     * Method used to set final order 
     * @param console Used to read user input.
     */


    public static void setFinalOrder(Order finalOrder){
        order = finalOrder;
    }

    /**
     * Method used to get final order 
     * @return Returns finalOrder item
     */

    public static Order getFinalOrder(){
        return order;
    }

}
