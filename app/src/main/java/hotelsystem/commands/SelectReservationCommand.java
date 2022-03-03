package hotelsystem.commands;

import java.util.Map;

import hotelsystem.ReservationSystem;

/**
 * A Select Reservation Command for selecting reservation data from the API
 * @author Marcin Sęk
 */
public class SelectReservationCommand implements Command
{
	private String id;

	public SelectReservationCommand(String id)
	{
		this.id = id;
	}

	@Override
	public void execute()
	{
		String message = String.format("{\"query\":\"query{reservationById(id: %s){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", id);
		
		Map<String, Object> response = ReservationSystem.sendRequest(message);
		if (response.containsKey("reservationById"))
		{
			Map<String, Object> reservationData = (Map<String, Object>) response.get("reservationById");
		}
	}

	@Override
	public void undo()
	{
		// Undo does not apply to this command
	}

	@Override
	public Object getResponse()
	{
		return null;
	}
}
