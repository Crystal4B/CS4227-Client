package login;

import hotelsystem.user.User;

public interface SignupInterface {
    public boolean signup(String email, String password);
    public void setName(String username);
    public boolean isValidEmail(String email);
    public User createsUser(String email, String username, String password);
    public User returnUser();
    public void setType(String type);
}