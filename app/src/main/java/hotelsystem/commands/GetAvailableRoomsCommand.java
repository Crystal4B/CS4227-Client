package hotelsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Room;
import hotelsystem.room.Standard;

/**
 * Command for getting all available room for specified dates
 * @author Marcin Sęk
 * @apiNote Response type of ArrayList[Room]
 */
public class GetAvailableRoomsCommand extends CommandTemplate<ArrayList<Room>>
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
		System.out.println(String.format("{\"query\":\"query{%s(checkIn: \\\"%s\\\" checkOut: \\\"%s\\\"){id type numberOfBeds}}\"}", QUERY_NAME, checkIn, checkOut));

		// Undo doesn't apply to requests of type query
		return String.format("{\"query\":\"query{%s(checkIn: \\\"%s\\\" checkOut: \\\"%s\\\"){id type numberOfBeds}}\"}", QUERY_NAME, checkIn, checkOut);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			ArrayList<Map<String,Object>> roomsData = (ArrayList<Map<String, Object>>) response.get(QUERY_NAME);
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
