package billingsystem;

import order.*;

public class BillingCard extends BillingTemplate {
    @Override 
    public String Bill(Order order, double num) {
        temp = num;
        TempOrder = order;
        String bill = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + CouponPaid() +
        "\n" + "Total:\t\t" + BillCalc(order) +
        "\n" + "Paid By:\t\tCard";
        return bill;
    }
}
