package billingsystem;

import java.math.BigDecimal;
import order.*;
import hotelsystem.user.*;
import email.Email;

abstract class BillingTemplate {
   double temp = 0.0;
   public double BillCalc(Order order) {
      return order.getFinalCost()*(1-temp);
   }

   public BigDecimal RoundToTwoDec(double num) {
      BigDecimal temp = new BigDecimal(Double.toString(num));
      temp = temp.setScale(2);
      return temp;
   }

   public void SendEmail(Order order, double num ){
      User user = order.getUser();
      Email email = new Email(user.getEmail(), "Platinum Hotels: Booking confirmation", Bill(order, num));
   }

   abstract public String Bill(Order order, double num);

   public String CouponPaid(){

      if(temp == 0.0){
         return "";
      } else {
         return "\n" + "Coupoun:\t\t" + temp;
      }
   }

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
      temp = Bill(order, 0);
      return temp;
   }

}
