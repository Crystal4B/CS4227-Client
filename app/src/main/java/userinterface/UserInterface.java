package userinterface;

import java.util.Scanner;

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
                     break;
            case 2:  System.out.println("Sign-Up");
                     break;
            case 3:  System.exit(0);
                     break;
            default: runLogin();
                     break;
        }
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
