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
 * A Create Reservation Command for creating a new reservation in the system
 * @author Marcin Sęk
 * @apiNote Response of type Order
 */

 	/**
     * Default Constructor for command
     */
public class CreateReservationCommand extends CommandTemplate<Order>
{
	public static final String MUTATION_NAME = "createReservation";
	public static final String UNDO_MUTATION_NAME = "removeReservation";

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

		UserInterface creator = reservationOrder.getUser();

		ArrayList<Room> rooms = reservationOrder.getRooms();
		StringBuilder orderGuests = new StringBuilder();
		for (Room room : rooms) {
			int roomId = room.getRoomNumber();

			List<UserInterface> occupants = room.getOccupants();
			for (int j = 0; j < occupants.size(); j++) {
				UserInterface occupant = occupants.get(j);

				String firstName = occupant.getFirstName();
				String lastName = occupant.getLastName();

				orderGuests.append(String.format("{firstName: \\\"%s\\\" lastName: \\\"%s\\\" roomId: \\\"%d\\\"}", firstName, lastName, roomId));

				if (j < occupants.size() - 1) {
					orderGuests.append(",");
				}
			}
		}

		return String.format("{\"query\":\"mutation{%s(input:{checkIn: \\\"%s\\\" checkOut: \\\"%s\\\" user: {id: \\\"%d\\\"} guests: [%s]}){id guests{id firstName lastName}}}\"}", MUTATION_NAME, reservationOrder.getStartDate(), reservationOrder.getEndDate(), creator.getId(), orderGuests);
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
		builder.setStartDate(reservationOrder.getStartDate());
		builder.setEndDate(reservationOrder.getEndDate());
		builder.setUser(reservationOrder.getUser());

		List<Room> rooms = reservationOrder.getRooms();

		List<?> guestsList = (List<?>) reservationData.get("guests");
		for (Object o : guestsList) {
			Map<?, ?> guestMap = (Map<?, ?>) o;

			String guestId = (String) guestMap.get("id");
			String firstName = (String) guestMap.get("firstName");
			String lastName = (String) guestMap.get("lastName");

			for (Room room : rooms) {
				boolean found = false;

				List<UserInterface> guests = room.getOccupants();
				for (UserInterface guest : guests) {
					if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
						found = true;
						guest.setId(Integer.parseInt(guestId));
						break;
					}
				}

				if (found) {
					break;
				}
			}
		}

		builder.setRooms((ArrayList<Room>) rooms);

		responseObject = builder.getOrder();

		// Make copy for undo
		this.reservationOrder = responseObject;
	}
}
