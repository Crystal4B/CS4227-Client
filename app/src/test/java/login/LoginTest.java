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

}