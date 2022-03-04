package hotelsystem.commands;

import java.util.Map;

import order.Order;

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
		return String.format("{\"query\":\"query{%s(id: %s){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", QUERY_NAME, id);
	}

	@Override
	public void parseResponse(Map<String, Object> response)
	{
		if (response.containsKey(QUERY_NAME))
		{
			Map<String, Object> reservationData = (Map<String, Object>) response.get(QUERY_NAME);
		}
	}
}
