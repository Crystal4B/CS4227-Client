package userinterface;

import java.io.Console;

public class UI {

    private Console console = System.console();

    public final static int EXIT = -1;
    public final static int LOGIN_STATE = 0;
    public final static int MENU_STATE = 1;
    public final static int RESERVATION_STATE = 2;

    public void run() {
        int state = 0;
        while(true){
            switch (state) {
                case EXIT:    
                    System.exit(0);
                case LOGIN_STATE:
                    state = LoginUI.run(console);
                    break;
                case MENU_STATE:
                    state = MenuUI.run(console);
                    break;
                case RESERVATION_STATE:
                    state = ReservationUI.run(console);
                    break;
            }
        }
    }
}
