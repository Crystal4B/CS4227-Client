package billingsystem;

import order.*;
import hotelsystem.userFactory.*;
import email.Email;

abstract class BillingTemplate {
   double discount = 0.0;
   CouponVisitor visitor = new CouponVisitor();
   public double BillCalc(Order order) {
      return order.getFinalCost()*(1-discount);
   }

   public void SendEmail(Order order){
      UserInterface user = order.getUser();
      new Email(user.getEmail(), "Platinum Hotels: Booking confirmation", Bill(order));
   }

   abstract public String Bill(Order order);

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

   abstract public double AcceptCouponVisitorCode(CouponVisitor a);

   public String PercentConverter(CouponVisitor a, double num){
      return a.PercentConverter(num);
   }

   Order TempOrder;

   public Order GetOrder(){
      return TempOrder;
   }

   public String GetBill(Order order){
      return Bill(order);
   }

}