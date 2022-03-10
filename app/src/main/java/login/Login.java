package login;

import hotelsystem.commands.LoginUserCommand;
import hotelsystem.commands.CommandInvoker;
import hotelsystem.user.User;
import hotelsystem.user.Customer;
import java.util.regex.Pattern;


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    CommandInvoker invoker;
    public User user;

    public boolean login(String email, String password) {
        invoker = new CommandInvoker();
        this.isValidEmail(email);
        if(validatesUser(email,password) != null) {
            return true;
        }
        else {
            return false;
        }
        
    }

    public void isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public User validatesUser(String email, String password) {

        user = new Customer();
        user.setEmail(email);
        user.setPassword(password);
        invoker.setCommand(new LoginUserCommand(user));
        invoker.execute();
        if(invoker.getResponse() == null) {
            return null;
        }
        else {
            return invoker.getResponse();
        }
        
    }

    public User returnUser() {
        return user;
    }
}
