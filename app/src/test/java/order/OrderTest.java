package order;

import hotelsystem.room.Deluxe;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 

class RoomTest {

    // Order Unit Tests

    @Test void checkOrderRateCost() {
        
		OrderBuilder builder = new OrderBuilder();

        builder.addRoom(new Standard("Test Name", 123, 2));
        builder.addRoom(new Deluxe("Test Name", 321, 3));
        builder.addRoom(new VIP("Test Name", 111, 4));

		Order order = builder.getOrder();
        
        assertEquals(order.getRateCost(), 1700);
    }

    // TODO: Add back in once date is changed in Director.java

    // @Test void checkOrderFinalCost() {
    //     ArrayList<Room> rooms = new ArrayList<>();
    //     rooms.add(new Standard("Test Name", 123, 2));
    //     rooms.add(new Deluxe("Test Name", 321, 3));
    //     rooms.add(new VIP("Test Name", 111, 4));
        
    //     Director director = new Director();
	// 	   OrderBuilder builder = new OrderBuilder();
	// 	   director.constructOrder(builder, rooms);
	// 	   Order order = builder.getOrder();
        
    //     assertEquals(order.getFinalCost(), 1700);
    // }

}