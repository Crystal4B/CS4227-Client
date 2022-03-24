package login;

import hotelsystem.user.User;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.RegisterUserCommand;
import hotelsystem.user.Customer;
import hotelsystem.user.Staff;

import java.util.regex.Pattern;

public class Signup implements SignupInterface{
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public String type = "Customer";
    public User person;
    private String username;
    CommandInvoker invoker;

    public boolean signup(String email, String password) {
        invoker = new CommandInvoker();
        if (!isValidEmail(email)) {
            return false;
        }
        if("Staff".equals(type)) {
            person = new Staff();
        }
        else {
            person = new Customer();
        }
        person = this.createsUser(email, username, password); 
        invoker.setCommand(new RegisterUserCommand(person));
        invoker.execute();
        return true;
    }
    public void setName(String username){
        this.username = username;
    }
    public void setType(String type){
        this.type = type;
    }
    public boolean isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            return false;
        }

        return true;
    }

    public User createsUser(String email,String username, String password) {
        person.setEmail(email);
        person.setUserName(username);
        person.setPassword(password);

        return person;

    }

    public User returnUser() {
        return person;
    }
}
