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
     * @return a username as String
     */
    String getUserName();

    /**
     * Sets a new password.
     * @param password new password as String
     */
    void setPassword(String password);

    /**
     * @return password as String
     */
    String getPassword();

    /**
     * Sets a new email.
     * @param email new email as String
     */
    void setEmail(String email);

    /**
     * @return email as String
     */
    String getEmail();

    /**
     * @return User Type as String
     */
    String getUserType();

    /**
     * Sets a new ID.
     * @param id new ID as int
     */
    void setId(int id);

    /**
     * @return ID as int
     */
    int getId();

    /**
     * Sets first name of User.
     * @param name new first name as String
     */
    void setFirstName(String name);

    /**
     * @return first name of user as String
     */
    String getFirstName();

    /**
     * Sets last name of User.
     * @param name new last name as String
     */
    void setLastName(String name);

    /**
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
     * @return first and last name of user as String
     */
    String getLegalName();
}