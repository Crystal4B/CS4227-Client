package requestsystem.interceptors;

import requestsystem.commands.CommandInvoker;
import requestsystem.commands.users.ChangeUserPasswordCommand;

import java.io.Console;
import java.util.Map;
import java.util.Random;

import email.Email;

/**
 * @author Jakub Pažej
 * Interceptor that prompts the user to change from default password
 */

public class LoginInterceptor implements InterceptorInterface{

	/**
	 * Function that runs after user send message but before passing it to server
	 * @param command the command being sent to the server
	 */
	public void preHandle(String command){

	}

	/**
	 * Function that runs after server sends a response but before passing it to the user
	 * @param command the command sent to the server
	 * @param response the response from server
	 */
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
			Console console = System.console();
			String password = String.valueOf(console.readPassword());
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
			int number = Integer.parseInt(console.readLine());
			if(number == authKey) {
				System.out.println("Signed in");
			}
		}

		
	}

	/**
	 * Function that runs after passing the message from server back to the user
	 */
	public void afterCompletion(){

	}
}