package hotelsystem.billingsystem;

import hotelsystem.order.Order;
import hotelsystem.payment.Payment;

/**
 * @author Aleksandr Jakusevs
 * Billing for cash payments
 */
public class BillingCash extends BillingTemplate {

    /**
	 * Method to that returns that a user is paying by cash
     * @param order the order the payment is attached to
     * @return Validation of payment
	 */
    public boolean PaymentSend(Order order){
        Payment pay = new Payment();
        pay.setCost(BillCalc(order));
        int orderId = Integer.parseInt(order.getOrderID());
        return pay.payByCash(orderId);
    }

    /**
	 * Method to accept a CouponVisitor object and use one of its functions
	 * @param a the CouponVisitor object that was accepted
	 */
    @Override
    public double AcceptCouponVisitorCode(CouponVisitor a){
        discount = a.CouponInput();
        return discount;
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
                "\n" + "Paid By:\t\tCash";
    }
}
