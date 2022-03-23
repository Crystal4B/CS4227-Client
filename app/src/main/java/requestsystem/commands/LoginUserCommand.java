package requestsystem.commands;

import hotelsystem.user.Customer;
import hotelsystem.user.Staff;
import hotelsystem.user.User;

import java.util.Map;

/**
 * Command for logging into the system
 * @author Marcin Sęk
 * @apiNote Response type of User
 */
public class LoginUserCommand extends CommandTemplate<User>
{
	private static final String QUERY_NAME = "loginUser";

	private User user;

	/**
	 * Simple constructor for command
	 * @param user attempting to login
	 */
	public LoginUserCommand(User user)
	{
		this.user = user;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"query{%s(input:{email: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", QUERY_NAME, user.getEmail(), user.getPassword());
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
