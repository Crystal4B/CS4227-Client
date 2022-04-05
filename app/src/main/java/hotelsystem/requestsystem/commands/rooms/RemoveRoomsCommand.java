package hotelsystem.requestsystem.commands.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.roomfactory.Room;
import hotelsystem.roomfactory.RoomFactory;

/**
 * Command for removing rooms from the system
 * @author Marcin Sęk
 * @apiNote Response type of ArrayList[Standard]
 */

 /**
     * Default Constructor for command
     */
public class RemoveRoomsCommand extends CommandTemplate<List<Room>>
{
	public static final String MUTATION_NAME = "removeRooms";
	public static final String UNDO_MUTATION_NAME = "createRooms";

	private List<Room> rooms;

	/**
	 * Simple constructor for command
	 * @param rooms ArrayList of rooms being removed from the system
	 */
	public RemoveRoomsCommand(List<Room> rooms)
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

		StringBuilder message = Optional.ofNullable(String.format("{\"query\":\"mutation{%s(input:[", mutation)).map(StringBuilder::new).orElse(null);
		for (int i = 0; i < rooms.size(); i++)
		{
			Room room = rooms.get(i);
			if (undo)
			{
				message = (message == null ? new StringBuilder("null") : message).append(String.format("{type: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}", room.getClass().getSimpleName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice()));
			}
			else
			{
				message = (message == null ? new StringBuilder("null") : message).append(String.format("{id: \\\"%s\\\"}", room.getRoomNumber()));
			}

			if (i < rooms.size() - 1)
			{
				message.append(",");
			}
		}
		message = (message == null ? new StringBuilder("null") : message).append("]){id type perks numberOfBeds rate}}\"}");

		return message.toString();
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
		for (Object o : roomsList) {
			Map<?, ?> roomMap = (Map<?, ?>) o;

			String type = (String) roomMap.get("type");
			int id = Integer.parseInt((String) roomMap.get("id"));
			int numberOfBeds = (int) roomMap.get("numberOfBeds");
			switch (type) {
				case "Standard" -> responseObject.add(RoomFactory.createStandard(id, numberOfBeds));
				case "Deluxe" -> responseObject.add(RoomFactory.createDeluxe(id, numberOfBeds));
				case "VIP" -> responseObject.add(RoomFactory.createVIP(id, numberOfBeds));
				default -> System.out.println("Unknown type of Room!");
			}
		}
		
		// Make a copy for undo
		this.rooms = responseObject;
	}
}
