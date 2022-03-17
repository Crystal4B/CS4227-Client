/**
 * @author Jakub Pažej
 * Guest class implementing the user abstract factory method.
 */
package hotelsystem.user;

import java.util.ArrayList;
import java.util.Arrays;

public class Guest extends User {
    final String type = "Guest";
    final ArrayList<String> permissions = new ArrayList<>(Arrays.asList(
            "RegisterUser"
    ));

    public Guest() {
    }

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