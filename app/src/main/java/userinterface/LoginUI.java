package userinterface;

import java.io.Console;

import hotelsystem.userFactory.UserInterface;
import login.Login;
import login.LoginAdapter;
import login.Signup;

public class LoginUI {

    private static UserInterface userInterface;
    private static String userType = "Customer";
    
    /**
     * Run function of current state.
     * @param console Used to read user input.
     * @return The next state.
     */
    public static int run(Console console){
        
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
            option = Integer.parseInt(console.readLine());
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

    public static int login(Console console) {
        System.out.println("Please enter email");
        String email = console.readLine();
        System.out.println("Please enter password");
        String password = String.valueOf(console.readPassword());
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

    public static int signup(Console console) {
        System.out.println("Please enter email");
        String email = console.readLine();
        System.out.println("Please enter username");
        String username = console.readLine();
        System.out.println("Please enter password");
        String password = String.valueOf(console.readPassword());
        LoginAdapter signup = new LoginAdapter(new Signup());
        signup.setName(username);
        signup.setType(userType);
        signup.login(email, password);
        userInterface = signup.returnUser();
        if(userInterface.getClass().getSimpleName().equals("Staff")){
            return UI.STAFF_MENU;
        }
        else{
            return UI.MENU_STATE;
        }
    }
    

    public static UserInterface getUser(){
        return userInterface;
    }

    public static void setUser(UserInterface loggedInUser){
        userInterface = loggedInUser;
    }

    public static void setType(String type){
        userType = type;
    }
}
