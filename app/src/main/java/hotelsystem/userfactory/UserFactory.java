package hotelsystem.userfactory;

/**
 * @author Jakub Pažej
 * Factory method for making different types of users.
 */
public class UserFactory implements FactoryInterface
{
	/**
     * Creates a Staff user account.
     * @return a UserInterface object
     */
    public static UserInterface createStaff(){
        return new Staff();
    }

	/**
     * Creates a Staff user account.
     * @param userName Username of staff
     * @param password Password
     * @param email Email
     * @return a UserInterface object
     */
    public static UserInterface createStaff(String userName, String password, String email){
        return new Staff(userName, password, email);
    }

	/**
     * Creates a Customer user account.
     * @return a UserInterface object
     */
    public static UserInterface createCustomer(){
        return new Customer();
    }

	/**
    * Creates a Customer user account.
    * @param userName Username of Customer
    * @param password Password
    * @param email Email
    * @return a UserInterface object
    */
    public static UserInterface createCustomer(String userName, String password, String email){
        return new Customer(userName, password, email);
    }

	/**
     * Creates a Guest user.
     * @return a UserInterface object
     */
    public static UserInterface createGuest(){
        return new Staff();
    }

    /**
     * Creates a Guest user.
     * @param firstName Firstname of Guest
     * @param lastName Last name of guest
     * @param ID Id of guest
     * @return a UserInterface object
     */
    public static UserInterface createGuest(String firstName, String lastName, int ID){
        return new Guest(firstName, lastName, ID);
    }
}