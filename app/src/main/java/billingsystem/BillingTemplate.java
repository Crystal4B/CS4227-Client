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

   abstract public double VouchInput();

   public void SendEmail(Order order, double num ){
      UserInterface userInterface = order.getUser();
      new Email(userInterface.getEmail(), "Platinum Hotels: Booking confirmation", Bill(order, num));
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
