package billingsystem;

import order.*;
import hotelsystem.userFactory.*;
import email.Email;

/**
 * @author Aleksandr Jakusevs
 * Billing template class
 */
abstract class BillingTemplate {
   double discount = 0.0;
   CouponVisitor visitor = new CouponVisitor();
   
   /**
	* Method to calculate the discount if applied into the bill string
   * @param order the order the bill is attached to
	*/
   public double BillCalc(Order order) {
      return order.getFinalCost()*(1-discount);
   }

   /**
	* Method to send an email with the bill to the user
   * @param order the order the bill is attached to
	*/
   public void SendEmail(Order order){
      UserInterface user = order.getUser();
      new Email(user.getEmail(), "Platinum Hotels: Booking confirmation", Bill(order));
   }

   /**
	* Abstract method to be overriden through extension
   * @param order the order the bill is attached to
	*/
   abstract public String Bill(Order order);

   /**
   * Simple get method
	* @return CoupanVisitor object
	*/
   public CouponVisitor VisitorGet(){
      return visitor;
   }

   /**
	* Simple method to set the discount
   * @param num the number to which you want to set the discount to
	*/
   public void SetDiscount(double num){
      discount = num;
   }

   /**
   * Simple get method
	* @return discount
	*/
   public double DiscountGet(){
      return discount;
   }

   /**
	 * Method to accept a CouponVisitor object and use one of its functions to return a string
	 * @param a the CouponVisitor object that was accepted
	 */
   public String AcceptCouponVisitor(CouponVisitor a){
      return a.CouponPaid(this);
   }

   /**
	* Abstract method to be overriden through extension
   * @param a the CouponVisitor object that was accepted
	*/
   abstract public double AcceptCouponVisitorCode(CouponVisitor a);

   /**
	* Method to accept a CouponVisitor object and use one of its functions to return a string
   * @param a the CouponVisitor object that was accepted
   * @param num the double that is to be modified
	*/
   public String PercentConverter(CouponVisitor a, double num){
      return a.PercentConverter(num);
   }

   /**
	* Method to return the a bill for a specific order
   * @param order the order the bill is attached to
	*/
   public String GetBill(Order order){
      return Bill(order);
   }

}