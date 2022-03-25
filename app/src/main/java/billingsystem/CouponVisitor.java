package billingsystem;

public class CouponVisitor implements CouponInterface {
    public String CouponPaid(BillingTemplate b){
        if(b.DiscountGet() == 0.0){
           return "";
        } else {
           return "\n" + "Coupoun:\t\t" + b.PercentConverter(b.DiscountGet());
        }
     }
}
