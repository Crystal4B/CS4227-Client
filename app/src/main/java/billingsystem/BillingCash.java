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
    public double AcceptCouponVisitorCode(CouponVisitor a){
        discount = a.CouponInput();
        return discount;
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
