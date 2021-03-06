package hotelsystem.userfactory;

/**
 * @author Jakub Pažej
 * Interface for factory for abstract factory implementation.
 */
public interface FactoryInterface
{

    /**
     * Creates a Staff user account.
     * @return a UserInterface object
     */
    static UserInterface createStaff(){return null;}

    /**
     * Creates a Staff user account.
     * @param userName Username of staff
     * @param password Password
     * @param email Email
     * @return a UserInterface object
     */
    static UserInterface createStaff(String userName, String password, String email){return null;}

    /**
     * Creates a Customer user account.
     * @return a UserInterface object
     */
    static UserInterface createCustomer(){return null;}

    /**
    * Creates a Customer user account.
    * @param userName Username of Customer
    * @param password Password
    * @param email Email
    * @return a UserInterface object
    */
    static UserInterface createCustomer(String userName, String password, String email){return null;}

    /**
     * Creates a Guest user.
     * @return a UserInterface object
     */
    static UserInterface createGuest(){return null;}

    /**
     * Creates a Guest user.
     * @param firstName Firstname of Guest
     * @param lastName Last name of guest
     * @param ID Id of guest
     * @return a UserInterface object
     */
    static UserInterface createGuest(String firstName, String lastName, int ID){return null;}
}