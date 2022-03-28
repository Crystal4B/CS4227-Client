/**
 * @author Jordan Marshall
 */

package hotelsystem;

import hotelsystem.roomFactory.RoomFactory;
import hotelsystem.roomFactory.Room;
import hotelsystem.userFactory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class RoomFactoryTest {

    // Standard Room Unit Tests

    @Test
    void checkStandardRoomName() {
        Room room = new Room("Test Name", 123, 2);
        assertEquals(room.getRoomName(), "Test Name");
    }

    @Test
    void checkStandardRoomNumber() {
        Room room = new Room("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkStandardRoomNumberOfBeds() {
        Room room = new Room("Test Name", 123, 2);
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

        Room room = new Room("Test Name", 123, 2);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkStandardAddOccupantsArray() {
        UserFactory uf = new UserFactory();
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = new Room("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkStandardAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = new Room("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkStandardRemoveOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        Room room = new Room("Test Name", 123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkStandardRemoveOccupants() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = new Room("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    // Builder Room Unit Tests

    @Test
    void checkDeluxeRoomName() {
        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe("Deluxe Room", 123, 2);
        assertEquals(room.getRoomName(), "Deluxe Room");
    }

    @Test
    void checkVIPRoomName() {
        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        assertEquals(room.getRoomName(), "VIP Room");
    }

    @Test
    void checkDeluxeRoomNumber() {
        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe( "Deluxe Room", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkVIPRoomNumber() {
        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkDeluxeRoomNumberOfBeds() {
        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe("Deluxe Room", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkVIPRoomNumberOfBeds() {
        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkDeluxeAddOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe("Deluxe Room", 123, 2);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkVIPAddOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        room.addOccupant(user);
        System.out.println(room.getOccupants());
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkDeluxeAddOccupantsArray() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe("Deluxe Room", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkVIPAddOccupantsArray() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkDeluxeAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe( "Deluxe Room", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkVIPAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkDeluxeRemoveOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe( "Deluxe Room", 123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkVIPRemoveOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkDeluxeRemoveOccupants() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        RoomFactory rf = new RoomFactory();
        Room room = rf.createDeluxe("Deluxe Room", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkVIPRemoveOccupants() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        RoomFactory rf = new RoomFactory();
        Room room = rf.createVIP("VIP Room", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }
}