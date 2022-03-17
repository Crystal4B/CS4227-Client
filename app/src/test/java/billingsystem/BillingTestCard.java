/**
 * @author Aleksandr Jakusevs
 */

package billingsystem;

import org.junit.jupiter.api.Test;

import hotelsystem.room.*;
import order.*;

class BillingTestCard {

    // Billing Unit Tests

    @Test void checkBillingCard() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Standard("Test Name", 123, 2));
        Order order = builder.getOrder();
        BillingCard bill = new BillingCard();
        String expected = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
                "\n" + "Description:\t\t" + "Room Charge\t" + 0.0 +
                "\n" + "Voucher:\t\t" +
                "\n" + "Total:\t\t" + 0.0 +
                "\n" + "Paid By:\t\tCard";
        assertEquals(bill.Bill(order),expected);
    }
}