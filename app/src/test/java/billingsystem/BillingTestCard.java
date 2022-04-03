/**
 * @author Aleksandr Jakusevs
 */

package billingsystem;

import org.junit.jupiter.api.Test;

import hotelsystem.roomFactory.*;
import order.*;

import static org.junit.jupiter.api.Assertions.*;

class BillingTestCard {

    // Billing Unit Tests

    @Test void checkBillingCard() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createStandard(123, 2));
        Order order = builder.getOrder();
        BillingCard bill = new BillingCard();
        String expected = "Date:\t\t" + order.getStartDate() + "-"+ order.getEndDate() + 
                "\n" + "Description:\t\t" + "Room Charge\t" + 0.0  + bill.AcceptCouponVisitor(bill.VisitorGet()) +
                "\n" + "Total:\t\t" + 0.0 +
                "\n" + "Paid By:\t\tCard";
        assertEquals(bill.Bill(order),expected);
    }
}