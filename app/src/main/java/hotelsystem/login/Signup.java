package hotelsystem.login;

import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.users.RegisterUserCommand;
import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.Staff;
import hotelsystem.userFactory.UserInterface;

import java.util.regex.Pattern;

/**
 * @author Eoin McDonough
 * Payment class to handle payment states
 */

public class Signup implements SignupInterface{
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public String type = "Customer";
    public UserInterface person;
    private String username;
    CommandInvoker invoker;
    boolean defaultPassword;

    /**
     * Signs in User
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Validation of user existing
     */

    public boolean signup(String email, String password) {
        invoker = new CommandInvoker();
        if (!isValidEmail(email)) {
            return false;
        }
        if("Staff".equals(type)) {
            person = new Staff();
            defaultPassword = true;
        }
        else {
            person = new Customer();
        }
        person = this.createsUser(email, username, password);
        invoker.setCommand(new RegisterUserCommand(person, defaultPassword));
        invoker.execute();
        if(invoker.getResponse() == null) {
            return false;
        }
        return true;
    }

    /**
     * Sets username
     * 
     * @param username Username of user
     */

    public void setName(String username){
        this.username = username;
    }

    /**
     * Sets Type
     * 
     * @param type Type of user
     */

    public void setType(String type){
        this.type = type;
    }

    /**
     * Validates Email
     * 
     * @param email Email address of user
     * @return Validation of email
     */

    public boolean isValidEmail(String email) {

        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        return pattern.matcher(email).matches();
    }

    /**
     * Registers user in database
     * 
     * @param email Email address of user
     * @param username Username of user
     * @param password Password of user
     * @return Returns user
     */

    public UserInterface createsUser(String email,String username, String password) {
        person.setEmail(email);
        person.setUserName(username);
        person.setPassword(password);

        return person;

    }

    /**
     * Gets User
     * 
     * @return User thats logged in
     */

    public UserInterface returnUser() {
        return person;
    }
}