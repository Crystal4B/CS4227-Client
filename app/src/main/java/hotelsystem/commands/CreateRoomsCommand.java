package hotelsystem.commands;

import java.util.ArrayList;

import hotelsystem.ReservationSystem;
import hotelsystem.room.Room;

/**
 * Command for adding new rooms into the system
 * @author Marcin Sęk
 */
public class CreateRoomsCommand implements Command
{
	private ArrayList<Room> rooms;

	public CreateRoomsCommand(ArrayList<Room> rooms)
	{
		this.rooms = rooms;
	}

	@Override
	public void execute()
	{
		String message = "{\"query\":\"mutation { createRooms(input: [";
		for (int i = 0; i < rooms.size(); i++)
		{
			Room room = rooms.get(i);
			message += String.format("{type: \\\"%s\\\" name: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}", room.getClass().getSimpleName(), room.getRoomName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());

			if (i < rooms.size() - 1)
			{
				message += ",";
			}
		}
		message += "]){ id name perks numberOfBeds rate }}\"}";

		ReservationSystem.sendRequest(message);
	}

	@Override
	public void undo()
	{
		// TODO: Formulate a message for removing of rooms
	}

	@Override
	public Object getResponse()
	{
		return null;
	}

}