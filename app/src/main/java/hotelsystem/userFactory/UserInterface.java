/**
 * @author Jakub Pažej
 * Abstract factory method for users of the hotel system.
 */
package hotelsystem.userFactory;

public interface UserInterface
{
    public abstract void setUserName(String userName);
    public abstract String getUserName();
    public abstract void setPassword(String password);
    public abstract String getPassword();
    public abstract void setEmail(String email);
    public abstract String getEmail();
    public abstract String getUserType();
    public abstract void setId(int id);
    public abstract int getId();
    public abstract void setFirstName(String name);
    public abstract String getFirstName();
    public abstract void setLastName(String name);
    public abstract String getLastName();
    public abstract void setLegalName(String firstName, String lastName);
    public abstract String getLegalName();
}