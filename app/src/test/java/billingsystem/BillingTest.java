/**
 * @author Aleksandr Jakusevs
 */

package billingsystem;

import org.junit.jupiter.api.Test;

import hotelsystem.roomFactory.*;
import order.*;

import static org.junit.jupiter.api.Assertions.*;

class BillingTest {

    // Billing Unit Tests

    @Test void checkBillingCashless() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createStandard(123, 2));
        Order order = builder.getOrder();
        BillingCashless bill = new BillingCashless();
        String expected = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
                "\n" + "Description:\t\t" + "Room Charge\t" + 0.0 +
                "\n" + "Total:\t\t" + 0.0 +
                "\n" + "Paid By:\t\tVoucher";
        assertEquals(bill.Bill(order),expected);
    }
}