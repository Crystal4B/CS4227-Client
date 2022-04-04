package requestsystem.commands.reservations;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import hotelsystem.roomFactory.RoomFactory;
import order.Order;
import order.OrderBuilder;
import requestsystem.commands.CommandTemplate;

/**
 * A Select Reservation Command for selecting reservation data from the API
 * @author Marcin Sęk
 * @apiNote Response type of Order
 */
public class SelectReservationCommand extends CommandTemplate<Order>
{
	public static final String QUERY_NAME = "reservationById";

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
	public void parseResponse(Map<?, ?> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			Map<?, ?> reservationData = (Map<?, ?>) response.get(QUERY_NAME);

			String reservationId = (String) reservationData.get("id");
			Timestamp arrivalDate = (Timestamp) reservationData.get("arrivalDate");
			Timestamp departureDate = (Timestamp) reservationData.get("departureDate");
			List<?> roomsList = (List<?>) reservationData.get("rooms");
	
			OrderBuilder builder = new OrderBuilder();
			builder.setOrderID(reservationId);
			builder.setStartDate(arrivalDate);
			builder.setEndDate(departureDate);
	
			for (int i = 0; i < roomsList.size(); i++)
			{
				Map<?, ?> roomMap = (Map<?, ?>) roomsList.get(i);

				String type = (String) roomMap.get("type");
				int roomId = Integer.parseInt((String) roomMap.get("id"));
				int numberOfBeds = (int) roomMap.get("numberOfBeds");
				switch(type)
				{
				case "Standard":
					builder.addRoom(RoomFactory.createStandard(roomId, numberOfBeds));
					break;
				case "Deluxe":
					builder.addRoom(RoomFactory.createDeluxe(roomId, numberOfBeds));
					break;
				case "VIP":
					builder.addRoom(RoomFactory.createVIP(roomId, numberOfBeds));
				}
			}
	
			responseObject = builder.getOrder();
		}
	}
}
