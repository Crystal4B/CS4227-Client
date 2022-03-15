/**
 * @author Aleksandr Jakusevs
 */

package billing;

import org.junit.jupiter.api.Test;

import hotelsystem.billing.Billing;
import hotelsystem.room.Standard;
import order.*;

import static org.junit.jupiter.api.Assertions.*;

class BillingTest {

    // Billing Unit Tests

    @Test void checkBilling() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Standard("Test Name", 123, 2));
        Order order = builder.getOrder();
        Billing bill = new Billing();
        String expected = "Date:              Description:                Voucher:               Charges:"
        + "\n" + "SD:  null  ED:  null \t\t\t" +  "Room Charge\t\t\t" + "\t\t\t" + 200.0 + "\t\t\t" 
        + "\n" + "\t\t\t\t\t\t\t\t Total: " + 1000.0;
        //assertEquals(bill.Bill(order),expected);
    }
}