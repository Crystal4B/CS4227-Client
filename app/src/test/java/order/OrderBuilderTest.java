package order;

import org.junit.jupiter.api.Test;
import hotelsystem.roomFactory.Room;
import hotelsystem.roomFactory.RoomFactory;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * @author Jordan Marshall
 */

class OrderBuilderTest {

    // OrderBuilder Unit Tests

    @Test void checkOrderBuilderSetRooms() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(RoomFactory.createStandard(123, 2));
        rooms.add(RoomFactory.createStandard(123, 2));
        rooms.add(RoomFactory.createStandard(123, 2));
        builder.setRooms(rooms);
		Order order = builder.getOrder();
        assertEquals(order.getRooms().size(), 3);
    }

    @Test void checkOrderBuilderSetNumberOfOccupants() {
		OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createVIP(123, 2));
        builder.setNumberOfOccupants();
		Order order = builder.getOrder();
        assertEquals(order.getNumberOfOccupants(), 0);
    }

    @Test void checkOrderBuilderGetRoomsArrayListSize() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(RoomFactory.createStandard(123, 2));
        rooms.add(RoomFactory.createStandard(123, 2));
        rooms.add(RoomFactory.createStandard(123, 2));
        builder.setRooms(rooms);
        assertEquals(builder.getRoomsArrayListSize(), 3);
    }

    @Test void checkOrderBuilderRemoveRoom() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(RoomFactory.createStandard(123, 2));
        rooms.add(RoomFactory.createStandard(123, 2));
        builder.setRooms(rooms);
        builder.removeRoom(1);
        assertEquals(builder.getRoomsBuilder().size(), 1);
        assertEquals(builder.getRoomsBuilder().get(0).getRoomName(), "Standard");
    }

    @Test void checkOrderBuilderToString() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(RoomFactory.createStandard(123, 2));
        builder.setRooms(rooms);
        String expected = """
                Rooms:\s
                \tRoom Name - Standard ; Room Number - 123 ; Beds - 2 ; Price - 200.0 ; Taken - false

                Number of Occupants: 0
                Start Date: null
                End Date: null
                Number of Days Booked: 0
                Rate Cost: EURO 200.0
                Total Cost: EURO 0.0
                """;
        assertEquals(builder.toString(), expected);
    }
}
