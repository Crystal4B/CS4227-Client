package requestsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Standard;
import order.Order;
import order.OrderBuilder;

/**
 * A Select Reservation Command for selecting reservation data from the API
 * @author Marcin Sęk
 * @apiNote Response type of Order
 */
public class SelectReservationCommand extends CommandTemplate<Order>
{
	private static final String QUERY_NAME = "reservationById";

	private String id;

	/**
	 * Simple constructor for the command
	 * @param id of the reservation being selected
	 */
	public SelectReservationCommand(String id)
	{
		this.id = id;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"query{%s(id: %s){id reservationDate arrivalDate departureDate rooms{id type name perks numberOfBeds rate}}}\"}", QUERY_NAME, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			Map<String, Object> reservationData = (Map<String, Object>) response.get(QUERY_NAME);

			String reservationId = (String) reservationData.get("id");
			Timestamp arrivalDate = (Timestamp) reservationData.get("arrivalDate");
			Timestamp departureDate = (Timestamp) reservationData.get("departureDate");
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
					break;
				}
			}
	
			responseObject = builder.getOrder();
		}
	}
}
