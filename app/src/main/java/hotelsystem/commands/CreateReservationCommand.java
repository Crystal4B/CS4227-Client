package hotelsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Standard;
import order.Order;
import order.OrderBuilder;

/**
 * A Create Reservation Command for creating a new reservation in the system
 * @author Marcin Sęk
 * @apiNote Response of type Order
 */
public class CreateReservationCommand extends CommandTemplate<Order>
{
	private static final String MUTATION_NAME = "createReservation";
	private static final String UNDO_MUTATION_NAME = "removeReservation";

	private Order reservationOrder;

	/**
	 * Simple constructor for command
	 * @param reservationOrder the order being placed in the system
	 */
	public CreateReservationCommand(Order reservationOrder)
	{
		this.reservationOrder = reservationOrder;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id arrivalDate departureDate numberOfOccupants rooms{id type name perks numberOfBeds rate}}}\"}", UNDO_MUTATION_NAME, reservationOrder.getOrderID());
		}


		ArrayList<Standard> rooms = reservationOrder.getRooms();
		String orderRooms = "";
		for (int i = 0; i < rooms.size(); i++)
		{
			Standard room = rooms.get(i);
			orderRooms += String.format("{id: \\\"%d\\\"}", room.getRoomNumber());
			if (i < rooms.size() - 1)
			{
				orderRooms += ",";
			}
		}

		return String.format("{\"query\":\"mutation{%s(input:{arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\" rooms: [%s]}){id arrivalDate departureDate rooms{id type name numberOfBeds}}}\"}", MUTATION_NAME, reservationOrder.getStartDate(), reservationOrder.getEndDate(), orderRooms);
	}

	@Override
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

		Map<String, Object> reservationData = (Map<String, Object>) response.get(mutation);
		String reservationId = (String) reservationData.get("id");
		Timestamp arrivalDate = Timestamp.valueOf((String) reservationData.get("arrivalDate"));
		Timestamp departureDate = Timestamp.valueOf((String) reservationData.get("departureDate"));
		ArrayList<Map<String, Object>> roomsMap = (ArrayList<Map<String, Object>>) reservationData.get("rooms");

		OrderBuilder builder = new OrderBuilder();
		builder.setOrderID(reservationId);
		builder.setStartDate(arrivalDate);
		builder.setEndDate(departureDate);

		for (Map<String, Object> map : roomsMap)
		{
			String roomId = (String) map.get("id");
			String type = (String) map.get("type");
			String name = (String) map.get("name");
			int numberOfBeds = (int) map.get("numberOfBeds");

			switch(type)
			{
			case "Standard":
				builder.addRoom(new Standard(name, Integer.parseInt(roomId), numberOfBeds));
			}
		}

		responseObject = builder.getOrder();

		// Make copy for undo
		this.reservationOrder = responseObject;
	}
}
