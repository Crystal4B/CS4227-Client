package hotelsystem.commands;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import order.Order;

/**
 * A Cancel Reservation Command for canceling an exiting reservation in the system
 * @author Marcin Sęk
 */
public class CancelReservationCommand extends CommandTemplate<Object> // TODO: ask Jakub to create Reservation type
{
	private Order orderCancelation;

	/**
	 * Cancel Reservation Command constructor
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
			return ""; // TODO: Formulate a message for removing a reservation from the system	
		}
		return String.format("{\"query\":\"mutation { createReservation(input: { reservationDate: \\\"%s\\\" arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\" numberOfOccupants: 4}){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", Timestamp.valueOf(LocalDateTime.now()), orderCancelation.getStartDate(), orderCancelation.getEndDate(), orderCancelation.getNumberOfOccupants());

	}

	@Override
	public void parseResponse(Map<String, Object> response)
	{
		// TODO: parse response from both cancel reservation and create reservation
	}
}
