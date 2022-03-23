package requestsystem.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hotelsystem.room.Room;
import hotelsystem.room.Standard;

public class GetAllRoomsCommand extends CommandTemplate<Map<String, List<Room>>>
{
	private static final String QUERY_NAME = "allRooms";

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
		responseObject = new TreeMap<>();
		for (int i = 0; i < roomsList.size(); i++)
		{
			Map<?, ?> roomMap = (Map<?, ?>) roomsList.get(i);

			String id = (String) roomMap.get("id");
			String type = (String) roomMap.get("type");
			int numberOfBeds = (int) roomMap.get("numberOfBeds");
			List<Room> rooms = responseObject.getOrDefault(type, new ArrayList<>());
			rooms.add(new Standard(type, Integer.parseInt(id), numberOfBeds));
			responseObject.put(type, rooms);
		}
	}
}
