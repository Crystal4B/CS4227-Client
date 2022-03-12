package userinterface;

import java.io.Console;

public class UI {

    private Console console = System.console();

    public final static int EXIT = -1;
    public final static int LOGIN_STATE = 0;
    public final static int MENU_STATE = 1;
    public final static int RESERVATION_STATE = 2;
    public final static int PAYMENT_STATE = 3;
    public final static int BILLING_STATE = 4;

    public void run() {
        int state = 0;
        while(true){
            switch (state) {
                case EXIT:    
                    Runtime.getRuntime().exit(0);
                case LOGIN_STATE:
                    state = LoginUI.run(console);
                    break;
                case MENU_STATE:
                    state = MenuUI.run(console);
                    break;
                case RESERVATION_STATE:
                    state = ReservationUI.run(console);
                    break;
                case PAYMENT_STATE:
                    state = PaymentUI.run(console);
                    break;
                case BILLING_STATE:
                    state = BillingUI.run(console);
                    break;
            }
        }
    }
}
