package hotelsystem;

import hotelsystem.roomfactory.RoomFactory;
import hotelsystem.roomfactory.Room;
import hotelsystem.userfactory.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jordan Marshall
 */

class RoomFactoryTest {

    // Room Unit Tests

    @Test
    void checkSetRoomName() {
        Room room = new Room(123, 2);
        assertEquals(room.getRoomName(), null);
    }

    @Test
    void checkSetRoomNumber() {
        Room room = new Room(123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkSetRoomNumberOfBeds() {
        Room room = new Room( 123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkSetPerks() {
        Room room = new Room( 123, 2);
        room.setPerks("test perks");
        assertEquals(room.getPerks(), "test perks");
    }

    @Test
    void checkSetPrice() {
        Room room = new Room( 123, 2);
        room.setPrice(666.66);
        assertEquals(room.getPrice(), 666.66);
    }

    @Test
    void checkStandardAddOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        Room room = new Room( 123, 2);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkStandardAddOccupantsArray() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = new Room( 123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkStandardAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = new Room( 123, 2);
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

        Room room = new Room( 123, 2);
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

        Room room = new Room( 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkRemoveStandardOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = new Room( 123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkStandardToString() {
        Room room = new Room( 123, 2);
        assertEquals(room.toString(),"Room Name - null ; Room Number - 123 ; Beds - 2 ; Price - 200.0 ; Taken - false");
    }

    // Factory Room Unit Tests

    @Test
    void checkRoomName() {
        Room room = RoomFactory.createStandard(123, 2);
        assertEquals(room.getRoomName(), "Standard");
    }

    @Test
    void checkDeluxeRoomName() {
        Room room = RoomFactory.createDeluxe(123, 2);
        assertEquals(room.getRoomName(), "Deluxe");
    }

    @Test
    void checkVIPRoomName() {
        Room room = RoomFactory.createVIP(123, 2);
        assertEquals(room.getRoomName(), "VIP");
    }

    @Test
    void checkRoomNumber() {
        Room room = RoomFactory.createStandard(123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkDeluxeRoomNumber() {
        Room room = RoomFactory.createDeluxe(123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkVIPRoomNumber() {
        Room room = RoomFactory.createVIP(123, 2);
        assertEquals(room.getRoomNumber(), 123);
    }

    @Test
    void checkRoomNumberOfBeds() {
        Room room = RoomFactory.createStandard(123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkDeluxeRoomNumberOfBeds() {
        Room room = RoomFactory.createDeluxe(123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkVIPRoomNumberOfBeds() {
        Room room = RoomFactory.createVIP(123, 2);
        assertEquals(room.getNumberBeds(), 2);
    }

    @Test
    void checkSetRoomPerks() {
        Room room = RoomFactory.createStandard(123, 2);
        room.setPerks("test perks");
        assertEquals(room.getPerks(), "test perks");
    }

    @Test
    void checkSetDeluxePerks() {
        Room room = RoomFactory.createDeluxe(123, 2);
        room.setPerks("test perks");
        assertEquals(room.getPerks(), "test perks");
    }

    @Test
    void checkSetVIPPerks() {
        Room room = RoomFactory.createVIP(123, 2);
        room.setPerks("test perks");
        assertEquals(room.getPerks(), "test perks");
    }

    @Test
    void checkSetRoomPrice() {
        Room room = RoomFactory.createStandard(123, 2);
        room.setPrice(666.66);
        assertEquals(room.getPrice(), 666.66);
    }

    @Test
    void checkSetDeluxePrice() {
        Room room = RoomFactory.createDeluxe(123, 2);
        room.setPrice(666.66);
        assertEquals(room.getPrice(), 666.66);
    }

    @Test
    void checkSetVIPPrice() {
        Room room = RoomFactory.createVIP(123, 2);
        room.setPrice(666.66);
        assertEquals(room.getPrice(), 666.66);
    }

    @Test
    void checkAddOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        Room room = RoomFactory.createStandard(123, 2);
        room.addOccupant(user);
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkDeluxeAddOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("Johndoe2525");
        user.setLegalName("John", "Doe");
        user.setPaid(true);

        Room room = RoomFactory.createDeluxe(123, 2);
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

        Room room = RoomFactory.createVIP(123, 2);
        room.addOccupant(user);
        System.out.println(room.getOccupants());
        assertEquals(room.getOccupants().size(), 1);
        assertEquals(room.getOccupants().get(0).getLegalName(), "John Doe");
    }

    @Test
    void checkAddOccupantsArray() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = RoomFactory.createStandard(123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkDeluxeAddOccupantsArray() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = RoomFactory.createDeluxe(123, 2);
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

        Room room = RoomFactory.createVIP(123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = RoomFactory.createStandard(123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkDeluxeAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = RoomFactory.createDeluxe(123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkVIPAddOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = RoomFactory.createVIP(123, 2);
        room.addOccupants(occupants);
        assertEquals(room.getOccupants().size(), 3);
    }

    @Test
    void checkRemoveOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        Room room = RoomFactory.createStandard(123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkDeluxeRemoveOccupant() {
        Customer user = new Customer();
        user.setEmail("test@test.com");
        user.setPassword("#Password123");
        user.setUserName("John Doe");
        user.setPaid(true);

        Room room = RoomFactory.createDeluxe(123, 2);
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

        Room room = RoomFactory.createVIP(123, 2);
        room.addOccupant(user);
        room.removeOccupant(user);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkRemoveOccupants() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = RoomFactory.createStandard(123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkDeluxeRemoveOccupants() {
        UserInterface[] occupants = {
                new Staff(),
                new Customer(),
                new Guest()
        };

        Room room = RoomFactory.createDeluxe(123, 2);
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

        Room room = RoomFactory.createVIP(123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkRemoveOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = RoomFactory.createStandard(123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkRemoveDeluxeOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = RoomFactory.createDeluxe(123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkRemoveVIPOccupantsArrayList() {
        ArrayList<UserInterface> occupants = new ArrayList<>();
        occupants.add(new Staff());
        occupants.add(new Customer());
        occupants.add(new Guest());

        Room room = RoomFactory.createVIP(123, 2);
        room.addOccupants(occupants);
        room.removeOccupants(occupants);
        assertEquals(room.getOccupants().size(), 0);
    }

    @Test
    void checkRoomToString() {
        Room room = RoomFactory.createStandard(123, 2);
        assertEquals(room.toString(),"Room Name - Standard ; Room Number - 123 ; Beds - 2 ; Price - 200.0 ; Taken - false");
    }

    @Test
    void checkDeluxeToString() {
        Room room = RoomFactory.createDeluxe(123, 2);
        assertEquals(room.toString(),"Room Name - Deluxe ; Room Number - 123 ; Beds - 2 ; Price - 500.0 ; Taken - false");
    }

    @Test
    void checkVIPToString() {
        Room room = RoomFactory.createVIP(123, 2);
        assertEquals(room.toString(),"Room Name - VIP ; Room Number - 123 ; Beds - 2 ; Price - 1000.0 ; Taken - false");
    }
}