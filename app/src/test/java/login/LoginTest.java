/**
 * @author Jordan Marshall
 */

package login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    // Signup & Login Unit Tests
    
    @Test void checkSignup() {
        Signup signup = new Signup();
        assertTrue(signup.signup("test@test.com", "#Password123"));
    }

    @Test void checkLogin(){
        Login login = new Login();
        assertTrue(login.login("test@test.com", "#Password123"));
    }

    @Test void checkLoginForUnsignedUser(){
        Login login = new Login();
        assertFalse(login.login("not@user.com", "#Password123"));
    }

    @Test void validEmail(){
        Login login = new Login();
        assertTrue(login.isValidEmail("not@user.com"));
    }

    @Test void notValidEmail(){
        Login login = new Login();
        assertFalse(login.isValidEmail("notuser.com"));
    }

}