package login;

import hotelsystem.user.User;

public interface LoginInterface {
    public boolean login(String email, String password);
    public boolean isValidEmail(String email);
    public User validatesUser(String email, String password);
    public User returnUser();
}
