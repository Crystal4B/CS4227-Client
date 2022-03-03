package hotelsystem.commands;

import hotelsystem.user.Customer;
import hotelsystem.user.Person;
import hotelsystem.user.Staff;

import java.util.Map;

import hotelsystem.ReservationSystem;

/**
 * Command for logging into the system
 * @author Marcin Sęk
 */
public class LoginUserCommand implements Command
{
	private Person user;

	public LoginUserCommand(Person user)
	{
		this.user = user;
	}

	@Override
	public void execute()
	{
		String message = String.format("{\"query\":\"query{loginUser(input:{email: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", user.getEmail(), user.getPassword());
		
		Map<String, Object> response = ReservationSystem.sendRequest(message);
		if (response.containsKey("loginUser"))
		{
			Map<String, String> userData = (Map<String, String>) response.get("loginUser");
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

	@Override
	public void undo()
	{
		// Signout
	}

	@Override
	public Object getResponse()
	{
		return this.user;
	}
	
}
