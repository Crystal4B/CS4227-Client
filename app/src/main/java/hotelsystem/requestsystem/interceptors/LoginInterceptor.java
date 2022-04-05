package hotelsystem.requestsystem.interceptors;

import java.io.Console;
import java.util.Map;
import java.util.Random;

import hotelsystem.email.Email;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.users.ChangeUserPasswordCommand;

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
			System.out.println("Please enter the code sent to " + email);
			int number = Integer.parseInt(console.readLine());
			if(number == authKey) {
				System.out.println("Signed in");
			}
		}

		
	}

	public void afterCompletion(){

	}
}