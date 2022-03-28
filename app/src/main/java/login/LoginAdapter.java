package login;

import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.Customer;


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

    public UserFactory validatesUser(String email, String password){
        return signup.createsUser(email, username, password);
    }

    public UserFactory returnUser() {
        return signup.returnUser();
    }

    public void setType(String userType) {
        signup.setType(userType);       
    }

    public void twoFactorAuth(String email) {
        signup.twoFactorAuth(email);
    }

    public boolean checkAuth(int num) {
        return signup.checkAuth(num);
    }
}
