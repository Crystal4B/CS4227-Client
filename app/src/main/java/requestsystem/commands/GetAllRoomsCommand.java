package requestsystem.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.room.Standard;

public class GetAllRoomsCommand extends CommandTemplate<List<Standard>>
{
	private static final String QUERY_NAME = "allRooms";

	@Override
	public String createMessage(boolean undo)
	{
		// Undo doesn't apply to requests of type query
		return String.format("{\"query\":\"query{%s{id type numberOfBeds}}\"}", QUERY_NAME);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			List<Map<String,Object>> roomsData = (ArrayList<Map<String, Object>>) response.get(QUERY_NAME);
			responseObject = new ArrayList<>();
			for (Map<String,Object> room : roomsData)
			{
				String id = (String) room.get("id");
				String type = (String) room.get("type");
				int numberOfBeds = (int) room.get("numberOfBeds");
				switch(type)
				{
				case "Standard":
					responseObject.add(new Standard(type, Integer.parseInt(id), numberOfBeds));
					break;
				}
			}
		}
	}
}
