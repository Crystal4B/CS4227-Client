package userinterface;

import java.util.Scanner;
import login.Login;
import login.LoginAdapter;
import hotelsystem.user.Person;

import login.Signup;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);

    public void run() {
        // Run login funtion
        runLogin();

        // Run main menu
        runMenu();
        scanner.close();
    }

    public void runLogin() {
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Login");
        System.out.println("2. \t Sign-up");
        System.out.println("3. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:  System.out.println("Login");
                     login();
                     break;
            case 2:  System.out.println("Sign-Up");
                     signup();
                     break;
            case 3:  System.exit(0);
                     break;
            default: runLogin();
                     break;
        }
    }

    public void login() {
        System.out.println("Please enter email");
        String email = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        Login login = new Login();
        login.login(email, password);
        Person user = login.returnUser();
    }


  
    public void signup() {
        System.out.println("Please enter email");
        String email = scanner.next();
        System.out.println("Please enter username");
        String username = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        LoginAdapter signup = new LoginAdapter(new Signup());
        signup.setName(username);
        signup.login(email, password);
        Person user = signup.returnUser();
        System.out.println("New User Created: Email: " + user.getEmail() + " , Username: " + user.getUserName()  + " , Password: " + user.getPassword());
}
 

  
    public void runMenu(){
        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Reserve Room(s)");
        System.out.println("2. \t Cancel Booking");
        System.out.println("3. \t View Booking Information");
        System.out.println("4. \t Exit");
        System.out.println("\n####################################################\n");
        System.out.println("Enter option here:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:  System.out.println("Reserve");
                     break;
            case 2:  System.out.println("Cancel");
                     break;
            case 3:  System.out.println("View");
                     break;
            case 4:  System.exit(0);
                     break;
            default: runMenu();
                     break;
        }
    }
}
