package requestsystem.commands;

import java.util.Map;

import hotelsystem.room.Standard;

public class RemoveRoomCommand extends CommandTemplate<Standard>
{
	private static final String MUTATION_NAME = "removeRoom";
	private static final String UNDO_MUTATION_NAME = "createRoom";

	private Standard room;

	/**
	 * Simple constructor for command
	 * @param rooms room being removed from the system
	 */
	public RemoveRoomCommand(Standard room)
	{
		this.room = room;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}){id type perks numberOfBeds rate}}\"}", UNDO_MUTATION_NAME, room.getClass().getSimpleName(), room.getRoomName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());
		}

		return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type perks numberOfBeds rate}}\"}", MUTATION_NAME, room.getRoomNumber());
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

		Map<String,Object> roomsData = (Map<String, Object>) response.get(mutation);
		String id = (String) roomsData.get("id");
		String type = (String) roomsData.get("type");
		int numberOfBeds = (int) roomsData.get("numberOfBeds");
		switch(type)
		{
			case "Standard":
			responseObject = new Standard(type, Integer.parseInt(id), numberOfBeds);
			break;
		}
		
		// Make a copy for undo
		this.room = responseObject;
	}
}
