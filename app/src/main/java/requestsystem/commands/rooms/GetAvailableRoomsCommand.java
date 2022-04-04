package requestsystem.commands.rooms;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.roomFactory.RoomInterface;
import requestsystem.commands.CommandTemplate;
import hotelsystem.roomFactory.RoomFactory;

/**
 * Command for getting all available room for specified dates
 * @author Marcin Sęk
 * @apiNote Response type of List[Room]
 */
public class GetAvailableRoomsCommand extends CommandTemplate<List<RoomInterface>>
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
		responseObject = new ArrayList<>();

		List<?> roomsList = (List<?>) response.get(QUERY_NAME);
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
			};
		}
	}
}
