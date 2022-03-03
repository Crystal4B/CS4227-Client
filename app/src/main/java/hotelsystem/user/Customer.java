//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.user;

public class Customer extends Person
{
    private boolean paid;
    final String type = "Customer";

    public Customer(){}

    public Customer(String userName, String password, String email)
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
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setPaid(boolean bool)
    {
        this.paid=bool;
    }

    public boolean getPaid()
    {
        return paid;
    }
}