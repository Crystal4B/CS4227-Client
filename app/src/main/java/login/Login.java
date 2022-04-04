package login;

import hotelsystem.userFactory.UserInterface;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.users.LoginUserCommand;
import hotelsystem.userFactory.Customer;
import java.util.regex.Pattern;

/**
 * @author Eoin McDonough
 * Payment class to handle payment states
 */


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    CommandInvoker invoker;
    public UserInterface userInterface;

    /**
     * Logs in User
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Validation of user existing
     */

    public boolean login(String email, String password) {
        invoker = new CommandInvoker();
        if (!isValidEmail(email)) {
            return false;
        }
        if(validatesUser(email,password) != null) {
            return true;
        }
        else {
            return false;
        }
        
    }

    /**
     * Validates Email
     * 
     * @param email Email address of user
     * @return Validation of email
     */

    public boolean isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    /**
     * Validates that user Exists and logs in
     * 
     * @param email Email address of user
     * @param password Password of user
     * @return Returns user
     */

    public UserInterface validatesUser(String email, String password) {

        userInterface = new Customer();
        userInterface.setEmail(email);
        userInterface.setPassword(password);
        invoker.setCommand(new LoginUserCommand(userInterface));
        invoker.execute();
        if(invoker.getResponse() == null) {
            return null;
        }
        else {
            this.userInterface = invoker.getResponse();
            return this.userInterface;
        }
        
    }

    /**
     * Gets User
     * 
     * @return User thats logged in
     */

    public UserInterface returnUser() {
        return userInterface;
    }
}
