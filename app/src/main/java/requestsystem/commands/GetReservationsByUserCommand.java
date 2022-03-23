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
	public void parseResponse(Map<?, ?> response)
	{
		List<?> reservationList = (List<?>) response.get(QUERY_NAME);

		responseObject = new ArrayList<>();
		for (int i = 0; i < reservationList.size(); i++)
		{
			Map<?, ?> reservationMap = (Map<?,?>) reservationList.get(i);
					
			OrderBuilder builder = new OrderBuilder();

			builder.setOrderID((String) reservationMap.get("id"));
			builder.setStartDate(Timestamp.valueOf((String) reservationMap.get("checkIn")));
			builder.setEndDate(Timestamp.valueOf((String) reservationMap.get("checkOut")));

			ArrayList<Standard> rooms = new ArrayList<>();

			List<?> guestList = (List<?>) reservationMap.get("guests");
			for (int j = 0; j < guestList.size(); j++)
			{
				Map<?, ?> guestMap = (Map<?, ?>) guestList.get(j);
				Map<?, ?> roomMap = (Map<?, ?>) guestMap.get("room");

				int id = Integer.parseInt((String) roomMap.get("id"));
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
					String type = (String) roomMap.get("type");
					int numberOfBeds = (int) roomMap.get("numberOfBeds");
					standardRoom = new Standard(type, id, numberOfBeds);
					rooms.add(standardRoom);
				}

				int guestId = Integer.parseInt((String) guestMap.get("id"));
				String firstName = (String) guestMap.get("firstName");
				String lastName = (String) guestMap.get("lastName");

				standardRoom.addOccupant(new Guest(firstName, lastName, guestId));
			}

			builder.setRooms(rooms);
			responseObject.add(builder.getOrder());
		}
	}
}
