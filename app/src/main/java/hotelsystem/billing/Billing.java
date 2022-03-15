package hotelsystem.billing;

import java.math.BigDecimal;
import order.*;


public class Billing extends BillingTemplate {
    @Override
    public double BillCalc(Order order) {
        return order.getFinalCost() + 1000;
    }
    @Override 
    public String Bill(Order order) {
        String bill = "Date:              Description:                Voucher:               Charges:"
         + "\n" + "SD: " + order.getStartDate() + " ED: "+ order.getEndDate() + "\t\t\t" +  "Room Charge\t\t\t" + "\t\t\t" + order.getFinalCost() + "\t\t\t" 
         + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\t Total: " + BillCalc(order);
        return bill;
    }

    @Override
    public int Round(double num) {
        // TODO Auto-generated method stub
        return 0;
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
