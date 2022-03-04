package hotelsystem.commands;

import hotelsystem.user.Customer;
import hotelsystem.user.Person;
import hotelsystem.user.Staff;

import java.util.Map;

import hotelsystem.ReservationSystem;

/**
 * Command for adding a new user into the system
 * @author Marcin Sęk
 */
public class RegisterUserCommand extends CommandTemplate<Object> // TODO: ask Jakub to create Reservation type
{
	private Person user;

	public RegisterUserCommand(Person user)
	{
		this.user = user;
	}

	@Override
	public void execute()
	{
		String message = String.format("{\"query\":\"mutation{createUser(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", user.getClass().getSimpleName(), user.getEmail(), user.getUserName(), user.getPassword());
		
		Map<String, Object> response = ReservationSystem.sendRequest(message);

	}

	@Override
	public void undo()
	{
		//TODO: Formulate request to remove user
	}

	@Override
	public Object getResponse()
	{
		return this.user;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			// TODO: Formulate request to remove user
		}
		return String.format("{\"query\":\"mutation{createUser(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", user.getClass().getSimpleName(), user.getEmail(), user.getUserName(), user.getPassword());
	}

	@Override
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey("createUser"))
		{
			Map<String, String> userData = (Map<String, String>) response.get("createUser");
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
