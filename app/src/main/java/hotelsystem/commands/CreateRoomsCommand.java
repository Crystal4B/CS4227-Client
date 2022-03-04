package hotelsystem.commands;

import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Deluxe;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;

/**
 * Command for adding new rooms into the system
 * @author Marcin Sęk
 * @apiNote Response of type ArrayList[Room]
 */
public class CreateRoomsCommand extends CommandTemplate<ArrayList<Room>>
{
	private static final String MUTATION_NAME = "createRooms";
	private static final String UNDO_MUTATION_NAME = "removeRooms";

	private ArrayList<Room> rooms;

	/**
	 * Simple constructor for command
	 * @param rooms ArrayList of rooms being added to the system
	 */
	public CreateRoomsCommand(ArrayList<Room> rooms)
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
				message += String.format("{id: \\\"%s\\\"}", room.getRoomNumber());
			}
			else
			{
				message += String.format("{type: \\\"%s\\\" name: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}", room.getClass().getSimpleName(), room.getRoomName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());
			}

			if (i < rooms.size() - 1)
			{
				message += ",";
			}
		}
		message += "]){ id type name perks numberOfBeds rate }}\"}";

		return message;
	}

	@Override
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
			String name = (String) room.get("name");
			int numberOfBeds = (int) room.get("numberOfBeds");
			switch(type)
			{
			case "Standard":
				responseObject.add(new Standard(name, Integer.parseInt(id), numberOfBeds));
				break;
			case "Deluxe":
				responseObject.add(new Deluxe(name, Integer.parseInt(id), numberOfBeds));
				break;
			case "VIP":
				responseObject.add(new VIP(name, Integer.parseInt(id), numberOfBeds));
			}
		}
		
		// Make a copy for undo
		this.rooms = responseObject;
	}
}