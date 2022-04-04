package requestsystem.interceptors;

import requestsystem.commands.CommandInvoker;
import requestsystem.commands.users.ChangeUserPasswordCommand;

import java.io.Console;
import java.util.Map;

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
		}
	}

	/**
	 * Function that runs after passing the message from server back to the user
	 */
	public void afterCompletion(){

	}
}