package billingsystem;


import java.math.BigDecimal;
import order.*;


public class BillingCashless extends BillingTemplate {
    @Override 
    public String Bill(Order order) {
        TempOrder = order;
        String bill = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + 
        "\n" + "Total:\t\t" + BillCalc(order) +
        "\n" + "Paid By:\t\tVoucher";
        return bill;
    }
}