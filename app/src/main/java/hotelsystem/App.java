package hotelsystem;

import hotelsystem.requestsystem.RequestDispatcher;
import hotelsystem.requestsystem.commands.users.LoginUserCommand;
import hotelsystem.requestsystem.commands.users.RegisterUserCommand;
import hotelsystem.requestsystem.interceptors.LoginInterceptor;
import hotelsystem.requestsystem.interceptors.TFAInterceptor;
import hotelsystem.userinterface.UI;

public class App
{
	/**
	 * Main Java function. Runs UI state machine.
	 * @param args List of arguments as strings.
	 */
	public static void main(String[] args)
	{
		// Register interceptors
		registerInterceptors();

		// Run terminal-based user interface
		UI ui = new UI();
		ui.run();
	}

	/**
	 * Registers interceptors used in application.
	 */
	private static void registerInterceptors(){
		RequestDispatcher.register(LoginUserCommand.QUERY_NAME, new LoginInterceptor());
		RequestDispatcher.register(RegisterUserCommand.MUTATION_NAME, new TFAInterceptor());
	}
}