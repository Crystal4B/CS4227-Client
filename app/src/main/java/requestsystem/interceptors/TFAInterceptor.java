package requestsystem.interceptors;

import org.checkerframework.checker.units.qual.C;

import email.Email;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.users.ChangeUserPasswordCommand;

import java.io.Console;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TFAInterceptor implements InterceptorInterface{
	public void preHandle(String command){

	}

	public void postHandle(Map<?, ?> response, String command){
		System.out.println("ENTERING TFA INTERCEPTOR");
		Map<?, ?> userData = (Map<?, ?>) response.get(command);
		if (userData == null)
		{
			System.out.println("hello");
			return;
		}
		int authKey = new Random().nextInt(10000);
		String email = (String) userData.get("email");
        String emailToSend = "Thank you for signing up to Platinum Hotels \n Your confirmation code is: " + authKey;
        new Email(email, "Platinum Hotels Signup Confirmation", emailToSend);
        //TODO Remove next line before release
		System.out.println("\n\n\nAUTHKEY FOR DEVELOPMENT PURPOSES //// TO BE REMOVED   : " + authKey);
        System.out.println("Please enter the code sent to " + email);
    	Console console = System.console();
		int number = Integer.parseInt(console.readLine());
		if(number == authKey) {
			System.out.println("Signed in");
		}
	}

	public void afterCompletion(){

	}
}