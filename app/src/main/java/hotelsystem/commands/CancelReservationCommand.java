package hotelsystem.commands;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import hotelsystem.ReservationSystem;
import order.Order;

/**
 * A Cancel Reservation Command for canceling an exiting reservation in the system
 * @author Marcin Sęk
 */
public class CancelReservationCommand implements Command
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
	public void execute()
	{
		// TODO: formulate a message for removing a reservation from the system
	}

	@Override
	public void undo()
	{
		String message = String.format("{\"query\":\"mutation { createReservation(input: { reservationDate: \\\"%s\\\" arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\" numberOfOccupants: 4}){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", Timestamp.valueOf(LocalDateTime.now()), orderCancelation.getStartDate(), orderCancelation.getEndDate(), orderCancelation.getNumberOfOccupants());

		ReservationSystem.sendRequest(message);
	}
}
