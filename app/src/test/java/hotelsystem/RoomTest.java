/**
 * @author Jordan Marshall
 */

package hotelsystem;

import hotelsystem.room.Standard;
import hotelsystem.user.Customer;
import hotelsystem.user.Guest;
import hotelsystem.user.Staff;
import hotelsystem.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class RoomTest {

    // Standard Room Unit Tests

    @Test
    void checkStandardRoomName() {
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.getRoomName(), "Test Name");
    }

    @Test
    void checkStandardRoomNumber() {
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkStandardRoomNumberOfBeds() {
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkStandardAddOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0), "John Doe");
    }

    @Test
    void checkStandardAddOccupantsArray() {
        User[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test
    void checkStandardAddOccupantsArrayList() {
        ArrayList<User> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test
    void checkStandardRemoveOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkStandardRemoveOccupants() {
        User[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }
}