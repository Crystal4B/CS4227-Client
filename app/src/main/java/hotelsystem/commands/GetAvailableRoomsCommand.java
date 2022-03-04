package hotelsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Deluxe;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;

/**
 * Command for getting all available room for specified dates
 * @author Marcin Sęk
 */
public class GetAvailableRoomsCommand extends CommandTemplate<ArrayList<Room>>
{
	private Timestamp arrivalDate;
	private Timestamp departureDate;

	public GetAvailableRoomsCommand(Timestamp arrivalDate, Timestamp departureDate)
	{
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo doesn't apply to requests of type query
		return String.format("{\"query\":\"query{availableRoomsByDates(arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\"){id type name numberOfBeds}}\"}", arrivalDate, departureDate);
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
