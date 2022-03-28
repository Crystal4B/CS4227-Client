/**
 * @author Jordan Marshall
 */

package hotelsystem;

import hotelsystem.roomFactory.Standard;
import hotelsystem.userFactory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class RoomFactoryTest {

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

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkStandardAddOccupantsArrayList() {
        ArrayList<UserFactory> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Standard room = new Standard("Test Name", 123, 2);
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

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkStandardRemoveOccupants() {
        UserFactory[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = new Standard("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    // Builder Room Unit Tests

    @Test
    void checkDeluxeRoomName() {
        Standard room = roomBuilder.buildDeluxe("Deluxe Room", 123, 2);
        assertEquals(room.getRoomName(), "Deluxe Room");
    }

    @Test
    void checkVIPRoomName() {
        Standard room = roomBuilder.buildVIP("VIP Room", 123, 2);
        assertEquals(room.getRoomName(), "VIP Room");
    }

    @Test
    void checkDeluxeRoomNumber() {
        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkVIPRoomNumber() {
        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkDeluxeRoomNumberOfBeds() {
        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkVIPRoomNumberOfBeds() {
        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
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

        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
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

        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
        room.addOccupant(user);
        System.out.println(room.getOccupants());
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkDeluxeAddOccupantsArray() {
        UserFactory[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkVIPAddOccupantsArray() {
        UserFactory[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkDeluxeAddOccupantsArrayList() {
        ArrayList<UserFactory> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkVIPAddOccupantsArrayList() {
        ArrayList<UserFactory> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
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

        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
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

        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkDeluxeRemoveOccupants() {
        UserFactory[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = roomBuilder.buildDeluxe("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkVIPRemoveOccupants() {
        UserFactory[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Standard room = roomBuilder.buildVIP("Test Name", 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }
}