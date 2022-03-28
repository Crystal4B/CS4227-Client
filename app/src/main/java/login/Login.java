package login;

import hotelsystem.userFactory.UserFactory;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.LoginUserCommand;
import hotelsystem.userFactory.Customer;
import java.util.regex.Pattern;


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    CommandInvoker invoker;
    public UserFactory userFactory;

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

    public UserFactory validatesUser(String email, String password) {

        userFactory = new Customer();
        userFactory.setEmail(email);
        userFactory.setPassword(password);
        invoker.setCommand(new LoginUserCommand(userFactory));
        invoker.execute();
        if(invoker.getResponse() == null) {
            return null;
        }
        else {
            this.userFactory = invoker.getResponse();
            return this.userFactory;
        }
        
    }

    public UserFactory returnUser() {
        return userFactory;
    }
}
