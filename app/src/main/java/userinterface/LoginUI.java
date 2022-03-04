package userinterface;

import java.io.Console;

import hotelsystem.user.Person;
import login.Login;
import login.LoginAdapter;
import login.Signup;

public class LoginUI {
    
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
                login(console);
                return UI.MENU_STATE;
            case 2: 
                // signup(console);
                return UI.MENU_STATE;
            case 3:  
                return UI.EXIT;
            default:
                return UI.LOGIN_STATE;
        }
    }

    public static void login(Console console) {
        System.out.println("Please enter email");
        String email = console.readLine();
        System.out.println("Please enter password");
        String password = String.valueOf(console.readPassword());
        Login login = new Login();
        login.login(email, password);
        login.returnUser();
    }

    public static void signup(Console console) {
        System.out.println("Please enter email");
        String email = console.readLine();
        System.out.println("Please enter username");
        String username = console.readLine();
        System.out.println("Please enter password");
        String password = String.valueOf(console.readPassword());
        LoginAdapter signup = new LoginAdapter(new Signup());
        signup.setName(username);
        signup.login(email, password);
        Person user = signup.returnUser();
        System.out.println("New User Created: Email: " + user.getEmail() + " , Username: " + user.getUserName()  + " , Password: " + user.getPassword());
    }
}
