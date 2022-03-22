package requestsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.room.Standard;
import hotelsystem.user.Guest;
import hotelsystem.user.User;
import order.Order;
import order.OrderBuilder;

public class GetReservationsByUserCommand extends CommandTemplate<List<Order>>
{
	private static final String QUERY_NAME = "reservationsByUser";

	private User user;

	public GetReservationsByUserCommand(User user)
	{
		this.user = user;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to query messages
		return "{\"query\":\"query{" + QUERY_NAME + "(input: {id: " + user.getId() + " type: \\\"" + user.getClass().getSimpleName() + "\\\" email: \\\"" + user.getEmail() + "\\\"}){id checkIn checkOut user{id type} guests{id firstName lastName room{id type numberOfBeds}}}}\"}";
	}

	@Override
	public void parseResponse(Map<String, Object> response)
	{
		List<Map<String, Object>> reservationsData = (List<Map<String, Object>>) response.get(QUERY_NAME);
		responseObject = new ArrayList<>();
		for (Map<String, Object> reservationData : reservationsData)
		{
			OrderBuilder builder = new OrderBuilder();
	
			builder.setOrderID((String)reservationData.get("id"));
			builder.setStartDate(Timestamp.valueOf((String)reservationData.get("checkIn")));
			builder.setEndDate(Timestamp.valueOf((String)reservationData.get("checkOut")));
	
			ArrayList<Standard> rooms = new ArrayList<>();
			
			List<Map<String, Object>> guestsMap = (List<Map<String, Object>>) reservationData.get("guests");
			for (Map<String, Object> map : guestsMap)
			{
				Map<String, Object> roomData = (Map<String,Object>) map.get("room");
				int id = Integer.parseInt((String) roomData.get("id"));

				Standard standardRoom = null;

				boolean found = false;
				for (Standard room : rooms)
				{
					if (room.getRoomNumber() == id)
					{
						standardRoom = room;
						found = true;
						break;
					}
				}

				if (!found)
				{
					String type = (String) roomData.get("type");
					int numberOfBeds = Integer.parseInt((String) roomData.get("numberOfBeds"));
					standardRoom = new Standard(type, id, numberOfBeds);
				}

				int guestId = Integer.parseInt((String) map.get("id"));
				String firstName = (String) map.get("firstName");
				String lastName = (String) map.get("lastName");

				standardRoom.addOccupant(new Guest(firstName, lastName, guestId));
			}

			builder.setRooms(rooms);
			responseObject.add(builder.getOrder());
		}
	}
}
