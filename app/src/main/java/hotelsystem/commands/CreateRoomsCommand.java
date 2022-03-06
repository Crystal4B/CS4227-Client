package hotelsystem.commands;

import java.util.ArrayList;

import hotelsystem.ReservationSystem;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;

public class CreateRoomsCommand implements Command
{
	private ArrayList<Standard> rooms;

	public CreateRoomsCommand(ArrayList<Standard> rooms)
	{
		this.rooms = rooms;
	}

	@Override
	public void execute()
	{
		String message = "{\"query\":\"mutation { createRooms(input: [";
		for (int i = 0; i < rooms.size(); i++)
		{
			Standard room = rooms.get(i);
			message += String.format("{name: \\\"%s\\\" perks: \\\"%s\\\" numberOfBeds: %d rate: %d}", room.getRoomName(), room.getPerks(), room.getNumberBeds(), (int) room.getPrice());

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

}