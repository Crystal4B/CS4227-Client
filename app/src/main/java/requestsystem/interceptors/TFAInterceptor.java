package requestsystem.interceptors;

import email.Email;

import java.io.Console;
import java.util.Map;
import java.util.Random;

/**
 * @author Eoin McDonough
 * Payment class to handle payment states
 */
public class TFAInterceptor implements InterceptorInterface{
	public void preHandle(String command){
	}

	public void postHandle(Map<?, ?> response, String command){
		Map<?, ?> userData = (Map<?, ?>) response.get(command);

		if (userData == null)
		{
			return;
		}
		if ("Staff".equals(userData.get("type"))) {
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