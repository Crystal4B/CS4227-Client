package order;

import hotelsystem.roomFactory.RoomFactory;
import hotelsystem.userFactory.Customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

/**
 * @author Jordan Marshall
 */

class OrderTest {

    // Order Unit Tests

    @Test void checkOrderID() {
		OrderBuilder builder = new OrderBuilder();
        builder.setOrderID("123");
		Order order = builder.getOrder();
        assertEquals(order.getOrderID(), "123");
    }

    @Test void checkOrderUser() {
		OrderBuilder builder = new OrderBuilder();
        builder.setUser(new Customer("username", "password", "email@test.com"));
		Order order = builder.getOrder();
        assertEquals(order.getUser().getUserName(), "username");
        assertEquals(order.getUser().getPassword(), "password");
        assertEquals(order.getUser().getEmail(), "email@test.com");
    }

    @Test void checkOrderGetRooms() {
		OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createStandard(123, 2));
        builder.addRoom(RoomFactory.createStandard(123, 2));
		Order order = builder.getOrder();
        assertEquals(order.getRooms().size(), 2);
    }

    @Test void checkOrderRateCost() {
		OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createStandard(123, 2));
		Order order = builder.getOrder();
        assertEquals(order.getRateCost(), 200);
    }

    @Test void checkOrderFinalCost() {
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createStandard(123, 2));
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
        builder.addRoom(RoomFactory.createStandard(123, 2));
        builder.setStartDate(Timestamp.valueOf("2020-10-10 12:00:00"));
        builder.setEndDate(Timestamp.valueOf("2020-10-12 12:00:00"));
        Order order = builder.getOrder();
        String expected = """
                Rooms:\s
                \tRoom Name - Standard ; Room Number - 123 ; Beds - 2 ; Price - 200.0 ; Taken - false

                Number of Occupants: 0
                Start Date: 2020-10-10 12:00:00.0
                End Date: 2020-10-12 12:00:00.0
                Number of Days Booked: 2
                Rate Cost: EURO 200.0
                Total Cost: EURO 400.0
                """;
        assertEquals(order.toString(), expected);
    }

    @Test void checkOrderEquals(){
        OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createStandard(123, 2));
        builder.setStartDate(Timestamp.valueOf("2020-10-10 12:00:00"));
        builder.setEndDate(Timestamp.valueOf("2020-10-12 12:00:00"));
        Order order = builder.getOrder();

        OrderBuilder builder2 = new OrderBuilder();
        builder2.addRoom(RoomFactory.createStandard(123, 2));
        Order order2 = builder2.getOrder();

        assertEquals(order, order);
        assertNotEquals(order, order2);
    }
}