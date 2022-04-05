package hotelsystem.userinterface;

import java.util.Scanner;

import hotelsystem.login.Login;
import hotelsystem.login.LoginAdapter;
import hotelsystem.login.Signup;
import hotelsystem.userfactory.UserInterface;

public class LoginUI {

    private static UserInterface userInterface;
    private static String userType = "Customer";
    
    /**
     * This runs the current state specified in UI.java and can also change sub-states.
     * @param console Used to read user input.
     * @return Returns boolean to break loop and change state.
     */
    public static int run(Scanner console){
        
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Login");
        System.out.println("2. \t Sign-up");
        System.out.println("3. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option;
        try {
            option = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return UI.LOGIN_STATE;
        }
        return switch (option) {
            case 1 -> login(console);
            case 2 -> signup(console);
            case 3 -> UI.EXIT;
            default -> UI.LOGIN_STATE;
        };
        
    }

    /**
     * This runs the login ui for user to login to account
     * @param console Used to read user input.
     * @return Returns correct menu for type of logged in user
     */

    public static int login(Scanner console) {
        System.out.println("Please enter email");
        String email = console.nextLine();
        System.out.println("Please enter password");
        String password = StaffUI.getPassword(console);
        Login login = new Login();
        if(login.login(email, password)) {
            setUser(login.returnUser());
            if(userInterface.getClass().getSimpleName().equals("Customer")){
                return UI.MENU_STATE;
            }
            else if(userInterface.getClass().getSimpleName().equals("Staff")){
                return UI.STAFF_MENU;
            }
        }
        else{
            System.out.println("Invalid User \nPlease try logging in again \n");
            return login(console);
        }
        return UI.LOGIN_STATE;
    }

    /**
     * This runs the signup ui for users to create a new account
     * @param console Used to read user input.
     * @return Returns correct menu 
     */

    public static int signup(Scanner console) {
        System.out.println("Please enter email");
        String email = console.nextLine();
        System.out.println("Please enter username");
        String username = console.nextLine();
        System.out.println("Please enter password");
        String password = StaffUI.getPassword(console);
        LoginAdapter signup = new LoginAdapter(new Signup());
        signup.setName(username);
        signup.setType(userType);
        boolean newAccount = signup.login(email, password);
        userInterface = signup.returnUser();
        if(newAccount == false){
            return UI.LOGIN_STATE;
        }
        if(userInterface.getClass().getSimpleName().equals("Staff")){
            return UI.STAFF_MENU;
        }
        else{
            return UI.MENU_STATE;
        }
    }
    
    /**
     * Returns the logged in user
     * @return Returns logged in user
     */

    public static UserInterface getUser(){
        return userInterface;
    }

    /**
     * Sets the logged in user
     * @param loggedInUser User to be logged in
     */

    public static void setUser(UserInterface loggedInUser){
        userInterface = loggedInUser;
    }

    /**
     * Sets the logged in users type
     * @param type UserType to set
     */

    public static void setType(String type){
        userType = type;
    }
}
