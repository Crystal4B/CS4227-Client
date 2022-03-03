package login;

import hotelsystem.Person;
import hotelsystem.Customer;
import java.util.regex.Pattern;

public class Signup implements SignupInterface{
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public Customer person;
    private String username;

    public boolean signup(String email, String password) {
        this.isValidEmail(email);
        
        person = new Customer();
        person = this.createsUser(email, username, password); 
        return true;
    }
    public void setName(String username){
        this.username = username;
    }
    public void isValidEmail(String email) {
        
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
