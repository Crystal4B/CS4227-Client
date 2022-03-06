package hotelsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import hotelsystem.room.Standard;
import order.Order;
import order.OrderBuilder;

/**
 * A Cancel Reservation Command for canceling an exiting reservation in the system
 * @author Marcin Sęk
 * @apiNote Response of type Order
 */
public class CancelReservationCommand extends CommandTemplate<Order>
{
	private static final String MUTATION_NAME = "removeReservation";
	private static final String UNDO_MUTATION_NAME = "createReservation";

	private Order orderCancelation;

	/**
	 * Cancel Reservation Command constructor
	 * @param orderCancelation the order being cancelled
	 */
	public CancelReservationCommand(Order orderCancelation)
	{
		this.orderCancelation = orderCancelation;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\"}){id arrivalDate departureDate rooms{id type name perks numberOfBeds rate}}}\"}", UNDO_MUTATION_NAME, orderCancelation.getStartDate(), orderCancelation.getEndDate());
		}
		return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id arrivalDate departureDate rooms{id type name perks numberOfBeds rate}}}\"}", MUTATION_NAME, orderCancelation.getOrderID());
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

		// Make copy for undo
		this.orderCancelation = responseObject;
	}
}
