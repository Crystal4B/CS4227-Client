package hotelsystem.commands;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import hotelsystem.ReservationSystem;
import order.Order;

/**
 * A Create Reservation Command for creating a new reservation in the system
 * @author Marcin Sęk
 */
public class CreateReservationCommand extends CommandTemplate<Object> // TODO: ask Jakub to create Reservation type
{
	private Order reservationOrder;

	/**
	 * Create Reservation Command constructor 
	 */
	public CreateReservationCommand(Order reservationOrder)
	{
		this.reservationOrder = reservationOrder;
	}

	@Override
	public String createMessage(boolean undo) {
		if (undo)
		{
			return ""; // TODO: Formulate a message for removing of reservation
		}
		return String.format("{\"query\":\"mutation { createReservation(input: { reservationDate: \\\"%s\\\" arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\" numberOfOccupants: 4}){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", Timestamp.valueOf(LocalDateTime.now()), reservationOrder.getStartDate(), reservationOrder.getEndDate(), reservationOrder.getNumberOfOccupants());
	}

	@Override
	public void parseResponse(Map<String, Object> response)
	{
		// TODO: parse response from both cancel reservation and create reservation
	}
}
