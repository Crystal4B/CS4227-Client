/**
 * @author Jordan Marshall
 */

package order;

import org.junit.jupiter.api.Test;
import hotelsystem.roomFactory.Room;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class OrderBuilderTest {

    // OrderBuilder Unit Tests

    @Test void checkOrderBuilderSetRooms() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Test Name", 123, 2));
        rooms.add(new Room("Test Name", 123, 2));
        rooms.add(new Room("Test Name", 123, 2));
        builder.setRooms(rooms);
		Order order = builder.getOrder();
        assertEquals(order.getRooms().size(), 3);
    }

    @Test void checkOrderBuilderSetNumberOfOccupants() {
		OrderBuilder builder = new OrderBuilder();
        builder.addRoom(new Room("Test Name", 123, 2));
        builder.setNumberOfOccupants();
		Order order = builder.getOrder();
        assertEquals(order.getNumberOfOccupants(), 0);
    }

    @Test void checkOrderBuilderGetRoomsArrayListSize() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Test Name", 123, 2));
        rooms.add(new Room("Test Name", 123, 2));
        rooms.add(new Room("Test Name", 123, 2));
        builder.setRooms(rooms);
        assertEquals(builder.getRoomsArrayListSize(), 3);
    }

    @Test void checkOrderBuilderRemoveRoom() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Test Name 1", 123, 2));
        rooms.add(new Room("Test Name 2", 123, 2));
        builder.setRooms(rooms);
        builder.removeRoom(1);
        assertEquals(builder.getRoomsBuilder().size(), 1);
        assertEquals(builder.getRoomsBuilder().get(0).getRoomName(), "Test Name 1");
    }

    @Test void checkOrderBuilderToString() {
		OrderBuilder builder = new OrderBuilder();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Test Name", 123, 2));
        builder.setRooms(rooms);
        String expected = "Rooms: \n" +
        "\tRoom Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false\n" +
        "\nNumber of Occupants: 0" +
        "\nStart Date: null" +
        "\nEnd Date: null" + 
        "\nNumber of Days Booked: 0" + 
        "\nRate Cost: EURO 200.0" + 
        "\nTotal Cost: EURO 0.0" + 
        "\n";
        assertEquals(builder.toString(), expected);
    }
}
