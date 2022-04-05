package hotelsystem.billingsystem;

/**
 * @author Aleksandr Jakusevs
 * Interface class for coupons/vouchers
 */
public interface CouponInterface {
    
    /**
	* Method to check if a discount has been applied then add a string to the bill string
    * @param b the template object
    * @return Amount paid off
	*/
    String CouponPaid(BillingTemplate b);

    /**
	* Method to check if the inputted coupon is valid
    * @return Returns discount
	*/
    double CouponInput();

    /**
	* Method to check if the inputted voucher is valid
    * @return Returns voucher percent off
	*/
    double VoucherInput();

    /**
	* Simple set method
    * @param type the string to be set
	*/
    void TypeSet(String type);

    /**
	* Simple get method
    * @return type
	*/
    String TypeGet();

    /**
	* Simple set method
    * @param available the boolean to be set
	*/
    void AvailableSet(boolean available);

    /**
	* Simple get method
    * @return available
	*/
    boolean AvailableGet();

    /**
	* Simple get method
    * @return discount
	*/
    double DiscountGet();

    /**
	* Simple set method for discount
    * @param num the double to be set
	*/
    void DiscountSet(double num);

    /**
	* Simple set method for code
    * @param setC the string to be set
	*/
    void CodeSet(String setC);

    /**
	* Simple get method
    * @return code
	*/
    String CodeGet();

    /**
	* method to convert a double into a percent value eg 0.2 becomes 20%
    * @param num the double that is to be modified
    * @return Returns percent
	*/
    String PercentConverter(double num);
    
    /**
	* Simple set method
    * @param reservationId the int to be set
	*/
    void ReservationSet(int reservationId);

    /**
	* Simple get method
    * @return ReservationId
	*/
    int ReservationGet();
}
