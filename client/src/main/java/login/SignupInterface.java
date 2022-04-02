package login;

import hotelsystem.userFactory.UserInterface;

public interface SignupInterface {
    public boolean signup(String email, String password);
    public void setName(String username);
    public boolean isValidEmail(String email);
    public UserInterface createsUser(String email, String username, String password);
    public UserInterface returnUser();
    public void setType(String type);
    public void twoFactorAuth(String email);
    public boolean checkAuth(int num);
}