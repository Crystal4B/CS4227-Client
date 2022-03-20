package requestsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.room.Standard;
import hotelsystem.user.Guest;
import hotelsystem.user.User;
import order.Order;
import order.OrderBuilder;

/**
 * A Create Reservation Command for creating a new reservation in the system
 * @author Marcin Sęk
 * @apiNote Response of type Order
 */
public class CreateReservationCommand extends CommandTemplate<Order>
{
	private static final String MUTATION_NAME = "createReservation";
	private static final String UNDO_MUTATION_NAME = "removeReservation";

	private Order reservationOrder;

	/**
	 * Simple constructor for command
	 * @param reservationOrder the order being placed in the system
	 */
	public CreateReservationCommand(Order reservationOrder)
	{
		this.reservationOrder = reservationOrder;
	}

	@Override
	public String createMessage(boolean undo)
	{
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id arrivalDate departureDate numberOfOccupants rooms{id type name perks numberOfBeds rate}}}\"}", UNDO_MUTATION_NAME, reservationOrder.getOrderID());
		}

		User creator = reservationOrder.getUser();

		ArrayList<Standard> rooms = reservationOrder.getRooms();
		String orderGuests = "";
		for (int i = 0; i < rooms.size(); i++)
		{
			Standard room = rooms.get(i);
			int roomId = room.getRoomNumber();

			List<User> occupants = room.getOccupants();
			for (int j = 0; j < rooms.size(); j++)
			{
				User occupant = occupants.get(j);

				String firstName = occupant.getFirstName();
				String lastName = occupant.getLastName();

				orderGuests += String.format("{firstName: \\\"%s\\\" lastName: \\\"%s\\\" roomId: \\\"%d\\\"}", firstName, lastName, roomId);

				if (i < occupants.size() - 1)
				{
					orderGuests += ",";
				}
			}
		}

		return String.format("{\"query\":\"mutation{%s(input:{checkIn: \\\"%s\\\" checkOut: \\\"%s\\\" user: {id: \\\"%d\\\"} guests: [%s]}){id guests{id firstName lastName}}}\"}", MUTATION_NAME, reservationOrder.getStartDate(), reservationOrder.getEndDate(), creator.getId(), orderGuests);
	}

	@Override
	@SuppressWarnings("unchecked")
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

		// TODO: Get Jordan to have a look at this with you
		Map<String, Object> reservationData = (Map<String, Object>) response.get(mutation);
		String reservationId = (String) reservationData.get("id");

		List<Map<String, Object>> guestsMap = (List<Map<String, Object>>) reservationData.get("guests");

		for (Map<String, Object> map : guestsMap)
		{
			String guestId = (String) map.get("id");
			String firstName = (String) map.get("firstName");
			String lastName = (String) map.get("lastName");
		}

		responseObject = reservationOrder;

		// Make copy for undo
		this.reservationOrder = responseObject;
	}
}
