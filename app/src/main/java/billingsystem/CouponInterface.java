package billingsystem;

public interface CouponInterface {
    String CouponPaid(BillingTemplate b);

    double CouponInput();

    double VoucherInput();

    void TypeSet(String type);

    String TypeGet();

    void AvailableSet(boolean available);

    boolean AvailableGet();

    double DiscountGet();

    void DiscountSet(double num);

    void CodeSet(String setC);

    String CodeGet();

    String PercentConverter(double num);
    
    void ReservationSet(int reservationId);

    int ReservationGet();
}
