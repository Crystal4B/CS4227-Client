package hotelsystem.commands;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import hotelsystem.ReservationSystem;
import order.Order;

/**
 * A Create Reservation Command for creating a new reservation in the system
 * @author Marcin Sęk
 */
public class CreateReservationCommand implements Command
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
	public void execute()
	{
		String message = String.format("{\"query\":\"mutation { createReservation(input: { reservationDate: \\\"%s\\\" arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\" numberOfOccupants: 4}){id reservationDate arrivalDate departureDate numberOfOccupants}}\"}", Timestamp.valueOf(LocalDateTime.now()), reservationOrder.getStartDate(), reservationOrder.getEndDate(), reservationOrder.getNumberOfOccupants());

		ReservationSystem.sendRequest(message);
	}

	@Override
	public void undo()
	{
		// TODO: Formulate a message for removing of reservation
	}
}
