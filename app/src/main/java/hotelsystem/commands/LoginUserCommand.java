package hotelsystem.commands;

import hotelsystem.user.Customer;
import hotelsystem.user.Person;
import hotelsystem.user.Staff;

import java.util.Map;

/**
 * Command for logging into the system
 * @author Marcin Sęk
 * @apiNote Response type of Person
 */
public class LoginUserCommand extends CommandTemplate<Person>
{
	private static final String QUERY_NAME = "loginUser";

	private Person user;

	/**
	 * Simple constructor for command
	 * @param user attempting to login
	 */
	public LoginUserCommand(Person user)
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
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			Map<String, String> userData = (Map<String, String>) response.get(QUERY_NAME);
			String id = userData.get("id");
			String type = userData.get("type");
			String email = userData.get("email");
			String username = userData.get("username");
			String password = userData.get("password");
	
			switch(type)
			{
			case "Customer":
				user = new Customer(username, password, email);
				break;
			case "Staff":
				user = new Staff(username, password, email);
			}
			user.setId(Integer.parseInt(id));
		}
	}
	
}
