package hotelsystem.billing;

import java.math.BigDecimal;
import order.*;
import hotelsystem.room.Standard;

abstract class BillingTemplate {
   abstract public double BillCalc(Order order);

   abstract public int Round(double num);

   abstract public BigDecimal RoundToTwoDec(double num);

   abstract public String Bill(Order order);

   abstract public String DoubleToString(double num);

   abstract public String IntToString(int num);

   OrderBuilder builder = new OrderBuilder();

   public void BuildBasicOrder(){
      builder.addRoom(new Standard("Test Name", 123, 2));
   }

   Order order = builder.getOrder();

   public String GetBill(){
      String temp = "";
      temp = Bill(order);
      return temp;
   }

}
