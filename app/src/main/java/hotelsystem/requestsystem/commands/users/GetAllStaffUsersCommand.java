package hotelsystem.requestsystem.commands.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.userfactory.UserFactory;
import hotelsystem.userfactory.UserInterface;

/**
 * Command for getting allStaffUsers of the hotel
 * @author Marcin Sęk
 * @apiNote Response type of List[UserInterface]
 */
public class GetAllStaffUsersCommand extends CommandTemplate<List<UserInterface>>
{
	public static final String QUERY_NAME = "allStaffUsers";

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"query{%s{id type email username}}\"}", QUERY_NAME);
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			List<?> userList = (List<?>) response.get(QUERY_NAME);
			if (userList == null)
			{
				return;
			}
			responseObject = new ArrayList<>();
			for (Object o : userList) {
				Map<?, ?> userData = (Map<?, ?>) o;

				String id = (String) userData.get("id");
				String type = (String) userData.get("type");
				String email = (String) userData.get("email");
				String username = (String) userData.get("username");

				UserInterface user;
				switch (type) {
					case "Customer":
						user = UserFactory.createCustomer();
						break;
					case "Staff":
						user = UserFactory.createStaff();
						break;
					default:
						continue;
				}

				user.setId(Integer.parseInt(id));
				user.setEmail(email);
				user.setUserName(username);
				responseObject.add(user);
			}
		}
	}	
}
