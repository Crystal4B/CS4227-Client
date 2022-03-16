/**
 * @author Aleksandr Jakusevs
 */

package billing;

import org.junit.jupiter.api.Test;

import billingsystem.BillingCash;
import hotelsystem.room.Standard;
import order.*;

import static org.junit.jupiter.api.Assertions.*;

class BillingTest {

    // Billing Unit Tests

    @Test void checkBilling() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Standard("Test Name", 123, 2));
        Order order = builder.getOrder();
        BillingCash bill = new BillingCash();
        String expected = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
        "\n" + "Description:\t\t" + "Room Charge\t" + 0 + 
        "\n" + "Total:\t\t" + 0 +
        "\n" + "Paid By:\t\tVoucher";
        assertEquals(bill.Bill(order),expected);
    }
}