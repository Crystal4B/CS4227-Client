//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem;

public class Customer extends Person
{
    private boolean paid;
    @Override public void setUserName(String userName)
    {
        this.userName = userName;
    }
    @Override public String getUserName(){
        return userName;
    }
    public void setPaid(boolean bool){this.paid=bool;}
    public boolean getPaid(){return paid;}
}