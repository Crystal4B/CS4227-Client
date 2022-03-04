package hotelsystem.commands;

import java.util.Map;

/**
 * A Select Reservation Command for selecting reservation data from the API
 * @author Marcin Sęk
 */
public class SelectReservationCommand extends CommandTemplate<Object> // TODO: ask Jakub to create Reservation type
{
	private String id;

	public SelectReservationCommand(String id)
	{
		this.id = id;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to requests of type query
		return String.format("{\"query\":\"query{reservationById(id: %s){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", id);
	}

	@Override
	public void parseResponse(Map response)
	{
		if (response.containsKey("reservationById"))
		{
			Map<String, Object> reservationData = (Map<String, Object>) response.get("reservationById");
			// TODO: finish parsing response
		}
	}
}
