package billingsystem;

import hotelsystem.userFactory.UserFactory;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.vouchers.ValidateVoucherCommand;

public class CouponVisitor implements CouponInterface {
   Double discount = 0.0;
   String code = "";
   String type = "";
   boolean available = true;
   UserFactory creator;
   
   public CouponVisitor(){
   }

   public CouponVisitor(String code, String type, double discount, boolean available){
      this.code = code;
      this.type = type;
      this.discount = discount;
      this.available = available;
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
      CouponVisitor couponBoi = this;
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
      CouponVisitor couponBoi = this;
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
