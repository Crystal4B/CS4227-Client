package billingsystem;

import order.*;

import payment.*;

public class BillingCash extends BillingTemplate {

    public boolean PaymentSend(Order order){
        Payment pay = new Payment();
        pay.setCost(BillCalc(order));
        int orderId = Integer.parseInt(order.getOrderID());
        return pay.payByCash(orderId);
    }

    @Override
    public double VouchInput() {
        if(code == "123"){
            return 0.1;
        } else if(code == "12345") {
            return 0.2;
        } else if(code == "Amogus"){
            return 0.5;
        } else {
            return 0;
        }
    }

    @Override
    public String Bill(Order order, double num) {
        discount = num;
        TempOrder = order;
        String bill = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + AcceptCouponVisitor(visitor) +
        "\n" + "Total:\t\t" + BillCalc(order) +
        "\n" + "Paid By:\t\tCash";
        return bill;
    }
}
