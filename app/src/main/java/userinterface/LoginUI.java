package userinterface;

import java.io.Console;

import hotelsystem.user.User;
import login.Login;
import login.LoginAdapter;
import login.Signup;

public class LoginUI {

    public static String userType;
    
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
        int option = Integer.parseInt(console.readLine());
        switch (option) {
            case 1:
                return login(console);
            case 2: 
                return signup(console);
            case 3:  
                return UI.EXIT;
            default:
                return UI.LOGIN_STATE;
        }
    }

    public static int login(Console console) {
        System.out.println("Please enter email");
        String email = console.readLine();
        System.out.println("Please enter password");
        String password = String.valueOf(console.readPassword());
        Login login = new Login();
        if(login.login(email, password)) {
            User user = login.returnUser();
            if(user.getClass().getSimpleName().equals("Customer")){
                LoginUI.userType = "Customer";
                return UI.MENU_STATE;
            }
            else if(user.getClass().getSimpleName().equals("Staff")){
                LoginUI.userType = "Staff";
                return UI.STAFF_MENU;
            }
        }
        else{
            System.out.println("Invalid User \nPlease try logging in again \n \n");
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
        signup.login(email, password);
        User user = signup.returnUser();
        if(user.getClass().getSimpleName().equals("Customer")){
            return UI.MENU_STATE;
        }
        else if(user.getClass().getSimpleName().equals("Staff")){
            return UI.STAFF_MENU;
        }
        return UI.LOGIN_STATE;
    }
}
