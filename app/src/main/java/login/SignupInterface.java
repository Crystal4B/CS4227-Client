package login;

import hotelsystem.Person;
import hotelsystem.Customer;

public interface SignupInterface {
    public void signup(String email, String password);
    public void setName(String username);
    public void isValidEmail(String email);
    public Person createsUser(String email,String username, String password);
    public Person returnUser();
    
}
