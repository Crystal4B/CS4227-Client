package login;

import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.UserInterface;


public class LoginAdapter implements LoginInterface{
    private SignupInterface signup;
    public Customer person;
    private String username;
    public LoginAdapter(SignupInterface signup) {
        this.signup = signup;
    }
    /**
     * Logs in or Signs in User
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Validation of user existing
     */
    public boolean login(String email, String password) {
        return signup.signup(email, password);
    }
    /**
     * Validates Email
     * 
     * @param email Email address of user
     * @return Validation of email
     */
    public boolean isValidEmail(String email) {
        return signup.isValidEmail(email);
    }
    /**
     * Sets username
     * 
     * @param username Username of user
     */
    public void setName(String username) {
        signup.setName(username);
    }
    /**
     * Validates that user Exists and logs in or Creates user and signs in
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Returns user
     */
    public UserInterface validatesUser(String email, String password){
        return signup.createsUser(email, username, password);
    }
    /**
     * Gets User
     * 
     * @return User thats logged in
     */
    public UserInterface returnUser() {
        return signup.returnUser();
    }
    /**
     * Sets Type
     * 
     * @param type Type of user
     */
    public void setType(String userType) {
        signup.setType(userType);       
    }
}
