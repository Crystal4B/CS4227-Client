package hotelsystem.requestsystem.interceptors;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import hotelsystem.email.Email;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.users.ChangeUserPasswordCommand;
import hotelsystem.userinterface.StaffUI;

/**
 * @author Jakub Pažej
 * Interceptor that prompts the user to change from default password
 */
public class LoginInterceptor implements InterceptorInterface{

	public void preHandle(String command){

	}

	public void postHandle(Map<?, ?> response, String command){
		Map<?, ?> userData = (Map<?, ?>) response.get(command);
		if (userData == null)
		{
			return;
		}
		boolean defaultPassword = (boolean) userData.get("defaultPassword");
		String id = (String) userData.get("id");
		if (defaultPassword){
			System.out.println("You typed in the default password please change your password");
			Scanner console = new Scanner(System.in);
			String password = StaffUI.getPassword(console);
			System.out.println("password has been changed.");
			CommandInvoker invoker = new CommandInvoker();
			invoker.setCommand(new ChangeUserPasswordCommand(Integer.parseInt(id),password));
			invoker.execute();
			int authKey = new Random().nextInt(10000);
			String email = (String) userData.get("email");
			String emailToSend = "Thank you for signing up to Platinum Hotels \n Your confirmation code is: " + authKey;
			new Email(email, "Platinum Hotels Signup Confirmation", emailToSend);
			//TODO Remove next line before release
			System.out.println("\n\n\nAUTHKEY FOR DEVELOPMENT PURPOSES //// TO BE REMOVED   : " + authKey);
			System.out.println("Please enter the code sent to " + email);
			int number = Integer.parseInt(console.nextLine());
			if(number == authKey) {
				System.out.println("Signed in");
			}
		}

		
	}

	public void afterCompletion(){

	}
}