package hotelsystem.requestsystem.interceptors;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import hotelsystem.email.Email;

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
        System.out.println("Please enter the code sent to " + email);
    	Scanner console = new Scanner(System.in);
		int number;
		try {
            number = Integer.parseInt(console.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input: Please try again!");
            return false;
        }
		if(number == authKey) {
			System.out.println("Signed in");
		}
		console.close();
	}

	public void afterCompletion(){

	}
}