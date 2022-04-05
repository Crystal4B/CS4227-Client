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
import hotelsystem.userfactory.UserInterface;

/**
 * A Get Reservation Command for retrieving reservations visible to a specific user
 * @author Marcin Sęk
 * @apiNote Response of type List[Order]
 */
public class GetReservationsByUserCommand extends CommandTemplate<List<Order>>
{
	public static final String QUERY_NAME = "reservationsByUser";

	private UserInterface userInterface;

	/**
	 * Simple constructor for command
	 * @param userInterface the user which is attempting to view their reservations
	 */
	public GetReservationsByUserCommand(UserInterface userInterface)
	{
		this.userInterface = userInterface;
	}

	@Override
	public String createMessage(boolean undo)
	{
		// Undo does not apply to query messages
		return "{\"query\":\"query{" + QUERY_NAME + "(input: {id: " + userInterface.getId() + " type: \\\"" + userInterface.getClass().getSimpleName() + "\\\" email: \\\"" + userInterface.getEmail() + "\\\"}){id checkIn checkOut user{id type} guests{id firstName lastName room{id type numberOfBeds}}}}\"}";
	}

	@Override
	public void parseResponse(Map<?, ?> response)
	{
		List<?> reservationList = (List<?>) response.get(QUERY_NAME);

		responseObject = new ArrayList<>();
		for (Object o : reservationList) {
			Map<?, ?> reservationMap = (Map<?, ?>) o;

			OrderBuilder builder = new OrderBuilder();

			builder.setOrderID((String) reservationMap.get("id"));
			builder.setStartDate(Timestamp.valueOf((String) reservationMap.get("checkIn")));
			builder.setEndDate(Timestamp.valueOf((String) reservationMap.get("checkOut")));

			ArrayList<Room> rooms = new ArrayList<>();

			List<?> guestList = (List<?>) reservationMap.get("guests");

			for (Object value : guestList) {
				Map<?, ?> guestMap = (Map<?, ?>) value;
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
			responseObject.add(builder.getOrder());
		}
	}
}
