package hotelsystem.requestsystem.commands.users;

import java.util.Map;

import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.userfactory.Customer;
import hotelsystem.userfactory.Staff;
import hotelsystem.userfactory.UserInterface;

public class ChangeUserPasswordCommand extends CommandTemplate<UserInterface>
{
	public static final String MUTATION_NAME = "changeUserPassword";

	private int id;
	private String password;

	/**
	 * Simple constructor for command
	 */
	public ChangeUserPasswordCommand(int id, String password)
	{
		this.id = id;
		this.password = password;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"mutation{%s(input:{id: %d password: \\\"%s\\\"}){id type email username password defaultPassword}}\"}", MUTATION_NAME, id, password);
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		if (response.containsKey(MUTATION_NAME))
		{
			Map<?, ?> userData = (Map<?, ?>) response.get(MUTATION_NAME);
			if (userData == null)
			{
				return;
			}

			String id = (String) userData.get("id");
			String type = (String) userData.get("type");
			String email = (String) userData.get("email");
			String username = (String) userData.get("username");
			String password = (String) userData.get("password");

			switch (type) {
				case "Customer" -> responseObject = new Customer(username, password, email);
				case "Staff" -> responseObject = new Staff(username, password, email);
				default -> System.out.println("Unknown user type!");
			}
			responseObject.setId(Integer.parseInt(id));
		}
	}
}
