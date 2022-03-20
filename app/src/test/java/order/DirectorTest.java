package order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DirectorTest {

    // @Test void checkDirectorSetDatesUsingUI() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     director.setDatesUsingUI(builder, "2020-10-10", "2020-10-12");
    //     String expected = "Rooms: \n" +
    //     "\tRoom Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false\n" +
    //     "\nNumber of Occupants: 0" +
    //     "\nStart Date: 2020-10-10 12:00:00.0" +
    //     "\nEnd Date: 2020-10-12 12:00:00.0" + 
    //     "\nNumber of Days Booked: 2" + 
    //     "\nRate Cost: EURO 200.0" + 
    //     "\nTotal Cost: EURO 400.0" + 
    //     "\n";
    //     assertEquals(director.viewCart(builder), expected);
    // }

    // @Test void checkDirectorSetDatesUsingUIInvalidDate() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     assertFalse(director.setDatesUsingUI(builder, "2020-10-10", "2020-10-09"));
    // }

    // @Test void checkDirectorSetDatesUsingUIInvalidDateFormat() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     assertFalse(director.setDatesUsingUI(builder, "2020-m-1", "2020-10-09"));
    // }

    // @Test void checkDirectorAddRoomUsingUI() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     String expected = "0.\t Back\n\n" +
    //     "1.\tRoom Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false\n";
    //     assertEquals(director.viewRoomsInCart(builder), expected);
    // }

    // @Test void checkDirectorAddRoomUsingUIOptions() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     assertTrue(director.addRoomUsingUI(builder, 1));
    //     assertTrue(director.addRoomUsingUI(builder, 2));
    //     assertFalse(director.addRoomUsingUI(builder, 3));
    //     assertFalse(director.addRoomUsingUI(builder, 0));
    // }

    // @Test void checkDirectorRemoveRoomUsingUI() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     director.removeRoomUsingUI(builder, 1);
    //     String expected = "0.\t Back\n\n";
    //     assertEquals(director.viewRoomsInCart(builder), expected);
    // }

    // @Test void checkDirectorRemoveRoomUsingUIOptions() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     assertTrue(director.removeRoomUsingUI(builder, 0));
    //     assertTrue(director.removeRoomUsingUI(builder, 1));
    //     assertFalse(director.removeRoomUsingUI(builder, 2));
    // }

    // @Test void checkDirectorViewCart() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     String expected = "Rooms: \n" +
    //     "\tRoom Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false\n" +
    //     "\nNumber of Occupants: 0" +
    //     "\nStart Date: null" +
    //     "\nEnd Date: null" + 
    //     "\nNumber of Days Booked: 0" + 
    //     "\nRate Cost: EURO 200.0" + 
    //     "\nTotal Cost: EURO 0.0" + 
    //     "\n";
    //     assertEquals(director.viewCart(builder), expected);
    // }
    
    // @Test void checkDirectorViewRoomsInCart() {
    //     Director director = new Director();
	// 	OrderBuilder builder = new OrderBuilder();
    //     director.addRoomUsingUI(builder, 1);
    //     String expected = "0.\t Back\n\n" +
    //     "1.\tRoom Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false\n";
    //     assertEquals(director.viewRoomsInCart(builder), expected);
    // }
}
