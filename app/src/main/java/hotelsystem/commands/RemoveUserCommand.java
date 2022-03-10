package hotelsystem.commands;

import java.util.Map;

import hotelsystem.user.User;
import hotelsystem.user.Customer;
import hotelsystem.user.Staff;

/**
 * Command for removing a user from the system
 * @author Marcin Sęk
 * @apiNote Response type of User
 */
public class RemoveUserCommand extends CommandTemplate<User>
{
	private static final String MUTATION_NAME = "removeUser";
	private static final String UNDO_MUTATION_NAME = "createUser";

	private User user;

	/**
	 * Simple constructor for command
	 * @param user being removed from the system
	 */
	public RemoveUserCommand(User user)
	{
		this.user = user;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", UNDO_MUTATION_NAME, user.getClass().getSimpleName(), user.getEmail(), user.getUserName(), user.getPassword());
		}
		return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type email username password}}\"}", MUTATION_NAME, user.getId());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void parseResponse(Map<String, Object> response)
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

		Map<String, String> userData = (Map<String, String>) response.get(mutation);
		String id = userData.get("id");
		String type = userData.get("type");
		String email = userData.get("email");
		String username = userData.get("username");
		String password = userData.get("password");
	
		switch(type)
		{
		case "Customer":
			responseObject = new Customer(username, password, email);
			break;
		case "Staff":
			responseObject = new Staff(username, password, email);
		}
		responseObject.setId(Integer.parseInt(id));
	
		// Make copy for undo command
		this.user = responseObject;
	}
}
