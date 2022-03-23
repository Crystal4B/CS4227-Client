package requestsystem.commands;

import hotelsystem.user.User;
import hotelsystem.user.Customer;
import hotelsystem.user.Staff;

import java.util.Map;

/**
 * Command for adding a new user into the system
 * @author Marcin Sęk
 * @apiNote Response type of User
 */
public class RegisterUserCommand extends CommandTemplate<User>
{
	private static final String MUTATION_NAME = "createUser";
	private static final String UNDO_MUTATION_NAME = "removeUser";

	private User user;

	/**
	 * Simple constructor for command
	 * @param user being registered with the system
	 */
	public RegisterUserCommand(User user)
	{
		this.user = user;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type email username password}}\"}", UNDO_MUTATION_NAME, user.getId());
		}
		return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", MUTATION_NAME, user.getClass().getSimpleName(), user.getEmail(), user.getUserName(), user.getPassword());
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		String mutation;
		if (response.containsKey(MUTATION_NAME))
		{
			mutation = MUTATION_NAME;
		}
		else if (response.containsKey(UNDO_MUTATION_NAME))
		{
			mutation = UNDO_MUTATION_NAME;
		}
		else
		{
			return;
		}

		Map<?, ?> userData = (Map<?, ?>) response.get(mutation);
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
	
		// Make copy for undo command
		this.user = responseObject;
	}
}
