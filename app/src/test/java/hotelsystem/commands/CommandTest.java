package hotelsystem.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import hotelsystem.room.Deluxe;
import hotelsystem.room.Room;
import hotelsystem.room.Standard;
import hotelsystem.room.VIP;
import hotelsystem.user.Customer;
import order.OrderBuilder;

@TestMethodOrder(OrderAnnotation.class)
public class CommandTest
{
	// Default parameters for Commands
	static CommandInvoker invoker = new CommandInvoker();
	static Customer customer = new Customer("test", "password", "test@test.com");
	static ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
		new Standard("Test Suite 1", 0, 2),
		new Deluxe("Test Suite 2", 0, 2),
		new VIP("Test Suite 3", 0, 2)
	));

	@Test
	@Order(1)
	public void checkRegisterCommandExecution()
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

		// Update customer
		customer = result;
	}

	@Test
	@Order(2)
	public void checkRegisterCommandUndo()
	{
		// Undo command and assert
		boolean undo = invoker.undo();
		assertEquals(true, undo);

		// Retrieve response and assert
		Customer undoResult = invoker.getResponse();
		assertEquals(customer.getId(), undoResult.getId());
		assertEquals(customer.getEmail(), undoResult.getEmail());
	}

	@Test
	@Order(3)
	public void checkLoginUserCommand()
	{
		// TODO: fix ordering as user is undone before login test

		// Send new login command
		invoker.setCommand(new LoginUserCommand(customer));
		invoker.execute();

		// Retrieve data and assert
		Customer result = invoker.getResponse();
		System.out.println(result.getId());

		assertEquals(customer.getUserName(), result.getUserName());
		assertEquals(customer.getPassword(), result.getPassword());
		assertEquals(customer.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0); // TODO: get Jakub to update ID to a nullable type like Integer
	}

	@Test
	@Order(4)
	public void checkCreateRoomsCommandExecution()
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

		rooms = resultRooms;
	}

	@Test
	@Order(5)
	public void checkCreateRoomsCommandUndo()
	{
		// undo command and assert
		boolean undo = invoker.undo();
		assertEquals(true, undo);

		// Retrieve response and assert
		ArrayList<Room> resultUndoRooms = invoker.getResponse();
		assertEquals(rooms.size(), rooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			assertEquals(rooms.get(i).getClass(), resultUndoRooms.get(i).getClass());
			assertEquals(rooms.get(i).getRoomName(), resultUndoRooms.get(i).getRoomName());
			assertEquals(rooms.get(i).getNumberBeds(), resultUndoRooms.get(i).getNumberBeds());
			assertEquals(rooms.get(i).getRoomNumber(), resultUndoRooms.get(i).getRoomNumber());
		}
	}

	@Test
	@Order(6)
	public void checkCreateReservationCommandExecution()
	{
		// Create room for order
		Room room = rooms.get(0);
		room.setOccupant(customer);

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
		builder.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
		builder.addRoom(room);

		order.Order order = builder.getOrder();

		// Send new createRooms request
		invoker.setCommand(new CreateReservationCommand(order));
		invoker.execute();

		// Retrieve response and assert
		order.Order resultOrder = invoker.getResponse();
		assertEquals(order.getStartDate(), resultOrder.getStartDate());
		assertEquals(order.getEndDate(), resultOrder.getEndDate());
		assertEquals(order.getNumberOfDaysBooked(), resultOrder.getNumberOfDaysBooked());
		assertEquals(order.getFinalCost(), resultOrder.getFinalCost());
		assertEquals(order.getNumberOfOccupants(), resultOrder.getNumberOfOccupants());
		assertFalse(resultOrder.getOrderID() == null);
	}

	@Test
	@Order(7)
	public void checkCreateReservationCommandUndo()
	{
		// Override room order
		rooms.set(0, new Deluxe("Test Suite 7", 0, 1));

		// run execution
		checkCreateRoomsCommandExecution();
		order.Order resultOrder = invoker.getResponse();

		// undo command and assert
		boolean undo = invoker.undo();
		assertEquals(true, undo);

		// Retrieve response and assert
		order.Order resultUndoOrder = invoker.getResponse();
		assertEquals(resultOrder.getStartDate(), resultUndoOrder.getStartDate());
		assertEquals(resultOrder.getEndDate(), resultUndoOrder.getEndDate());
		assertEquals(resultOrder.getNumberOfDaysBooked(), resultUndoOrder.getNumberOfDaysBooked());
		assertEquals(resultOrder.getFinalCost(), resultUndoOrder.getFinalCost());
		assertEquals(resultOrder.getNumberOfOccupants(), resultUndoOrder.getNumberOfOccupants());
		assertEquals(resultOrder.getOrderID(), resultUndoOrder.getOrderID());
	}

	@Test
	@Order(8)
	public void checkGetAvailableRoomsCommand()
	{
		// TODO: set specific dates to initial Reservation so we have one for test
	}

	@Test
	@Order(9)
	public void checkCancelReservationCommandExecution()
	{
		// TODO: figure out how to set this command up
	}

	@Test
	@Order(10)
	public void checkCancelReservationCommandUndo()
	{
		
	}

	@Test
	@Order(11)
	public void checkRemoveRoomsCommandExecution()
	{
		
	}

	@Test
	@Order(12)
	public void checkRemoveRoomsCommandUndo()
	{
		// TODO: add undo to command above
	}

	@Test
	@Order(13)
	public void checkRemoveUserCommandExecution()
	{
		// TODO: create removeUser command
	}

	@Test
	@Order(14)
	public void checkRemoveUserCommandUndo()
	{
		// TODO: add undo to command above
	}
}