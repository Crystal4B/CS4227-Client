package login;

import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.UserInterface;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.LoginUserCommand;
import hotelsystem.userFactory.Customer;
import java.util.regex.Pattern;


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    CommandInvoker invoker;
    public UserInterface userInterface;

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

    public boolean isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            return false;
        }
        return true;
    }

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

    public UserInterface returnUser() {
        return userInterface;
    }
}
