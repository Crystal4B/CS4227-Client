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

	private Timestamp arrivalDate;
	private Timestamp departureDate;

	/**
	 * Simple constructor for command
	 * @param arrivalDate desired date for check-in
	 * @param departureDate desired date for check-out
	 */
	public GetAvailableRoomsCommand(Timestamp arrivalDate, Timestamp departureDate)
	{
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo doesn't apply to requests of type query
		return String.format("{\"query\":\"query{%s(arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\"){id type name numberOfBeds}}\"}", QUERY_NAME, arrivalDate, departureDate);
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
				String name = (String) room.get("name");
				int numberOfBeds = (int) room.get("numberOfBeds");
				switch(type)
				{
				case "Standard":
					responseObject.add(new Standard(name, Integer.parseInt(id), numberOfBeds));
					break;
				}
			}
		}
	}
}
