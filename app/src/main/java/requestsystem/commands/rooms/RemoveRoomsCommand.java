package requestsystem.commands.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.roomFactory.Room;
import hotelsystem.roomFactory.RoomFactory;
import requestsystem.commands.CommandTemplate;

/**
 * Command for removing rooms from the system
 * @author Marcin Sęk
 * @apiNote Response type of ArrayList[Standard]
 */
public class RemoveRoomsCommand extends CommandTemplate<ArrayList<Room>>
{
	private static final String MUTATION_NAME = "removeRooms";
	private static final String UNDO_MUTATION_NAME = "createRooms";

	private ArrayList<Room> rooms;

	/**
	 * Simple constructor for command
	 * @param rooms ArrayList of rooms being removed from the system
	 */
	public RemoveRoomsCommand(ArrayList<Room> rooms)
	{
		this.rooms = rooms;
	}

	@Override
	public String createMessage(boolean undo)
	{
		String mutation = MUTATION_NAME;
		if (undo)
		{
			mutation = UNDO_MUTATION_NAME;
		}

		String message = String.format("{\"query\":\"mutation{%s(input:[", mutation);
		for (int i = 0; i < rooms.size(); i++)
		{
			Room room = rooms.get(i);
			if (undo)
			{
				message += String.format("{type: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}", room.getClass().getSimpleName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());
			}
			else
			{
				message += String.format("{id: \\\"%s\\\"}", room.getRoomNumber());
			}

			if (i < rooms.size() - 1)
			{
				message += ",";
			}
		}
		message += "]){id type perks numberOfBeds rate}}\"}";

		return message;
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		String mutation;
		if (response.containsKey(MUTATION_NAME))
		{
			mutation = MUTATION_NAME;
		}
		else if (response.containsKey(UNDO_MUTATION_NAME))
		{
			mutation = UNDO_MUTATION_NAME;
		}
		else
		{
			// Break if no acceptable response is returned
			return;
		}

		List<?> roomsList = (List<?>) response.get(mutation);
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
		
		// Make a copy for undo
		this.rooms = responseObject;
	}
}
