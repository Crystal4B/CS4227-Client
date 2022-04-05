package hotelsystem.userinterface;

import java.util.Scanner;

public class UI {

    private Scanner console = new Scanner(System.in);

    public final static int EXIT = -1;
    public final static int LOGIN_STATE = 0;
    public final static int MENU_STATE = 1;
    public final static int RESERVATION_STATE = 2;
    public final static int BILLING_STATE = 3;
    public final static int STAFF_MENU = 4;

    /**
     * This the main state manager function which s/witches between different states. It is called in App.java file.
     */
    public void run() {
        int state = 0;
        while(true){
            switch (state) {
                case EXIT -> Runtime.getRuntime().exit(0);
                case LOGIN_STATE -> state = LoginUI.run(console);
                case MENU_STATE -> state = MenuUI.run(console);
                case RESERVATION_STATE -> state = ReservationUI.run(console);
                case BILLING_STATE -> state = BillingUI.run(console);
                case STAFF_MENU -> state = StaffUI.run(console);
                default -> System.out.println("Unknown state!");
            }
        }
    }
}
