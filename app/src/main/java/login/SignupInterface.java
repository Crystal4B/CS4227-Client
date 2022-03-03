package login;

import hotelsystem.user.Person;

public interface SignupInterface {
    public boolean signup(String email, String password);
    public void setName(String username);
    public void isValidEmail(String email);
    public Person createsUser(String email,String username, String password);
    public Person returnUser();    
}