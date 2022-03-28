package hotelsystem.userFactory;

public class UserFactory
{
    public static UserInterface createEmpty(String UserType)
    {
        if ( UserType.equals("customer") )
            return new Customer();
        else if ( UserType.equals("guest") )
            return new Guest();
        else if ( UserType.equals("staff") )
            return new Staff();

        return null;
    }

    public static UserInterface createFilled(String UserType, String userName, String password, String email)
    {
        if ( UserType.equals("customer") )
            return new Customer(userName, password, email);
        else if ( UserType.equals("staff") )
            return new Staff(userName, password, email);

        return null;
    }

    public static UserInterface createFilledGuest(String firstName, String lastName, int ID)
    {
        return new Guest(firstName, lastName, ID);
    }
}