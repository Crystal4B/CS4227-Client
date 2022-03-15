package hotelsystem.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import hotelsystem.room.Standard;
import hotelsystem.user.Customer;
import hotelsystem.user.User;
import order.OrderBuilder;

@TestMethodOrder(OrderAnnotation.class)
public class CommandExecuteTest
{
	// Default parameters for Commands
	static CommandInvoker invoker = new CommandInvoker();
	static Customer customer = new Customer("test", "password", "test@test.com");
	static ArrayList<Standard> rooms = new ArrayList<>(Arrays.asList(
		new Standard("Standard", 0, 2),
		new Standard("Standard", 0, 2),
		new Standard("Standard", 0, 2)
	));

	@Test
	@Order(1)
	public void checkRegisterCommand()
	{
		// Send new customer request
		invoker.setCommand(new RegisterUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert
		User result = invoker.getResponse();
		assertTrue(result instanceof Customer);
		assertEquals(customer.getUserName(), result.getUserName());
		assertEquals(customer.getPassword(), result.getPassword());
		assertEquals(customer.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0);

		// Update customer
		customer = (Customer) result;
	}

	@Test
	@Order(2)
	public void checkCorrectLoginUserCommand()
	{
		// Send new login command
		invoker.setCommand(new LoginUserCommand(customer));
		invoker.execute();

		// Retrieve data and assert
		User result = invoker.getResponse();
		assertTrue(result instanceof Customer);
		assertEquals(customer.getUserName(), result.getUserName());
		assertEquals(customer.getPassword(), result.getPassword());
		assertEquals(customer.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0);
	}

	@Test
	@Order(3)
	public void checkIncorrectLoginUserCommand()
	{
		// Set incorrect password
		String password = customer.getPassword();
		customer.setPassword("INCORRECT_PASSWORD");

		// Send new login command
		invoker.setCommand(new LoginUserCommand(customer));
		invoker.execute();

		// Retrieve data and assert
		User result = invoker.getResponse();
		assertTrue(result == null);

		customer.setPassword(password);
	}

	@Test
	@Order(4)
	public void checkCreateRoomsCommand()
	{
		// Send new createRooms request
		invoker.setCommand(new CreateRoomsCommand(rooms));
		invoker.execute();

		// Retrieve response and assert
		ArrayList<Standard> resultRooms = invoker.getResponse();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			assertEquals(rooms.get(i).getRoomName(), resultRooms.get(i).getRoomName());
			assertEquals(rooms.get(i).getNumberBeds(), resultRooms.get(i).getNumberBeds());
			assertFalse(resultRooms.get(i).getRoomNumber() == 0);
		}

		rooms = resultRooms;
	}

	// Tests 5-7 work in progress

	// @Test
	// @Order(5)
	public void checkCreateReservationCommand()
	{
		// Create room for order
		Standard room = rooms.get(0);
		room.addOccupant(customer.getLegalName());

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

	// @Test
	// @Order(6)
	public void checkGetAvailableRoomsCommand()
	{
		invoker.setCommand(new GetAvailableRoomsCommand(Timestamp.valueOf("2020-02-10 10:00:00"), Timestamp.valueOf("2020-02-12 10:00:00")));
		invoker.execute();

		// Retrieve Response and assert
		ArrayList<Standard> availableRooms = invoker.getResponse();
		assertEquals(rooms.size(), availableRooms.size()); // TODO: Update test to work with any number of rooms
		for (int i = 0; i < availableRooms.size(); i++)
		{
			assertEquals(rooms.get(i).getRoomNumber(), availableRooms.get(i).getRoomNumber());
			assertEquals(rooms.get(i).getRoomName(), availableRooms.get(i).getRoomName());
			assertEquals(rooms.get(i).getNumberBeds(), availableRooms.get(i).getNumberBeds());
		}
	}

	// @Test
	// @Order(7)
	public void checkCancelReservationCommand()
	{
		// TODO: Fix creaate reservation first
		assertFalse(true);
	}

	@Test
	@Order(8)
	public void checkRemoveRoomsCommand()
	{
		// Send new createRooms request
		invoker.setCommand(new RemoveRoomsCommand(rooms));
		invoker.execute();

		// Retrieve response and assert
		ArrayList<Standard> resultRooms = invoker.getResponse();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			assertEquals(rooms.get(i).getRoomName(), resultRooms.get(i).getRoomName());
			assertEquals(rooms.get(i).getNumberBeds(), resultRooms.get(i).getNumberBeds());
			assertFalse(resultRooms.get(i).getRoomNumber() == 0);
		}

		rooms = resultRooms;
	}
	
	@Test
	@Order(9)
	public void checkRemoveUserCommand()
	{
		// Send new customer request
		invoker.setCommand(new RemoveUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert (NOTE: Removing user does not return username and password)
		User result = invoker.getResponse();
		assertTrue(result instanceof Customer);
		assertEquals(customer.getEmail(), result.getEmail());
		assertEquals(customer.getId(), result.getId());
	}
}