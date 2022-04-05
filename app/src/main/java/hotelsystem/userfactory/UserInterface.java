package hotelsystem.userfactory;

/**
 * @author Jakub Pažej
 * Interface for user for abstract factory method implementation.
 */
public interface UserInterface
{

    /**
     * Sets a new username.
     * @param userName new username as String
     */
    void setUserName(String userName);

    /**
     * Gets username
     * @return a username as String
     */
    String getUserName();

    /**
     * Sets a new password.
     * @param password new password as String
     */
    void setPassword(String password);

    /**
     * Gets password
     * @return password as String
     */
    String getPassword();

    /**
     * Sets a new email.
     * @param email new email as String
     */
    void setEmail(String email);

    /**
     * Gets email
     * @return email as String
     */
    String getEmail();

    /**
     * Gets user type
     * @return User Type as String
     */
    String getUserType();

    /**
     * Sets a new ID.
     * @param id new ID as int
     */
    void setId(int id);

    /**
     * Gets is
     * @return ID as int
     */
    int getId();

    /**
     * Sets first name of User.
     * @param name new first name as String
     */
    void setFirstName(String name);

    /**
     * Gets first name
     * @return first name of user as String
     */
    String getFirstName();

    /**
     * Sets last name of User.
     * @param name new last name as String
     */
    void setLastName(String name);

    /**
     * Gets last name
     * @return last name of user as String
     */
    String getLastName();

    /**
     * Sets first and last name of User.
     * @param firstName new first name as String
     * @param lastName new last name as String
     */
    void setLegalName(String firstName, String lastName);

    /**
     * Gets legal name
     * @return first and last name of user as String
     */
    String getLegalName();
}