package billingsystem;

public interface CouponInterface {
    public String CouponPaid(BillingTemplate b);

    public double CouponInput();

    public double VoucherInput();

    public void TypeSet(String type);

    public String TypeGet();

    public void AvailableSet(boolean available);

    public boolean AvailableGet();

    public double DiscountGet();

    public void DiscountSet(double num);

    public void CodeSet(String setC);

    public String CodeGet();

    public String PercentConverter(double num);
}
