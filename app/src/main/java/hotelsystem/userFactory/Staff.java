/**
 * @author Jakub Pažej
 * Staff class implementing the user abstract factory method.
 */
package hotelsystem.userFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class Staff implements UserInterface
{
    protected String userName, password, email, firstName, lastName;
    protected int id;                                                        // ID for better database implementation and security reasons
    final String type = "Staff";
    final ArrayList<String> permissions = new ArrayList<>(Arrays.asList(
            "CancelReservation",
            "CreateReservation",
            "CreateRooms",
            "GetAvailableRooms",
            "LoginUser",
            "MacroReservation",
            "RegisterUser",
            "ReservationInvoker",
            "SelectReservation"
    ));
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

    @Override
    public void setFirstName(String name) {
        this.firstName=name;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName=name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLegalName(String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    @Override
    public String getLegalName() {
        return firstName+" "+lastName;
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