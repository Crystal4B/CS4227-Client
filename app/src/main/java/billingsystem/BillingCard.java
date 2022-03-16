package billingsystem;


import java.math.BigDecimal;
import order.*;


public class BillingCard extends BillingTemplate {
    @Override
    public double BillCalc(Order order) {
        return order.getFinalCost();
    }
    @Override 
    public String Bill(Order order) {
        TempOrder = order;
        String bill = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + order.getFinalCost() + 
        "\n" + "Voucher:\t\t" + 
        "\n" + "Total:\t\t" + BillCalc(order) +
        "\n" + "Paid By:\t\tCard";
        return bill;
    }

    @Override
    public BigDecimal RoundToTwoDec(double num) {
        BigDecimal temp = new BigDecimal(Double.toString(num));
        temp = temp.setScale(2);
        return temp;
    }

    @Override
    public String DoubleToString(double num) {
        String str = num + "";
        return str;
    }

    
    @Override
    public String IntToString(int num) {
        String str = num + "";
        return str;
    }
}
