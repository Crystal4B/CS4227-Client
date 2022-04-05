import requestsystem.RequestDispatcher;
import requestsystem.commands.users.LoginUserCommand;
import requestsystem.commands.users.RegisterUserCommand;
import requestsystem.interceptors.LoginInterceptor;
import requestsystem.interceptors.TFAInterceptor;
import userinterface.UI;

public class App
{
	/**
	 * Main Java function. This begins UI state machine and registers interceptors.
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