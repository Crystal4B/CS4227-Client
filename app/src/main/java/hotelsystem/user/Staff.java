/**
 * @author Jakub Pažej
 * Staff class implementing the user abstract factory method.
 */
package hotelsystem.user;

import java.util.ArrayList;

public class Staff extends User
{
    final String type = "Staff";
    final ArrayList<String> permissions = new ArrayList<String>(){
        {
            permissions.add("CancelReservation");
            permissions.add("CreateReservation");
            permissions.add("CreateRooms");
            permissions.add("GetAvailableRooms");
            permissions.add("LoginUser");
            permissions.add("MacroReservation");
            permissions.add("RegisterUser");
            permissions.add("ReservationInvoker");
            permissions.add("SelectReservation");
        }};
    private double salary;
    private int holidayDaysAvailable;

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

    public void setSalary(double salary)
    {
        this.salary=salary;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setHolidays(int days)
    {
        this.holidayDaysAvailable=days;
    }

    public void addHolidays(int days)
    {
        this.holidayDaysAvailable+=days;
    }

    public int getHolidays()
    {
        return holidayDaysAvailable;
    }
}