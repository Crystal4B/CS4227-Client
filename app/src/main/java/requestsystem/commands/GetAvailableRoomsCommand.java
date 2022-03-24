package requestsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hotelsystem.room.Room;
import hotelsystem.room.Standard;

/**
 * Command for getting all available room for specified dates
 * @author Marcin Sęk
 * @apiNote Response type of Map[Type, List[Room]]
 */
public class GetAvailableRoomsCommand extends CommandTemplate<Map<String, List<Room>>>
{
	private static final String QUERY_NAME = "availableRoomsByDates";

	private Timestamp checkIn;
	private Timestamp checkOut;

	/**
	 * Simple constructor for command
	 * @param checkIn desired date for check-in
	 * @param checkOut desired date for check-out
	 */
	public GetAvailableRoomsCommand(Timestamp checkIn, Timestamp checkOut)
	{
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo doesn't apply to requests of type query
		return String.format("{\"query\":\"query{%s(checkIn: \\\"%s\\\" checkOut: \\\"%s\\\"){id type numberOfBeds}}\"}", QUERY_NAME, checkIn, checkOut);
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
