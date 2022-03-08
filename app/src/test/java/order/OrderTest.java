/**
 * @author Jordan Marshall
 */

package order;

import hotelsystem.room.Standard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

class RoomTest {

    // Order Unit Tests

    @Test void checkOrderRateCost() {
        
		OrderBuilder builder = new OrderBuilder();

        builder.addRoom(new Standard("Test Name", 123, 2));

		Order order = builder.getOrder();
        
        assertEquals(order.getRateCost(), 200);
    }

    @Test void checkOrderFinalCost() {
        
        OrderBuilder builder = new OrderBuilder();

        builder.addRoom(new Standard("Test Name", 123, 2));
        
        builder.setStartDate(Timestamp.valueOf("2020-10-10 12:00:00"));
        builder.setEndDate(Timestamp.valueOf("2020-10-12 12:00:00"));

        Order order = builder.getOrder();
        
        assertEquals(order.getFinalCost(), 400);
    }

    @Test void checkOrderStartDate(){
        OrderBuilder builder = new OrderBuilder();
        
        builder.setStartDate(Timestamp.valueOf("2020-10-10 12:00:00"));

        Order order = builder.getOrder();
        
        assertEquals(order.getStartDate(), Timestamp.valueOf("2020-10-10 12:00:00"));
    }

    @Test void checkOrderEndDate(){
        OrderBuilder builder = new OrderBuilder();
        
        builder.setEndDate(Timestamp.valueOf("2020-10-12 12:00:00"));

        Order order = builder.getOrder();
        
        assertEquals(order.getEndDate(), Timestamp.valueOf("2020-10-12 12:00:00"));
    }

}