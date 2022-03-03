package hotelsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    // Standand Room Unit Tests

    @Test void checkStandardRoomName() {
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.getRoomName(), "Test Name");
    }

    @Test void checkStandardRoomNumber() {
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test void checkStandardRoomNumberOfBeds() {
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    //Deluxe Room Unit Tests

    @Test void checkDeluxeRoomName() {
        Deluxe room = new Deluxe("Test Name", 123, 2);
        assertEquals(room.getRoomName(), "Test Name");
    }

    @Test void checkDeluxeRoomNumber() {
        Deluxe room = new Deluxe("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test void checkDeluxeRoomNumberOfBeds() {
        Deluxe room = new Deluxe("Test Name", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    // VIP Room Unit Tests

    @Test void checkVIPRoomName() {
        VIP room = new VIP("Test Name", 123, 2);
        assertEquals(room.getRoomName(), "Test Name");
    }

    @Test void checkVIPRoomNumber() {
        VIP room = new VIP("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test void checkVIPRoomNumberOfBeds() {
        VIP room = new VIP("Test Name", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }
}