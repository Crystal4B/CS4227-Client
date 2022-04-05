package hotelsystem.requestsystem.commands.reservations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.order.Order;
import hotelsystem.order.OrderBuilder;
import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.roomfactory.Room;
import hotelsystem.roomfactory.RoomFactory;
import hotelsystem.userfactory.Guest;
/**
 * A Update Reservation Paid Command for changing the Reservations paid status
 * @author Marcin Sęk
 * @apiNote Response of type Order
 */

 /**
     * Default Constructor for command
     */
public class UpdateReservationPaidCommand extends CommandTemplate<Order>
{
	public static final String MUTATION_NAME = "updateReservationPaid";

	private int id;
	private boolean paid;

	/**
	 * Simple constructor for command
	 * @param id of the reservation being updated
	 * @param paid the new paid status for the reservation
	 */
	public UpdateReservationPaidCommand(int id, boolean paid)
	{
		this.id = id;
		this.paid = paid;
	}

	@Override
	public String createMessage(boolean undo)
	{
		boolean paidVal = paid != undo;
		return String.format("{\"query\":\"mutation{%s(input:{id: %d paid: %b}){id checkIn checkOut paid user{id type} guests{id firstName lastName room{id type numberOfBeds}}}}\"}", MUTATION_NAME, id, paidVal);
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		Map<?, ?> reservationData = (Map<?, ?>) response.get(MUTATION_NAME);
		OrderBuilder builder = new OrderBuilder();

		builder.setOrderID((String)reservationData.get("id"));
		builder.setStartDate(Timestamp.valueOf((String)reservationData.get("checkIn")));
		builder.setEndDate(Timestamp.valueOf((String)reservationData.get("checkOut")));

		ArrayList<Room> rooms = new ArrayList<>();
		
		List<?> guestsList = (List<?>) reservationData.get("guests");
		for (Object o : guestsList) {
			Map<?, ?> guestMap = (Map<?, ?>) o;
			Map<?, ?> roomMap = (Map<?, ?>) guestMap.get("room");

			int id = Integer.parseInt((String) roomMap.get("id"));

			Room standardRoom = null;

			boolean found = false;
			for (Room room : rooms) {
				if (room.getRoomNumber() == id) {
					standardRoom = room;
					found = true;
					break;
				}
			}

			if (!found) {
				String type = (String) roomMap.get("type");
				int numberOfBeds = (int) roomMap.get("numberOfBeds");
				switch (type) {
					case "Standard" -> standardRoom = (RoomFactory.createStandard(id, numberOfBeds));
					case "Deluxe" -> standardRoom = (RoomFactory.createDeluxe(id, numberOfBeds));
					case "VIP" -> standardRoom = (RoomFactory.createVIP(id, numberOfBeds));
					default -> System.out.println("Unknown type of Room!");
				}
				rooms.add(standardRoom);
			}

			int guestId = Integer.parseInt((String) guestMap.get("id"));
			String firstName = (String) guestMap.get("firstName");
			String lastName = (String) guestMap.get("lastName");

			standardRoom.addOccupant(new Guest(firstName, lastName, guestId));
		}

		builder.setRooms(rooms);
		responseObject = builder.getOrder();
	}
}
