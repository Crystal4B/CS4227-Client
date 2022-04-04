package login;

import hotelsystem.userFactory.UserInterface;

/**
 * @author Eoin McDonough
 * Payment class to handle payment states
 */

public interface LoginInterface {
    /**
     * Logs in User
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Validation of user existing
     */
    boolean login(String email, String password);
    /**
     * Validates Email
     * 
     * @param email Email address of user
     * @return Validation of email
     */
    boolean isValidEmail(String email);
    /**
     * Validates that user Exists and logs in
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Returns user
     */
    UserInterface validatesUser(String email, String password);
    /**
     * Gets User
     * 
     * @return User thats logged in
     */
    UserInterface returnUser();
}
