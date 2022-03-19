package billingsystem;

import order.*;

public class BillingCash extends BillingTemplate {
    @Override
    public String Bill(Order order) {
        TempOrder = order;
        String bill = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + 
        "\n" + "Voucher:\t\t" + 
        "\n" + "Total:\t\t" + BillCalc(order) +
        "\n" + "Paid By:\t\tCash";
        return bill;
    }
}
