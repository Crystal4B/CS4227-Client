package billingsystem;

import java.math.BigDecimal;
import order.*;

abstract class BillingTemplate {
   public double BillCalc(Order order) {
      return order.getFinalCost();
   }

   public BigDecimal RoundToTwoDec(double num) {
      BigDecimal temp = new BigDecimal(Double.toString(num));
      temp = temp.setScale(2);
      return temp;
   }

   abstract public String Bill(Order order);

   public String DoubleToString(double num) {
      String str = num + "";
      return str;
   }

   public String IntToString(int num) {
      String str = num + "";
      return str;
   }

   OrderBuilder builder = new OrderBuilder();

   Order TempOrder;

   public Order GetOrder(){
      return TempOrder;
   }

   public String GetBill(Order order){
      String temp = "";
      temp = Bill(order);
      return temp;
   }

}
