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

    @Test void checkOrderID() {
		OrderBuilder builder = new OrderBuilder();
        builder.setOrderID("123");
		Order order = builder.getOrder();
        assertEquals(order.getOrderID(), "123");
    }

    @Test void checkOrderGetRooms() {
		OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Standard("Test Name", 123, 2));
        builder.addRoom(new Standard("Test Name", 123, 2));
		Order order = builder.getOrder();
        assertEquals(order.getRooms().size(), 2);
    }

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

    @Test void checkOrderToString(){
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Standard("Test Name", 123, 2));
        builder.setStartDate(Timestamp.valueOf("2020-10-10 12:00:00"));
        builder.setEndDate(Timestamp.valueOf("2020-10-12 12:00:00"));
        Order order = builder.getOrder();
        String expected = "Rooms: \n" +
        "\tRoom Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false\n" +
        "\nNumber of Occupants: 0" +
        "\nStart Date: 2020-10-10 12:00:00.0" +
        "\nEnd Date: 2020-10-12 12:00:00.0" + 
        "\nNumber of Days Booked: 2" + 
        "\nRate Cost: EURO 200.0" + 
        "\nTotal Cost: EURO 400.0" + 
        "\n";
        assertEquals(order.toString(), expected);
    }
}