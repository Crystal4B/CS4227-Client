package hotelsystem;

import java.util.Scanner;

public class UI {
    public void run(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n####################################################");
        System.out.println("#     Welcome to the Hotel Reservation System      #");
        System.out.println("####################################################\n");
        System.out.println("Please select one of the following options:");
        System.out.println("1. \t Reserve Room(s)");
        System.out.println("2. \t Cancel Booking");
        System.out.println("3. \t View Booking Information");
        System.out.println("4. \t Exit");
        System.out.println("Enter option here:");
        int option = scanner.nextInt();
        processOption(option);
    }

    public void processOption(int option){
        switch (option) {
            case 1:  System.out.println("Reserve");
                     break;
            case 2:  System.out.println("Cancel");
                     break;
            case 3:  System.out.println("View");
                     break;
            case 4:  System.exit(0);
                     break;
            default: run();
                     break;
        }
    }
}
