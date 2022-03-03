package login;

import hotelsystem.Person;
import java.util.regex.Pattern;


public class Login implements LoginInterface {
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public Person person;

    public void login(String email, String password) {
        this.isValidEmail(email);
        person = this.validatesUser(email,password);
        
        
    }

    public void isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public Person validatesUser(String email, String password) {

        //Selects user from database with email and returns them as a Person object


       /* Person user = 
            if (user.password == password) {
                return user;
            }
            else {
                return null;
            } */
        return null;
        
    }

    public Person returnUser() {
        return person;
    }
}
