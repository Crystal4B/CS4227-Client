package hotelsystem;

import hotelsystem.user.Customer;
import hotelsystem.user.Staff;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    // Customer Unit Tests

    @Test void checkCustomerEmail() {
        Customer person = new Customer();
        person.setEmail("test@test.com");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkCustomerPassword() {
        Customer person = new Customer();
        person.setPassword("#Password123");
        assertEquals(person.getPassword(), "#Password123");
    }

    @Test void checkCustomerUsername() {
        Customer person = new Customer();
        person.setUserName("John Doe");
        assertEquals(person.getUserName(), "John Doe");
    }

    @Test void checkCustomerPaid() {
        Customer person = new Customer();
        person.setPaid(true);
        assertEquals(person.getPaid(), true);
    }

    // Staff Unit Tests

    @Test void checkStaffEmail() {
        Staff person = new Staff();
        person.setEmail("test@test.com");
        assertEquals(person.getEmail(), "test@test.com");
    }

    @Test void checkStaffPassword() {
        Staff person = new Staff();
        person.setPassword("#Password123");
        assertEquals(person.getPassword(), "#Password123");
    }

    @Test void checkStaffUserName() {
        Staff person = new Staff();
        person.setUserName("John Doe");
        assertEquals(person.getUserName(), "John Doe");
    }
}