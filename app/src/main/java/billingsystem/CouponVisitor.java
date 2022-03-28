package billingsystem;

public class CouponVisitor implements CouponInterface {
   Double discount = 0.0;
   String code = "";
   public String CouponPaid(BillingTemplate b){
        if(b.DiscountGet() == 0.0){
           return "";
        } else {
           return "\n" + "Coupoun:\t\t" + PercentConverter(b.DiscountGet());
        }
     }

   public double CouponInput() {
      if(code.equals("123")){
         discount = 0.1; 
         return discount;
      } else if(code.equals("12345")) {
         discount = 0.2; 
         return discount;
      } else if(code.equals("Amogus")){
         discount = 0.5; 
         return discount;
      } else {
          return 0;
      }
  }

   public double VoucherInput() {
      if(code.equals("123")){
         discount = 1.0; 
         return discount;
      } else {
         return 0.0;
      }
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
