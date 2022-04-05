package hotelsystem.userfactory;

/**
 * @author Jakub Pažej
 * Staff class implementing the user.
 */
public class Staff implements UserInterface
{
    protected String userName, password, email, firstName, lastName;
    protected int id;// ID for better database implementation and security reasons
    private int holidayDaysAvailable;
    private double salary;
    final static String type = "Staff";

    /**
     * Constructor for staff
     */
    public Staff(){}

    /**
     * Constructor for staff
     * @param userName  Username of staff user.
     * @param password Password of staff user.
     * @param email Email of staff user.
     */
    public Staff(String userName, String password, String email)
    {
        this.userName=userName;
        this.password=password;
        this.email=email;
    }

    @Override
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void setPassword(String password)
    {
        this.password=password;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUserType() {
        return type;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName=name;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName=name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLegalName(String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    @Override
    public String getLegalName() {
        return firstName+" "+lastName;
    }

    /**
     * Set salary for staff
     * @param salary The salary in Euro to be set 
     */
    public void setSalary(double salary)
    {
        this.salary=salary;
    }

    /**
     * Get salary for staff
     */
    public double getSalary()
    {
        return salary;
    }

    /**
     * Set holidays for staff.
     * @param days Number of days to be set for holidays for staff.
     */
    public void setHolidays(int days)
    {
        this.holidayDaysAvailable=days;
    }

    /**
     * Add holidays to staff.
     * @param days Number of days to be added for holidays for staff.
     */
    public void addHolidays(int days)
    {
        this.holidayDaysAvailable+=days;
    }

    /**
     * Get holidays for staff.
     */
    public int getHolidays()
    {
        return holidayDaysAvailable;
    }
}