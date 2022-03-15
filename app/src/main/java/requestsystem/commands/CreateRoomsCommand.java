package requestsystem.commands;

import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Standard;

/**
 * Command for adding new rooms into the system
 * @author Marcin Sęk
 * @apiNote Response of type ArrayList[Room]
 */
public class CreateRoomsCommand extends CommandTemplate<ArrayList<Standard>>
{
	private static final String MUTATION_NAME = "createRooms";
	private static final String UNDO_MUTATION_NAME = "removeRooms";

	private ArrayList<Standard> rooms;

	/**
	 * Simple constructor for command
	 * @param rooms ArrayList of rooms being added to the system
	 */
	public CreateRoomsCommand(ArrayList<Standard> rooms)
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
			Standard room = rooms.get(i);
			if (undo)
			{
				message += String.format("{id: \\\"%s\\\"}", room.getRoomNumber());
			}
			else
			{
				message += String.format("{type: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}", room.getClass().getSimpleName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());
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
	@SuppressWarnings("unchecked")
	public void parseResponse(Map<String, Object> response)
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

		ArrayList<Map<String,Object>> roomsData = (ArrayList<Map<String, Object>>) response.get(mutation);
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
		
		// Make a copy for undo
		this.rooms = responseObject;
	}
}