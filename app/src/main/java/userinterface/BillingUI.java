package userinterface;

import java.io.Console;

import billingsystem.BillingCard;
import billingsystem.BillingCash;
import billingsystem.BillingCashless;
import billingsystem.CouponVisitor;
import order.Order;

public class BillingUI {
    private static CouponVisitor a = new CouponVisitor();

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
        int option = -1;
        try {
            option = Integer.parseInt(console.readLine());
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
                    if(VoucherCode(console)){
                        break;
                    }
                }
                return returnToMenu();
            case 3: 
                while(true){
                    if(CouponCode(console)){
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

    public static Boolean CouponCode(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println(" Voucher Code :");
        String codeNum = console.readLine();
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

    public static Boolean VoucherCode(Console console){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println(" Voucher Code :");
        String codeNum = console.readLine();
        BillingCashless bill = new BillingCashless();
        a.CodeSet(codeNum);
        if (bill.AcceptCouponVisitorCode(a)!=0.0) {
            bill.SendEmail(order, bill.AcceptCouponVisitorCode(a));
            return true;
        }
        else {
            System.out.println(" Invalid Voucher Code, Please Try Again");
            return false;
        }
    }

    public static int returnToMenu(){ 
        if(LoginUI.getUser().getUserType().equals("Customer")){
            return UI.MENU_STATE;
        }
        else if(LoginUI.getUser().getUserType().equals("Staff")){
            return UI.STAFF_MENU;
        }
		return UI.MENU_STATE;
    }

    public static Boolean payOnArrival(Console console){
        System.out.println("\n Reservation will be paid for at Reception");
        BillingCash bill = new BillingCash();
        bill.PaymentSend(order); 
        bill.SendEmail(order, bill.AcceptCouponVisitorCode(a));
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
            bill.SendEmail(order, bill.AcceptCouponVisitorCode(a));
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
