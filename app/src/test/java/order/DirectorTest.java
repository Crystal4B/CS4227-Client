package order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DirectorTest {

    @Test void checkDirectorSetDates() {
        Director director = new Director();
		OrderBuilder builder = new OrderBuilder();
        director.setDates(builder, "2020-10-10", "2020-10-12");
        String expected = "Rooms: \n" +
        "\nNumber of Occupants: 0" +
        "\nStart Date: 2020-10-10 12:00:00.0" +
        "\nEnd Date: 2020-10-12 12:00:00.0" + 
        "\nNumber of Days Booked: 2" + 
        "\nRate Cost: EURO 0.0" + 
        "\nTotal Cost: EURO 0.0" + 
        "\n";
        assertEquals(director.viewCart(builder), expected);
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
    
}
