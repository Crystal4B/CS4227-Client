/**
 * @author Jordan Marshall
 */

package login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import hotelsystem.login.Login;
import hotelsystem.login.Signup;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.users.RemoveUserCommand;
import hotelsystem.userFactory.Customer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class LoginTest {

    // Signup Unit Tests
    static CommandInvoker invoker = new CommandInvoker();
    int prefix;

	@Order(1)
    @Test void checkSignup() {
        Signup signup = new Signup();
        prefix = new Random().nextInt(10000);

        assertTrue(signup.signup(prefix+"@test.com", "#Password123"));
    }
    
    @Test void checkSignupInvalidEmail() {
        Signup signup = new Signup();
        assertFalse(signup.signup("testTest.com", "#Password123"));
    }

    // Login Unit Tests
    @Order(2)
    @Test void checkLogin(){
        Login login = new Login();
        assertTrue(login.login("test@test.com", "#Password123"));
        assertEquals(login.returnUser().getEmail(), "test@test.com");
    }

    @Test void checkLoginForUnsignedUser(){
        Login login = new Login();
        assertFalse(login.login("not@user.com", "#Password123"));
    }

    @Test void checkLoginForUnsignedUserInvalidEmail(){
        Login login = new Login();
        assertFalse(login.login("notuser.com", "#Password123"));
    }

    @Test void checkLoginValidEmail(){
        Login login = new Login();
        assertTrue(login.isValidEmail("not@user.com"));
    }

    @Test void checkLoginNotValidEmail(){
        Login login = new Login();
        assertFalse(login.isValidEmail("notuser.com"));
    }

}