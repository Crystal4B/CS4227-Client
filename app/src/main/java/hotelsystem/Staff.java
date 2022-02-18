//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem;

public class Staff extends Person
{
    private double salary;
    @Override public void setUserName(String userName)
    {
        this.userName = userName;
    }
    @Override public String getUserName(){
        return userName;
    }
    public void setSalary(double salary){this.salary=salary;}
    public double getSalary(){return salary;}
}