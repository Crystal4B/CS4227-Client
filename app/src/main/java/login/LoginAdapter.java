package login;

import hotelsystem.Person;
import hotelsystem.Customer;


public class LoginAdapter implements LoginInterface{
    private SignupInterface signup;
    public Customer person;
    private String username;
    public LoginAdapter(SignupInterface signup) {
        this.signup = signup;
    }

    public boolean login(String email, String password) {
        System.out.println("Using adapter \n");
        return signup.signup(email, password);
    }
    public void isValidEmail(String email) {
        System.out.println("Using adapter \n");
        signup.isValidEmail(email);
    }
    public void setName(String username) {
        signup.setName(username);
    }

    public Person validatesUser(String email, String password){
        return signup.createsUser(email, username, password);
    }

    public Person returnUser() {
        return signup.returnUser();
    }
}
