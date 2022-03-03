package hotelsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import hotelsystem.ReservationSystem;
import hotelsystem.room.Deluxe;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;

/**
 * Command for getting all available room for specified dates
 * @author Marcin Sęk
 */
public class GetAvailableRoomsCommand implements Command
{
	private Timestamp arrivalDate;
	private Timestamp departureDate;
	private ArrayList<Room> rooms;

	public GetAvailableRoomsCommand(Timestamp arrivalDate, Timestamp departureDate)
	{
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	@Override
	public void execute()
	{
		String message = String.format("{\"query\":\"query{availableRoomsByDates(arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\"){id type name numberOfBeds}}\"}", arrivalDate, departureDate);

		Map<String, Object> response = ReservationSystem.sendRequest(message);
		if (response.containsKey("availableRoomsByDates"))
		{
			ArrayList<Map<String,Object>> roomsData = (ArrayList<Map<String, Object>>) response.get("availableRoomsByDates");
			rooms = new ArrayList<>();
			for (Map<String,Object> room : roomsData)
			{
				String id = (String) room.get("id");
				String type = (String) room.get("type");
				String name = (String) room.get("name");
				int numberOfBeds = (int) room.get("numberOfBeds");
				switch(type)
				{
				case "Standard":
					rooms.add(new Standard(name, Integer.parseInt(id), numberOfBeds));
					break;
				case "Deluxe":
					rooms.add(new Deluxe(name, Integer.parseInt(id), numberOfBeds));
					break;
				case "VIP":
					rooms.add(new VIP(name, Integer.parseInt(id), numberOfBeds));
				}
			}
		}
	}

	@Override
	public void undo()
	{
		// Undo does not apply to this type of command
	}

	public Object getResponse()
	{
		return this.rooms;
	}
}
