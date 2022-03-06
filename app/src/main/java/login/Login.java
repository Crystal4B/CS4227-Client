package login;

import hotelsystem.commands.LoginUserCommand;
import hotelsystem.commands.ReservationInvoker;
import hotelsystem.user.User;
import hotelsystem.user.Customer;
import java.util.regex.Pattern;


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public User user;
    ReservationInvoker invoker;

    public boolean login(String email, String password) {
        invoker = new ReservationInvoker();
        this.isValidEmail(email);
        validatesUser(email,password);
        return true;
        
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
        return null;
        
    }

    public User returnUser() {
        return user;
    }
}
