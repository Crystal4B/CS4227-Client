package hotelsystem.userFactory;

/**
 * @author Jakub Pažej
 * Interface for factory for abstract factory implementation.
 */

public interface FactoryInterface
{
    static UserInterface createStaff(){return null;}
    static UserInterface createStaff(String userName, String password, String email){return null;}
    static UserInterface createCustomer(){return null;}
    static UserInterface createCustomer(String userName, String password, String email){return null;}
    static UserInterface createGuest(){return null;}
    static UserInterface createGuest(String firstName, String lastName, int ID){return null;}
}