package requestsystem.commands.users;

import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.UserInterface;
import requestsystem.commands.CommandTemplate;

import java.util.Map;

/**
 * Command for adding a new user into the system
 * @author Marcin Sęk
 * @apiNote Response type of User
 */
public class RegisterUserCommand extends CommandTemplate<UserInterface>
{
	public static final String MUTATION_NAME = "createUser";
	private static final String UNDO_MUTATION_NAME = "removeUser";

	private UserInterface userInterface;

	/**
	 * Simple constructor for command
	 * @param userInterface being registered with the system
	 */
	public RegisterUserCommand(UserInterface userInterface)
	{
		this.userInterface = userInterface;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type email username password}}\"}", UNDO_MUTATION_NAME, userInterface.getId());
		}
		return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", MUTATION_NAME, userInterface.getClass().getSimpleName(), userInterface.getEmail(), userInterface.getUserName(), userInterface.getPassword());
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
			responseObject = UserFactory.createCustomer(username, password, email);
			break;
		case "Staff":
			responseObject = UserFactory.createStaff(username, password, email);
			break;
		}
		responseObject.setId(Integer.parseInt(id));
	
		// Make copy for undo command
		this.userInterface = responseObject;
	}
}
