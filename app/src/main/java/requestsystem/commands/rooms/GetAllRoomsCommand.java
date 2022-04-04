package requestsystem.commands.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hotelsystem.roomFactory.RoomInterface;
import requestsystem.commands.CommandTemplate;
import hotelsystem.roomFactory.RoomFactory;

/**
 * A Get Rooms Command for fetching all the rooms in the hotel
 * @author Marcin Sęk
 * @apiNote Response of type Map[String, List[Room]]
 */
public class GetAllRoomsCommand extends CommandTemplate<List<RoomInterface>>
{
	public static final String QUERY_NAME = "allRooms";

	@Override
	public String createMessage(boolean undo)
	{
		// Undo doesn't apply to requests of type query
		return String.format("{\"query\":\"query{%s{id type numberOfBeds}}\"}", QUERY_NAME);
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		if (!response.containsKey(QUERY_NAME))
		{
			return;
		}

		List<?> roomsList = (List<?>) response.get(QUERY_NAME);
		responseObject = new ArrayList<>();
		for (int i = 0; i < roomsList.size(); i++)
		{
			Map<?, ?> roomMap = (Map<?, ?>) roomsList.get(i);

			String type = (String) roomMap.get("type");
			int id = Integer.parseInt((String) roomMap.get("id"));
			int numberOfBeds = (int) roomMap.get("numberOfBeds");
			switch(type)
			{
			case "Standard":
				responseObject.add(RoomFactory.createStandard(id, numberOfBeds));
				break;
			case "Deluxe":
				responseObject.add(RoomFactory.createDeluxe(id, numberOfBeds));
				break;
			case "VIP":
				responseObject.add(RoomFactory.createVIP(id, numberOfBeds));
			}
		}
	}
}
