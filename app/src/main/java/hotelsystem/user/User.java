//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.user;

public abstract class User
{
    protected String userName, password, email;                              // username as String
    protected int id;

    public abstract void setUserName(String userName);
    public abstract String getUserName();
    public abstract void setPassword(String password);
    public abstract String getPassword();
    public abstract void setEmail(String email);
    public abstract String getEmail();
    public abstract String getUserType();
    public abstract void setId(int id);
    public abstract int getId();
}