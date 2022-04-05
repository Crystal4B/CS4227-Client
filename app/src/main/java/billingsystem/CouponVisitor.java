package billingsystem;

import requestsystem.commands.CommandInvoker;
import requestsystem.commands.vouchers.ValidateVoucherCommand;

/**
 * @author Aleksandr Jakusevs
 * Coupon/voucher class
 */
public class CouponVisitor implements CouponInterface {
   Double discount = 0.0;
   String code = "";
   String type = "";
   int reservationId;
   boolean available = true;
   
   /**
	 * Simple constructor for the CouponVisitor
	 */
   public CouponVisitor(){
   }

   /**
	 * Simple constructor for the CouponVisitor
	 * @param code the id of the voucher
	 */
   public CouponVisitor(String code){
      this.code = code;
   }

   /**
	 * Simple constructor for the CouponVisitor
	 * @param code the id of the voucher
	 * @param type the type of the voucher
    * @param discount the discount value of the voucher
    * @param available the availability of the voucher
	 */
   public CouponVisitor(String code, String type, double discount, boolean available){
      this.code = code;
      this.type = type;
      this.discount = discount;
      this.available = available;
   }

   /**
	 * Simple constructor for the CouponVisitor
	 * @param code the id of the voucher
	 * @param type the type of the voucher
    * @param discount the discount value of the voucher
    * @param available the availability of the voucher
    * @param reservationId the id of the reservation to which the voucher is attached to
	 */
   public CouponVisitor(String code, String type, double discount, boolean available, int reservationId){
      this.code = code;
      this.type = type;
      this.discount = discount;
      this.available = available;
      this.reservationId = reservationId;
   }
   
   public String CouponPaid(BillingTemplate b){
        if(b.DiscountGet() == 0.0){
           return "";
        } else {
           return "\n" + "Coupoun:\t\t" + PercentConverter(b.DiscountGet());
        }
     }

   public double CouponInput() {
      CommandInvoker commandInvoker = new CommandInvoker();
      ValidateVoucherCommand commandBoi = new ValidateVoucherCommand(this);
      commandInvoker.setCommand(commandBoi);
      commandInvoker.execute();
      CouponVisitor couponBoi;
      couponBoi = commandInvoker.getResponse();
      if(couponBoi.TypeGet().equalsIgnoreCase("coupon")){
         if(couponBoi.AvailableGet()){
            return couponBoi.DiscountGet();
         } else {
            return 0.0;
         }
      } else {
         return 0.0;
      }
  }

   public double VoucherInput() {
      CommandInvoker commandInvoker = new CommandInvoker();
      ValidateVoucherCommand commandBoi = new ValidateVoucherCommand(this);
      commandInvoker.setCommand(commandBoi);
      commandInvoker.execute();
      CouponVisitor couponBoi;
      couponBoi = commandInvoker.getResponse();
      if(couponBoi.TypeGet().equalsIgnoreCase("voucher")){
         if(couponBoi.AvailableGet()){
            return couponBoi.DiscountGet();
         } else {
            return 0.0;
         }
      } else {
         return 0.0;
      }
   }

   public void ReservationSet(int reservationId){
      this.reservationId = reservationId;
   }

   public int ReservationGet(){
      return reservationId;
   }

   public void TypeSet(String type){
      this.type = type;
   }

   public String TypeGet(){
      return type;
   }

   public void AvailableSet(boolean available){
      this.available = available;
   }

   public boolean AvailableGet(){
      return available;
   }

   public void DiscountSet(double num){
      discount = num;
   }

   public double DiscountGet(){
      return discount;
   }

   public String CodeGet(){
      return code;
   }

   public void CodeSet(String setC){
      code = setC;
   }

   public String PercentConverter(double num){
      String temp = Double.toString(num);
      if(temp.length() < 3){
         return "0";
      } 
      if(num < 1){
         temp = Character.toString(temp.charAt(0));
         return temp + "0%";
      } else {
         temp = Character.toString(temp.charAt(2));
         return temp + "00%";
      }
   }
   
}
