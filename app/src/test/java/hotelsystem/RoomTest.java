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

import java.util.ArrayList;

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
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupant(user.getLegalName());
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0), "John Doe");
    }

    @Test void checkStandardAddOccupantsArray(){
        String[] occupants = {
            "John Doe",
            "John 2",
            "John 3",
            "John 4"
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test void checkStandardAddOccupantsArrayList(){
        ArrayList<String> occupants = new ArrayList<>();
        occupants.add("John Doe");
        occupants.add("John 2");
        occupants.add("John 3");
        occupants.add("John 4");

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
        room.addOccupant(user.getLegalName());
        room.removeOccupant(user.getLegalName());
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test void checkStandardRemoveOccupantsArray(){
        String[] occupants = {
                "John Doe",
                "John 2",
                "John 3",
                "John 4"
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test void checkStandardRemoveOccupantsArrayList(){
        ArrayList<String> occupants = new ArrayList<>();
        occupants.add("John Doe");
        occupants.add("John Doe 1");
        occupants.add("John Doe 2");
        occupants.add("John Doe 3");


        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test void checkStandardIsTaken(){
        Standard room = new Standard("Test Name", 123, 2);
        room.setTaken(true);
        assertEquals(room.getTaken(), true);
    }

    @Test void checkStandardToString(){
        Standard room = new Standard("Test Name", 123, 2);
        assertEquals(room.toString(), "Room Name: Test Name ; Room Number: 123 ; Beds: 2 ; Price: 200.0 ; Taken?: false");
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
        user.setUserName("Johndoe2525");
        user.setLegalName("John","Doe");

        Service room = new Service("Test Name", 123);
        room.addOccupant(user.getLegalName());
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0), "John Doe");
    }

    @Test void checkServiceAddOccupantsArray(){
        String[] occupants = {
                "John Doe",
                "John 2",
                "John 3",
                "John 4"
        };

        Service room = new Service("Test Name", 123);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test void checkServiceAddOccupantsArrayList(){
        ArrayList<String> occupants = new ArrayList<>();
        occupants.add("John Doe");
        occupants.add("John 2");
        occupants.add("John 3");
        occupants.add("John 4");

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 4);
    }

    @Test void checkServiceRemoveOccupant(){
        Staff user = new Staff();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");

        Service room = new Service("Test Name", 123);
        room.addOccupant(user.getLegalName());
        room.removeOccupant(user.getLegalName());
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test void checkServiceRemoveOccupants(){
        String[] occupants = {
                "John Doe",
                "John 2",
                "John 3",
                "John 4"
        };

        Service room = new Service("Test Name", 123);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

}