package login;

import hotelsystem.userFactory.UserInterface;

public interface LoginInterface {
    public boolean login(String email, String password);
    public boolean isValidEmail(String email);
    public UserInterface validatesUser(String email, String password);
    public UserInterface returnUser();
}
