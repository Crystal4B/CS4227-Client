package billingsystem;

import order.*;
import hotelsystem.user.*;
import email.Email;

abstract class BillingTemplate {
   double discount = 0.0;
   CouponVisitor visitor = new CouponVisitor();
   public double BillCalc(Order order) {
      return order.getFinalCost()*(1-discount);
   }

   public void SendEmail(Order order, double num ){
      User user = order.getUser();
      new Email(user.getEmail(), "Platinum Hotels: Booking confirmation", Bill(order, num));
   }

   abstract public String Bill(Order order, double num);

   public CouponVisitor VisitorGet(){
      return visitor;
   }

   public void SetDiscount(double num){
      discount = num;
   }

   public double DiscountGet(){
      return discount;
   }

   public String AcceptCouponVisitor(CouponVisitor a){
      return a.CouponPaid(this);
   }

   abstract public double AcceptCouponVisitorCode(CouponVisitor a, String code);

   public String PercentConverter(CouponVisitor a, double num){
      return a.PercentConverter(num);
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
