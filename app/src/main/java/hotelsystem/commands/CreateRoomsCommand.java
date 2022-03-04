package hotelsystem.commands;

import java.util.ArrayList;
import java.util.Map;

import hotelsystem.ReservationSystem;
import hotelsystem.room.Deluxe;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;

/**
 * Command for adding new rooms into the system
 * @author Marcin Sęk
 */
public class CreateRoomsCommand extends CommandTemplate<ArrayList<Room>>
{
	private ArrayList<Room> rooms;

	public CreateRoomsCommand(ArrayList<Room> rooms)
	{
		this.rooms = rooms;
	}

	@Override
	public String createMessage(boolean undo)
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

		return message;
	}

	@Override
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey("availableRoomsByDates"))
		{
			ArrayList<Map<String,Object>> roomsData = (ArrayList<Map<String, Object>>) response.get("availableRoomsByDates");
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
		}		
	}

}