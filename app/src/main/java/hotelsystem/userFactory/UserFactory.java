package hotelsystem.userFactory;

/**
 * @author Jakub Pažej
 * Factory method for making different types of users.
 */
public class UserFactory implements FactoryInterface
{
    public static UserInterface createStaff(){
        return new Staff();
    }
    public static UserInterface createStaff(String userName, String password, String email){
        return new Staff(userName, password, email);
    }
    public static UserInterface createCustomer(){
        return new Customer();
    }
    public static UserInterface createCustomer(String userName, String password, String email){
        return new Customer(userName, password, email);
    }
    public static UserInterface createGuest(){
        return new Staff();
    }
    public static UserInterface createGuest(String firstName, String lastName, int ID){
        return new Guest(firstName, lastName, ID);
    }
}