package hotelsystem.userfactory;

/**
 * @author Jakub Pažej
 * Guest class implementing the user.
 */
public class Guest implements UserInterface
{   

     protected String userName, password, email, firstName, lastName;
     protected int id;
     final static String type = "Guest";



    /**
     * Creates a guest account
     * 
     */

    public Guest() {
    }

    /**
     * Creates a guest account
     * 
     * @param firstName Firstname of user
     * @param lastName Lastname of user
     * @param ID id of User
     */
    public Guest(String firstName, String lastName, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = ID;
    }

    @Override
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void setPassword(String password)
    {
        this.password=password;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUserType() {
        return type;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLegalName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getLegalName() {
        return firstName + " " + lastName;
    }
}