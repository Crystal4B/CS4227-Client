package hotelsystem.billingsystem;

import hotelsystem.order.Order;

/**
 * @author Aleksandr Jakusevs
 * Billing for voucher payments
 */
public class BillingCashless extends BillingTemplate {

    /**
	 * Method to accept a CouponVisitor object and use one of its functions
	 * @param a the CouponVisitor object that was accepted
	 */
    @Override
    public double AcceptCouponVisitorCode(CouponVisitor a){
        discount = a.VoucherInput();
        return discount;
    }

    /**
	 * Method to return the bill as a string
     * @param order the order the bill is attached to
	 */
    @Override
    public String Bill(Order order) {
        return "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() +
                "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() +
                "\n" + "Total:\t\t" + BillCalc(order) +
                "\n" + "Paid By:\t\tVoucher";
    }
}