package hotelsystem.billing;

import java.math.BigDecimal;

public class Billing extends BillingTemplate {
    @Override
    double BillCalc() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override 
    String Bill() {
        return "";
    }

    @Override
    int Round(double num) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    BigDecimal RoundToTwoDec(double num) {
        BigDecimal temp = new BigDecimal(Double.toString(num));
        temp = temp.setScale(2);
        // TODO Auto-generated method stub
        return temp;
    }

    @Override
    String DoubleToString(double num) {
        String str = num + "";
        return str;
    }

    
    @Override
    String IntToString(int num) {
        String str = num + "";
        return str;
    }
}
