package login;

import hotelsystem.user.User;
import hotelsystem.user.Customer;


public class LoginAdapter implements LoginInterface{
    private SignupInterface signup;
    public Customer person;
    private String username;
    public LoginAdapter(SignupInterface signup) {
        this.signup = signup;
    }

    public boolean login(String email, String password) {
        return signup.signup(email, password);
    }
    public boolean isValidEmail(String email) {
        return signup.isValidEmail(email);
    }
    public void setName(String username) {
        signup.setName(username);
    }

    public User validatesUser(String email, String password){
        return signup.createsUser(email, username, password);
    }

    public User returnUser() {
        return signup.returnUser();
    }
}
