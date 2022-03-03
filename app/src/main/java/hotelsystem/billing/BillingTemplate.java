package hotelsystem.billing;

import java.math.BigDecimal;

abstract class BillingTemplate {
   abstract double BillCalc();

   abstract int Round(double num);

   abstract BigDecimal RoundToTwoDec(double num);

   abstract String Bill();

   abstract String DoubleToString(double num);

   abstract String IntToString(int num);

   String GetBill(){
      String temp = "";
      temp = Bill();
      return temp;
   }

}
