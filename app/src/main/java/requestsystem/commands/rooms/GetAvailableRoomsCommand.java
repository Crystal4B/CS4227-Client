package requestsystem.commands.rooms;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hotelsystem.roomFactory.RoomInterface;
import requestsystem.commands.CommandTemplate;
import hotelsystem.roomFactory.RoomFactory;

/**
 * Command for getting all available rooms for specified dates
 * @author Marcin Sęk
 * @apiNote Response type of Map[String, List[Room]]
 */
public class GetAvailableRoomsCommand extends CommandTemplate<Map<String, List<RoomInterface>>>
{
	public static final String QUERY_NAME = "availableRoomsByDates";

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
		for (Object o : roomsList) {
			Map<?, ?> roomMap = (Map<?, ?>) o;

			String type = (String) roomMap.get("type");
			int id = Integer.parseInt((String) roomMap.get("id"));
			int numberOfBeds = (int) roomMap.get("numberOfBeds");
			List<RoomInterface> rooms = responseObject.getOrDefault(type, new ArrayList<>());
			switch (type) {
				case "Standard" -> rooms.add(RoomFactory.createStandard(id, numberOfBeds));
				case "Deluxe" -> rooms.add(RoomFactory.createDeluxe(id, numberOfBeds));
				case "VIP" -> rooms.add(RoomFactory.createVIP(id, numberOfBeds));
				default -> System.out.println("Unknown type of Room!");
			}

			responseObject.put(type, rooms);
		}
	}
}
