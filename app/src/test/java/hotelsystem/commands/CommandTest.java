package hotelsystem.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import hotelsystem.room.Deluxe;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;
import hotelsystem.user.Customer;
import order.Order;
import order.OrderBuilder;

public class CommandTest
{
	// Default parameters for Commands
	CommandInvoker invoker = new CommandInvoker();
	Customer customer = new Customer("test", "password", "test@test.com");
	// Create rooms
	ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
		new Standard("Test Suite 1", 0, 2),
		new Deluxe("Test Suite 2", 0, 2),
		new VIP("Test Suite 3", 0, 2)
	));

	// Command Unit Tests
	@Test void checkRegisterCommandExecution()
	{
		// Send new customer request
		invoker.setCommand(new RegisterUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert
		Customer result = invoker.getResponse();
		assertEquals(customer.getUserName(), result.getUserName());
		assertEquals(customer.getPassword(), result.getPassword());
		assertEquals(customer.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0); // TODO: get Jakub to update ID to a nullable type like Integer
	}

	@Test void checkRegisterCommandUndo()
	{
		// Override customer
		customer = new Customer("test 2", "password 2", "test2@test.com");

		// Run execution
		checkRegisterCommandExecution();
		Customer result = invoker.getResponse();

		// Undo command and assert
		boolean undo = invoker.undo();
		assertEquals(true, undo);

		// Retrieve response and assert
		Customer undoResult = invoker.getResponse();
		assertEquals(result.getId(), undoResult.getId());
		assertEquals(result.getEmail(), undoResult.getEmail());
	}

	@Test void checkLoginUserCommand()
	{
		// Send new login command
		invoker.setCommand(new LoginUserCommand(customer));
		invoker.execute();

		// Retrieve data and assert
		Customer result = invoker.getResponse();
		assertEquals(customer.getUserName(), result.getUserName());
		assertEquals(customer.getPassword(), result.getPassword());
		assertEquals(customer.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0); // TODO: get Jakub to update ID to a nullable type like Integer
	}

	@Test void checkCreateRoomsCommandExecution()
	{
		// Send new createRooms request
		invoker.setCommand(new CreateRoomsCommand(rooms));
		invoker.execute();

		// Retrieve response and assert
		ArrayList<Room> resultRooms = invoker.getResponse();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			assertEquals(rooms.get(i).getClass(), resultRooms.get(i).getClass());
			assertEquals(rooms.get(i).getRoomName(), resultRooms.get(i).getRoomName());
			assertEquals(rooms.get(i).getNumberBeds(), resultRooms.get(i).getNumberBeds());
			assertFalse(resultRooms.get(i).getRoomNumber() == 0);
		}
	}

	@Test void checkCreateRoomsCommandUndo()
	{
		// Override rooms
		rooms = new ArrayList<>(Arrays.asList(
			new Standard("Test Suite 4", 0, 3),
			new Deluxe("Test Suite 5", 0, 5),
			new VIP("Test Suite 6", 0, 1)
		));

		// Run execution
		checkCreateRoomsCommandExecution();
		ArrayList<Room> resultRooms = invoker.getResponse();

		// undo command and assert
		boolean undo = invoker.undo();
		assertEquals(true, undo);

		// Retrieve response and assert
		ArrayList<Room> resultUndoRooms = invoker.getResponse();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			assertEquals(resultRooms.get(i).getClass(), resultUndoRooms.get(i).getClass());
			assertEquals(resultRooms.get(i).getRoomName(), resultUndoRooms.get(i).getRoomName());
			assertEquals(resultRooms.get(i).getNumberBeds(), resultUndoRooms.get(i).getNumberBeds());
			assertEquals(resultRooms.get(i).getRoomNumber(), resultUndoRooms.get(i).getRoomNumber());
		}
	}

	@Test void getAvailableRoomsCommand()
	{

	}

	@Test void checkCreateReservationCommandExecution()
	{
		// Create room for order
		Room room = rooms.get(0);
		room.setOccupant(new Customer("test", "password", "test@test.com"));

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
		builder.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
		builder.addRoom(room);

		Order order = builder.getOrder();

		// Send new createRooms request
		invoker.setCommand(new CreateReservationCommand(order));
		invoker.execute();

		// Retrieve response and assert
		Order resultOrder = invoker.getResponse();
		assertEquals(order.getStartDate(), resultOrder.getStartDate());
		assertEquals(order.getEndDate(), resultOrder.getEndDate());
		assertEquals(order.getNumberOfDaysBooked(), resultOrder.getNumberOfDaysBooked());
		assertEquals(order.getFinalCost(), resultOrder.getFinalCost());
		assertEquals(order.getNumberOfOccupants(), resultOrder.getNumberOfOccupants());
		assertFalse(resultOrder.getOrderID() == null);
	}

	@Test void checkCreateReservationCommandUndo()
	{
		// Override room order
		rooms.set(0, new Deluxe("Test Suite 7", 0, 1));

		// run execution
		checkCreateRoomsCommandExecution();
		Order resultOrder = invoker.getResponse();

		// undo command and assert
		boolean undo = invoker.undo();
		assertEquals(true, undo);

		// Retrieve response and assert
		Order resultUndoOrder = invoker.getResponse();
		assertEquals(resultOrder.getStartDate(), resultUndoOrder.getStartDate());
		assertEquals(resultOrder.getEndDate(), resultUndoOrder.getEndDate());
		assertEquals(resultOrder.getNumberOfDaysBooked(), resultUndoOrder.getNumberOfDaysBooked());
		assertEquals(resultOrder.getFinalCost(), resultUndoOrder.getFinalCost());
		assertEquals(resultOrder.getNumberOfOccupants(), resultUndoOrder.getNumberOfOccupants());
		assertEquals(resultOrder.getOrderID(), resultUndoOrder.getOrderID());
	}

	@Test void checkCancelReservationCommandExecution()
	{

	}

	@Test void checkCancelReservationCommandUndo()
	{
		
	}

	@Test void checkRemoveRoomsCommandExecution()
	{
		// TODO: create removeRooms command
	}

	@Test void checkRemoveRoomsCommandUndo()
	{
		// TODO: add undo to command above
	}

	@Test void checkRemoveUserCommandExecution()
	{
		// TODO: create removeUser command
	}

	@Test void checkRemoveUserCommandUndo()
	{
		// TODO: add undo to command above
	}
}
