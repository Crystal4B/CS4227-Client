package login;

import hotelsystem.userFactory.UserFactory;

public interface LoginInterface {
    public boolean login(String email, String password);
    public boolean isValidEmail(String email);
    public UserFactory validatesUser(String email, String password);
    public UserFactory returnUser();
}
