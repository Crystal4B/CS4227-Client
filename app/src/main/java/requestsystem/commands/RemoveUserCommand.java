package requestsystem.commands;

import java.util.Map;

import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.Staff;

/**
 * Command for removing a user from the system
 * @author Marcin Sęk
 * @apiNote Response type of User
 */
public class RemoveUserCommand extends CommandTemplate<UserFactory>
{
	private static final String MUTATION_NAME = "removeUser";
	private static final String UNDO_MUTATION_NAME = "createUser";

	private UserFactory userFactory;

	/**
	 * Simple constructor for command
	 * @param userFactory being removed from the system
	 */
	public RemoveUserCommand(UserFactory userFactory)
	{
		this.userFactory = userFactory;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", UNDO_MUTATION_NAME, userFactory.getClass().getSimpleName(), userFactory.getEmail(), userFactory.getUserName(), userFactory.getPassword());
		}
		return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type email username password}}\"}", MUTATION_NAME, userFactory.getId());
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
		this.userFactory = responseObject;
	}
}
