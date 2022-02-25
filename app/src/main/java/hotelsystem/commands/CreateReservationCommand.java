package hotelsystem.commands;

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
		ReservationSystem.createReservation(reservationOrder);
	}
}
