package hotelsystem.billing;

public class BillingVIP extends BillingTemplate {
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
