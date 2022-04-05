package hotelsystem.login;

import hotelsystem.userfactory.UserInterface;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.users.LoginUserCommand;
import hotelsystem.userfactory.Customer;
import java.util.regex.Pattern;

/**
 * @author Eoin McDonough
 * Login class to handle logging in
 */

    /**
     * Logs in User
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
        return validatesUser(email, password) != null;
        
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
