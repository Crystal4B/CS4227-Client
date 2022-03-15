package hotelsystem.billing;
import java.math.BigDecimal;
import order.*;

public class BillingVIP extends BillingTemplate {
    @Override
    public double BillCalc(Order order) {
        return order.getFinalCost() + 1000;
    }
    @Override 
    public String Bill(Order order) {
        String bill = "";
        bill = "Date:              Description:                Voucher:               Charges:";
        bill = bill + "\n" + "SD: " + order.getStartDate() + " ED: "+ order.getEndDate() + "\t\t\t\t" +  "Room Charge\t\t\t\t" + "\t\t\t\t" + order.getFinalCost() + "\t\t\t\t" ;
        bill = bill + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t Total: " + BillCalc(order);
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
