package requestsystem.interceptors;

import org.checkerframework.checker.units.qual.C;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.users.ChangeUserPasswordCommand;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Jakub Pažej
 * Interface for reservation System handling HttpClient requests and responses
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
			Scanner userInput = new Scanner(System.in);
			String input = userInput.nextLine();
			System.out.println("password has been changed.");
			CommandInvoker invoker = new CommandInvoker();
			invoker.setCommand(new ChangeUserPasswordCommand(Integer.parseInt(id),input));
			invoker.execute();
		}
	}

	public void afterCompletion(){

	}
}