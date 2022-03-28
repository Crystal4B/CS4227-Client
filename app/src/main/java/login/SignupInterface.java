package login;

import hotelsystem.userFactory.UserFactory;

public interface SignupInterface {
    public boolean signup(String email, String password);
    public void setName(String username);
    public boolean isValidEmail(String email);
    public UserFactory createsUser(String email, String username, String password);
    public UserFactory returnUser();
    public void setType(String type);
    public void twoFactorAuth(String email);
    public boolean checkAuth(int num);
}