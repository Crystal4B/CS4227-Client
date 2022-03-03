package login;

import hotelsystem.Person;

public interface LoginInterface {
    public boolean login(String email, String password);
    public void isValidEmail(String email);
    public Person validatesUser(String email, String password);
    public Person returnUser();
}
