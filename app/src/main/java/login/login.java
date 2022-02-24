package login;

import hotelsystem.Person;
import java.util.regex.Pattern;


public class login{
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    public final Person person;

    public login(String email, String password) {
        this.isValidEmail(email);
        person = this.validatesUser(email,password);
        
        
    }

    private void isValidEmail(String email) {
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    private Person validatesUser(String email, String password) {

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