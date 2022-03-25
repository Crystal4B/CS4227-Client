package billingsystem;

import order.*;

public class BillingCashless extends BillingTemplate {
    
    @Override
    public double VouchInput() {
        if(code == "123"){
            return 1;
        } else {
            return 0;
        }
    }

    @Override 
    public String Bill(Order order, double num) {
        TempOrder = order;
        String bill = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + 
        "\n" + "Total:\t\t" + BillCalc(order) +
        "\n" + "Paid By:\t\tVoucher";
        return bill;
    }
}
