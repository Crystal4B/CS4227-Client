package hotelsystem.requestsystem.commands.reservations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.order.Order;
import hotelsystem.order.OrderBuilder;
import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.roomfactory.Room;
import hotelsystem.userfactory.UserInterface;

/**
 * A Cancel Reservation Command for canceling an exiting reservation in the system
 * @author Marcin Sęk
 * @apiNote Response of type Order
 */
public class CancelReservationCommand extends CommandTemplate<Order>
{
	public static final String MUTATION_NAME = "removeReservation";
	public static final String UNDO_MUTATION_NAME = "createReservation";

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
			UserInterface creator = orderCancelation.getUser();

			ArrayList<Room> rooms = orderCancelation.getRooms();
			StringBuilder orderGuests = new StringBuilder();
			for (int i = 0; i < rooms.size(); i++)
			{
				Room room = rooms.get(i);
				int roomId = room.getRoomNumber();
	
				List<UserInterface> occupants = room.getOccupants();
				for (int j = 0; j < rooms.size(); j++)
				{
					UserInterface occupant = occupants.get(j);
	
					String firstName = occupant.getFirstName();
					String lastName = occupant.getLastName();
	
					orderGuests.append(String.format("{firstName: \\\"%s\\\" lastName: \\\"%s\\\" roomId: \\\"%d\\\"}", firstName, lastName, roomId));
	
					if (i < occupants.size() - 1)
					{
						orderGuests.append(",");
					}
				}
			}
	
			return String.format("{\"query\":\"mutation{%s(input:{checkIn: \\\"%s\\\" checkOut: \\\"%s\\\" user: {id: \\\"%d\\\"} guests: [%s]}){id guests{id firstName lastName}}}\"}", MUTATION_NAME, orderCancelation.getStartDate(), orderCancelation.getEndDate(), creator.getId(), orderGuests);
	
		}
		return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id}}\"}", MUTATION_NAME, orderCancelation.getOrderID());
	}

	@Override
	public void parseResponse(Map<?, ?> response)
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

		Map<?, ?> reservationData = (Map<?, ?>) response.get(mutation);
		
		String reservationId = (String) reservationData.get("id");
		
		OrderBuilder builder = new OrderBuilder();
		builder.setOrderID(reservationId);
		builder.setStartDate(orderCancelation.getStartDate());
		builder.setEndDate(orderCancelation.getEndDate());
		builder.setUser(orderCancelation.getUser());

		List<Room> rooms = orderCancelation.getRooms();

		builder.setRooms((ArrayList<Room>) rooms);

		responseObject = builder.getOrder();

		// Make copy for undo
		this.orderCancelation = responseObject;
	}
}
