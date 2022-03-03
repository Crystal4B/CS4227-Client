package userinterface;

import java.io.Console;

public class UI {

    private Console console = System.console();

    public void run() {
        int state = 0;
        while(true){
            switch (state) {
                case -1:    
                    System.exit(0);
                case 0:
                    state = LoginUI.run(console);
                    break;
                case 1:
                    state = MenuUI.run(console);
                    break;
                case 2:
                    state = ReservationUI.run(console);
                    break;
            }
        }
    }
}
