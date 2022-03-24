package billingsystem;

import java.math.BigDecimal;
import order.*;
import hotelsystem.user.*;
import email.Email;

abstract class BillingTemplate {
   double discount = 0.0;
   String code = "";
   CouponVisitor visitor = new CouponVisitor();
   public double BillCalc(Order order) {
      return order.getFinalCost()*(1-discount);
   }

   abstract public double VouchInput();

   public void SendEmail(Order order, double num ){
      User user = order.getUser();
      new Email(user.getEmail(), "Platinum Hotels: Booking confirmation", Bill(order, num));
   }

   abstract public String Bill(Order order, double num);

   public void SetCode(String setC){
      code = setC;
   }

   public CouponVisitor VisitorGet(){
      return visitor;
   }

   public void SetDiscount(double num){
      discount = num;
   }

   public double DiscountGet(){
      return discount;
   }

   public String CodeGet(){
      return code;
   }

   public String AcceptCouponVisitor(CouponVisitor a){
      return a.CouponPaid(this);
   }

   public String PercentConverter(double num){
      String temp = Double.toString(num);
      if(temp.length() < 3){
         temp = Character.toString(temp.charAt(0));
         return temp + "00%";
      } else {
         temp = Character.toString(temp.charAt(2));
         return temp + "0%";
      }
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
