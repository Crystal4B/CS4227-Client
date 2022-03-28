package billingsystem;

public interface CouponInterface {
    public String CouponPaid(BillingTemplate b);

    public double CouponInput();

    public double VoucherInput();

    public double DiscountGet();

    public void DiscountSet(double num);

    public void CodeSet(String setC);

    public String CodeGet();

    public String PercentConverter(double num);
}
