package requestsystem.commands.users;

import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.Staff;
import hotelsystem.userFactory.UserInterface;
import requestsystem.commands.CommandTemplate;

import java.util.Map;

/**
 * Command for logging into the system
 * @author Marcin Sęk
 * @apiNote Response type of User
 */
public class LoginUserCommand extends CommandTemplate<UserInterface>
{
	private static final String QUERY_NAME = "loginUser";

	private UserInterface userInterface;

	/**
	 * Simple constructor for command
	 * @param userInterface attempting to login
	 */
	public LoginUserCommand(UserInterface userInterface)
	{
		this.userInterface = userInterface;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"query{%s(input:{email: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", QUERY_NAME, userInterface.getEmail(), userInterface.getPassword());
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			Map<?, ?> userData = (Map<?, ?>) response.get(QUERY_NAME);
			if (userData == null)
			{
				return;
			}

			String id = (String) userData.get("id");
			String type = (String) userData.get("type");
			String email = (String) userData.get("email");
			String username = (String) userData.get("username");
			String password = (String) userData.get("password");
	
			switch(type)
			{
			case "Customer":
				responseObject = new Customer(username, password, email);
				break;
			case "Staff":
				responseObject = new Staff(username, password, email);
				break;
			}
			responseObject.setId(Integer.parseInt(id));
		}
	}
	
}
