package billingsystem;

import order.*;

import payment.*;

public class BillingCard extends BillingTemplate {

    public boolean PaymentSend(String num, String name, int date, int csv,Order order){
        Payment pay = new Payment();
        pay.setCost(BillCalc(order));
        int orderId = Integer.parseInt(order.getOrderID());
        return pay.payCreditCard(orderId,num,name,date,csv);
    }

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
