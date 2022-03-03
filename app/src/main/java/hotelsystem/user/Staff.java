//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.user;

public class Staff extends Person
{
    private double salary;

    public Staff(){}

    public Staff(String userName, String password, String email)
    {
        this.userName=userName;
        this.password=password;
        this.email=email;
    }

    @Override
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override public
    String getUserName()
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

    public void setSalary(double salary)
    {
        this.salary=salary;
    }

    public double getSalary()
    {
        return salary;
    }
}