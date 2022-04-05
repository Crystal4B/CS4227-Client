package hotelsystem.billingsystem;

import hotelsystem.order.Order;
import hotelsystem.payment.Payment;

/**
 * @author Aleksandr Jakusevs
 * Billing for card payments
 */
public class BillingCard extends BillingTemplate {

    /**
	 * Method to check if card details have been inputted correctly
	 * @param num the number of the card
     * @param name the name of the card holder
     * @param date the expiry date of the card mm/yy
     * @param csv the security code on the back of the card
     * @param order the order the payment is attached to
	 */
    public boolean PaymentSend(String num, String name, int date, int csv, Order order){
        Payment pay = new Payment();
        pay.setCost(BillCalc(order));
        int orderId = Integer.parseInt(order.getOrderID());
        return pay.payCreditCard(orderId,num,name,date,csv);
    }

    
    /**
	 * Method to accept a CouponVisitor object and use one of its functions
	 * @param a the CouponVisitor object that was accepted
	 */
    @Override
    public double AcceptCouponVisitorCode(CouponVisitor a){
        discount = a.CouponInput();
        return discount ;
    }

    /**
	 * Method to return the bill as a string
     * @param order the order the bill is attached to
	 */
    @Override
    public String Bill(Order order) {
        return "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() +
                "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + AcceptCouponVisitor(visitor) +
                "\n" + "Total:\t\t" + BillCalc(order) +
                "\n" + "Paid By:\t\tCard";
    }
}