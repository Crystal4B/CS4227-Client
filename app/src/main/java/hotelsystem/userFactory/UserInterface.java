package hotelsystem.userFactory;

/**
 * @author Jakub Pažej
 * Interface for user for abstract factory method implementation.
 */

public interface UserInterface
{
    void setUserName(String userName);
    String getUserName();
    void setPassword(String password);
    String getPassword();
    void setEmail(String email);
    String getEmail();
    String getUserType();
    void setId(int id);
    int getId();
    void setFirstName(String name);
    String getFirstName();
    void setLastName(String name);
    String getLastName();
    void setLegalName(String firstName, String lastName);
    String getLegalName();
}