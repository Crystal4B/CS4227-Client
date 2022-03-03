package login;

import hotelsystem.commands.LoginUserCommand;
import hotelsystem.commands.ReservationInvoker;
import hotelsystem.user.Person;
import hotelsystem.user.Customer;
import java.util.regex.Pattern;


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public Person person;
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

    public Person validatesUser(String email, String password) {

        person = new Customer();
        person.setEmail(email);
        person.setPassword(password);
        invoker.setCommand(new LoginUserCommand(person));
        invoker.execute();
        return null;
        
    }

    public Person returnUser() {
        return person;
    }
}
