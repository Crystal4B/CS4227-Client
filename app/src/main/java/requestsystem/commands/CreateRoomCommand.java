package requestsystem.commands;

import java.util.Map;

import hotelsystem.roomFactory.Room;

public class CreateRoomCommand extends CommandTemplate<Room>
{
	private static final String MUTATION_NAME = "createRoom";
	private static final String UNDO_MUTATION_NAME = "roomRoom";

	private Room room;

	/**
	 * Simple constructor for command
	 * @param room being removed from the system
	 */
	public CreateRoomCommand(Room room)
	{
		this.room = room;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type perks numberOfBeds rate}}\"}", UNDO_MUTATION_NAME, room.getRoomNumber());
		}
		
		return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}){id type perks numberOfBeds rate}}\"}", MUTATION_NAME, room.getClass().getSimpleName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());
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

		Map<?, ?> roomsData = (Map<?, ?>) response.get(mutation);
		String id = (String) roomsData.get("id");
		String type = (String) roomsData.get("type");
		int numberOfBeds = (int) roomsData.get("numberOfBeds");
		switch(type)
		{
			case "Standard":
			responseObject = new Room(type, Integer.parseInt(id), numberOfBeds);
			break;
		}
		
		// Make a copy for undo
		this.room = responseObject;
	}
}
