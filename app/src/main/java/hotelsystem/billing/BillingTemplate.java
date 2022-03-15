package hotelsystem.billing;

import java.math.BigDecimal;
import order.*;
import hotelsystem.Standard;

abstract class BillingTemplate {
   abstract public double BillCalc(Order order);

   abstract public BigDecimal RoundToTwoDec(double num);

   abstract public String Bill(Order order);

   abstract public String DoubleToString(double num);

   abstract public String IntToString(int num);

   OrderBuilder builder = new OrderBuilder();

   Order TempOrder;

   public Order GetOrder(){
      return TempOrder;
   }

   public void BuildBasicOrder(){
      builder.addRoom(new Standard("Test Name", 123, 2));
   }

   public String GetBill(Order order){
      String temp = "";
      temp = Bill(order);
      return temp;
   }

}
