package login;

import hotelsystem.user.Person;

public interface LoginInterface {
    public void login(String email, String password);
    public void isValidEmail(String email);
    public Person validatesUser(String email, String password);
    public Person returnUser();
}
