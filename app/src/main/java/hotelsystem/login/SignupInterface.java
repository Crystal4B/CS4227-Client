package hotelsystem.login;

import hotelsystem.userFactory.UserInterface;

/**
 * @author Eoin McDonough
 * Payment class to handle payment states
 */

public interface SignupInterface {
    /**
     * Signs in User
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Validation of user existing
     */
    boolean signup(String email, String password);
    /**
     * Sets username
     * 
     * @param username Username of user
     */
    void setName(String username);
    /**
     * Validates Email
     * 
     * @param email Email address of user
     * @return Validation of email
     */
    boolean isValidEmail(String email);
    /**
     * Registers user in database
     * 
     * @param email Email address of user
     * @param username Username of user
     * @param password Password of user
     * @return Returns user
     */
    UserInterface createsUser(String email, String username, String password);
    /**
     * Gets User
     * 
     * @return User thats logged in
     */
    UserInterface returnUser();
    /**
     * Sets Type
     * 
     * @param type Type of user
     */
    void setType(String type);
}