package login;

import hotelsystem.Person;
import hotelsystem.Customer;
import java.util.regex.Pattern;

public class Signup {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public Customer person;

    public Signup(String email, String username, String password) {
        this.isValidEmail(email);
        person = new Customer();
        person = this.createsUser(email, username, password); 
    }

    private void isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public Customer createsUser(String email,String username, String password) {
        person.setEmail(email);
        person.setUserName(username);
        person.setPassword(password);

        return person;

    }

    public Person returnUser() {
        return person;
    }
}
