package requestsystem.commands.users;

import java.util.Map;

import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.UserInterface;
import requestsystem.commands.CommandTemplate;

public class GetAllStaffUsersCommand extends CommandTemplate<UserInterface>
{
	private static final String QUERY_NAME = "allStaffUsers";

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"query{%s(){id type email username}}\"}", QUERY_NAME);
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
	
			switch(type)
			{
			case "Customer":
				responseObject = UserFactory.createCustomer();
				break;
			case "Staff":
				responseObject = UserFactory.createStaff();
				break;
			}
			responseObject.setId(Integer.parseInt(id));
			responseObject.setEmail(email);
			responseObject.setUserName(username);
		}
	}	
}
