package order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hotelsystem.roomFactory.Room;
import hotelsystem.roomFactory.RoomFactory;

public class DirectorTest {

    @Test void checkDirectorSetDatesValid() {
        Director director = new Director();
		OrderBuilder builder = new OrderBuilder();
        assertTrue(director.setDates(builder, "2020-10-10", "2020-10-12"));
    }

    @Test void checkDirectorSetDatesInvalid() {
        Director director = new Director();
		OrderBuilder builder = new OrderBuilder();
        assertFalse(director.setDates(builder, "string", "2020-10-dd"));
    }

    @Test void checkDirectorViewCart() {
        Director director = new Director();
		OrderBuilder builder = new OrderBuilder();
        String expected = "Rooms: \n" +
        "\nNumber of Occupants: 0" +
        "\nStart Date: null" +
        "\nEnd Date: null" + 
        "\nNumber of Days Booked: 0" + 
        "\nRate Cost: EURO 0.0" + 
        "\nTotal Cost: EURO 0.0" + 
        "\n";
        assertEquals(director.viewCart(builder), expected);
    }

    @Test void checkViewRoomsInCart(){
        Director director = new Director();
		OrderBuilder builder = new OrderBuilder();
        builder.addRoom(RoomFactory.createDeluxe(-1, 2));
        builder.addRoom(RoomFactory.createVIP(-1, 2));
        int size = director.viewRoomsInCart(builder);
        assertEquals(size, 2);
    }
    
}
