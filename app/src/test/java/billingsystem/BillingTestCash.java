/**
 * @author Aleksandr Jakusevs
 */

package billingsystem;

import org.junit.jupiter.api.Test;

import hotelsystem.room.*;
import order.*;

import static org.junit.jupiter.api.Assertions.*;

class BillingTestCash {

    // Billing Unit Tests

    @Test void checkBillingCash() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Standard("Test Name", 123, 2));
        Order order = builder.getOrder();
        BillingCash bill = new BillingCash();
        String expected = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
                "\n" + "Description:\t\t" + "Room Charge\t" + 0.0 +
                "\n" + "Voucher:\t\t" +
                "\n" + "Total:\t\t" + 0.0 +
                "\n" + "Paid By:\t\tCash";
        assertEquals(bill.Bill(order),expected);
    }
}