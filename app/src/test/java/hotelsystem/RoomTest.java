/**
 * @author Jordan Marshall
 */

package hotelsystem;

import hotelsystem.room.Standard;
import hotelsystem.user.Customer;
import hotelsystem.user.Staff;
import hotelsystem.room.Service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    // Standard Room Unit Tests

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

    @Test void checkStandardAddOccupant(){
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getUserName(), "John Doe");
    }

    @Test void checkStandardAddOccupants(){
        Customer[] occupants = {
            new Customer(),
            new Customer(),
            new Customer(),
            new Customer()
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test void checkStandardRemoveOccupant(){
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

    @Test void checkStandardRemoveOccupants(){
        Customer[] occupants = {
            new Customer(),
            new Customer(),
            new Customer(),
            new Customer()
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    // Service Room Unit Tests

    @Test void checkServiceRoomName() {
        Service room = new Service("Test Name", 123);
        assertEquals(room.getRoomName(), "Test Name");
    }

    @Test void checkServiceRoomNumber() {
        Service room = new Service("Test Name", 123);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test void checkServiceAddOccupant(){
        Staff user = new Staff();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");

        Service room = new Service("Test Name", 123);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getUserName(), "John Doe");
    }

    @Test void checkServiceAddOccupants(){
        Staff[] occupants = {
            new Staff(),
            new Staff(),
            new Staff(),
            new Staff()
        };

        Service room = new Service("Test Name", 123);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test void checkServiceRemoveOccupant(){
        Staff user = new Staff();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");

        Service room = new Service("Test Name", 123);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test void checkServiceRemoveOccupants(){
        Staff[] occupants = {
            new Staff(),
            new Staff(),
            new Staff(),
            new Staff()
        };

        Service room = new Service("Test Name", 123);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

}