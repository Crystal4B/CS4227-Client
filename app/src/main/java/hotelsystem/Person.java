//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem;

public abstract class Person
{
    protected String userName, password;                              // username as String

    public abstract void setUserName(String userName);
    public abstract String getUserName();
    public abstract void setPassword(String password);
    public abstract String getPassword();
}