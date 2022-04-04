import requestsystem.RequestDispatcher;
import requestsystem.commands.users.LoginUserCommand;
import requestsystem.commands.users.RegisterUserCommand;
import requestsystem.interceptors.LoginInterceptor;
import requestsystem.interceptors.TFAInterceptor;
import userinterface.UI;

public class App
{
	public static void main(String[] args)
	{
		registerInterceptors();
		// Run terminal-based user interface
		UI ui = new UI();
		ui.run();
	}

	private static void registerInterceptors(){
		RequestDispatcher.register(LoginUserCommand.QUERY_NAME, new LoginInterceptor());
		RequestDispatcher.register(RegisterUserCommand.MUTATION_NAME, new TFAInterceptor());
	}
}