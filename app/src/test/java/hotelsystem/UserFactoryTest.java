package hotelsystem;

import hotelsystem.userfactory.Customer;
import hotelsystem.userfactory.Guest;
import hotelsystem.userfactory.Staff;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jordan Marshall
 */

class UserFactoryTest {

    // Customer Unit Tests

    @Test void checkCustomer() {
        Customer person = new Customer();
        assertNull(person.getUserName());
        assertNull(person.getPassword());
        assertNull(person.getEmail());
    }

    @Test void checkCustomerFilled() {
        Customer person = new Customer("TestUsername", "TestPassword", "test@test.com");
        assertEquals(person.getUserName(), "TestUsername");
        assertEquals(person.getPassword(), "TestPassword");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkCustomerUsername() {
        Customer person = new Customer();
        person.setUserName("John Doe");
        assertEquals(person.getUserName(), "John Doe");
    }

    @Test void checkCustomerPassword() {
        Customer person = new Customer();
        person.setPassword("#Password123");
        assertEquals(person.getPassword(), "#Password123");
    }

    @Test void checkCustomerEmail() {
        Customer person = new Customer();
        person.setEmail("test@test.com");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkCustomerType() {
        Customer person = new Customer();
        assertEquals(person.getUserType(), "Customer");
    }

    @Test void checkCustomerID() {
        Customer person = new Customer();
        person.setId(123);
        assertEquals(person.getId(), 123);
    }

    @Test void checkCustomerFirstName() {
        Customer person = new Customer();
        person.setFirstName("John");
        assertEquals(person.getFirstName(), "John");
    }

    @Test void checkCustomerLastName() {
        Customer person = new Customer();
        person.setLastName("Doe");
        assertEquals(person.getLastName(), "Doe");
    }

    @Test void checkCustomerLegalName() {
        Customer person = new Customer();
        person.setLegalName("John","Doe");
        assertEquals(person.getLegalName(), "John Doe");
    }

    @Test void checkCustomerPaid() {
        Customer person = new Customer();
        person.setPaid(true);
        assertTrue(person.getPaid());
    }

    // Staff Unit Tests

    @Test void checkStaff() {
        Staff person = new Staff();
        assertNull(person.getUserName());
        assertNull(person.getPassword());
        assertNull(person.getEmail());
    }

    @Test void checkStaffFilled() {
        Staff person = new Staff("TestUsername", "TestPassword", "test@test.com");
        assertEquals(person.getUserName(), "TestUsername");
        assertEquals(person.getPassword(), "TestPassword");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkStaffUserName() {
        Staff person = new Staff();
        person.setUserName("John Doe");
        assertEquals(person.getUserName(), "John Doe");
    }

    @Test void checkStaffPassword() {
        Staff person = new Staff();
        person.setPassword("#Password123");
        assertEquals(person.getPassword(), "#Password123");
    }

    @Test void checkStaffEmail() {
        Staff person = new Staff();
        person.setEmail("test@test.com");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkStaffType() {
        Staff person = new Staff();
        assertEquals(person.getUserType(), "Staff");
    }

    @Test void checkStaffID() {
        Staff person = new Staff();
        person.setId(123);
        assertEquals(person.getId(), 123);
    }
    
    @Test void checkStaffFirstName() {
        Staff person = new Staff();
        person.setFirstName("John");
        assertEquals(person.getFirstName(), "John");
    }

    @Test void checkStaffLastName() {
        Staff person = new Staff();
        person.setLastName("Doe");
        assertEquals(person.getLastName(), "Doe");
    }

    @Test void checkStaffLegalName() {
        Staff person = new Staff();
        person.setLegalName("John","Doe");
        assertEquals(person.getLegalName(), "John Doe");
    }

    @Test void checkStaffSalary() {
        Staff person = new Staff();
        person.setSalary(30000);
        assertEquals(person.getSalary(), 30000);
    }

    @Test void checkStaffHolidays() {
        Staff person = new Staff();
        person.setHolidays(5);
        person.addHolidays(3);
        assertEquals(person.getHolidays(), 8);
    }

    // Guest Unit Tests

    @Test void checkGuest() {
        Guest person = new Guest();
        assertNull(person.getFirstName());
        assertNull(person.getLastName());
        assertEquals(person.getId(), 0);
    }

    @Test void checkGuestFilled() {
        Guest person = new Guest("John", "Doe", 123);
        assertEquals(person.getFirstName(), "John");
        assertEquals(person.getLastName(), "Doe");
        assertEquals(person.getId(), 123);
    }

    @Test void checkGuestUserName() {
        Guest person = new Guest();
        person.setUserName("John Doe");
        assertEquals(person.getUserName(), "John Doe");
    }

    @Test void checkGuestPassword() {
        Guest person = new Guest();
        person.setPassword("#Password123");
        assertEquals(person.getPassword(), "#Password123");
    }

    @Test void checkGuestEmail() {
        Guest person = new Guest();
        person.setEmail("test@test.com");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkGuestType() {
        Guest person = new Guest();
        assertEquals(person.getUserType(), "Guest");
    }

    @Test void checkGuestID() {
        Guest person = new Guest();
        person.setId(123);
        assertEquals(person.getId(), 123);
    }

    @Test void checkGuestFirstName() {
        Guest person = new Guest();
        person.setFirstName("John");
        assertEquals(person.getFirstName(), "John");
    }

    @Test void checkGuestLastName() {
        Guest person = new Guest();
        person.setLastName("Doe");
        assertEquals(person.getLastName(), "Doe");
    }

    @Test void checkGuestLegalName() {
        Guest person = new Guest();
        person.setLegalName("John","Doe");
        assertEquals(person.getLegalName(), "John Doe");
    }
}