package requestsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import hotelsystem.room.Standard;
import hotelsystem.user.Customer;
import hotelsystem.user.Guest;
import hotelsystem.user.Staff;
import hotelsystem.user.User;
import order.OrderBuilder;
import requestsystem.commands.CancelReservationCommand;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.CreateReservationCommand;
import requestsystem.commands.CreateRoomsCommand;
import requestsystem.commands.GetAvailableRoomsCommand;
import requestsystem.commands.LoginUserCommand;
import requestsystem.commands.RegisterUserCommand;
import requestsystem.commands.RemoveRoomsCommand;
import requestsystem.commands.RemoveUserCommand;

@TestMethodOrder(OrderAnnotation.class)
public class CommandExecuteTest
{
	// Reservation variabes
	static Timestamp startDate = Timestamp.valueOf(LocalDateTime.now());
	static Timestamp endDate = Timestamp.valueOf(LocalDateTime.now().plusDays(2));

	// Default parameters for Commands
	static CommandInvoker invoker = new CommandInvoker();
	static Customer customer = new Customer("testCustomer", "customer_password", "testCustomer@test.com");
	static Staff staff = new Staff("testStaff", "staff_password", "testStaff@test.com");
	static ArrayList<Standard> rooms = new ArrayList<>(Arrays.asList(
		new Standard("Standard", 0, 2),
		new Standard("Standard", 0, 2),
		new Standard("Standard", 0, 2)
	));

	static order.Order reservation;

	public boolean contains(ArrayList<Standard> list, Standard room)
	{
		for (Standard roomStandard : list)
		{
			if (roomStandard.getRoomNumber() == room.getRoomNumber())
			{
				return true;
			}
		}
		return false;
	}

	@Test
	@Order(1)
	public void checkRegisterCommandOnCustomer()
	{
		// Send new customer request
		invoker.setCommand(new RegisterUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert
		User result = invoker.getResponse();
		assertFalse(result instanceof Staff);
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
	public void checkRegisterCommandOnStaff()
	{
		// Send new customer request
		invoker.setCommand(new RegisterUserCommand(staff));
		invoker.execute();

		// Retrieve response and assert
		User result = invoker.getResponse();
		assertFalse(result instanceof Customer);
		assertTrue(result instanceof Staff);
		assertEquals(staff.getUserName(), result.getUserName());
		assertEquals(staff.getPassword(), result.getPassword());
		assertEquals(staff.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0);

		// Update customer
		staff = (Staff) result;
	}

	@Test
	@Order(3)
	public void checkIncorrectRegisterCommandOnCustomer()
	{
		// Send new customer request
		invoker.setCommand(new RegisterUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert
		User result = invoker.getResponse();
		assertTrue(result == null);
	}

	@Test
	@Order(4)
	public void checkIncorrectRegisterCommandOnStaff()
	{
		// Send new customer request
		invoker.setCommand(new RegisterUserCommand(staff));
		invoker.execute();

		// Retrieve response and assert
		User result = invoker.getResponse();
		assertTrue(result == null);
	}

	@Test
	@Order(5)
	public void checkCorrectLoginUserCommandOnCustomer()
	{
		// Send new login command
		invoker.setCommand(new LoginUserCommand(customer));
		invoker.execute();

		// Retrieve data and assert
		User result = invoker.getResponse();
		assertFalse(result instanceof Staff);
		assertTrue(result instanceof Customer);
		assertEquals(customer.getUserName(), result.getUserName());
		assertEquals(customer.getPassword(), result.getPassword());
		assertEquals(customer.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0);
	}

	@Test
	@Order(6)
	public void checkCorrectLoginUserCommandOnStaff()
	{
		// Send new login command
		invoker.setCommand(new LoginUserCommand(staff));
		invoker.execute();

		// Retrieve data and assert
		User result = invoker.getResponse();
		assertFalse(result instanceof Customer);
		assertTrue(result instanceof Staff);
		assertEquals(staff.getUserName(), result.getUserName());
		assertEquals(staff.getPassword(), result.getPassword());
		assertEquals(staff.getEmail(), result.getEmail());
		assertFalse(result.getId() == 0);
	}

	@Test
	@Order(7)
	public void checkIncorrectLoginUserCommandCustomer()
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
	@Order(8)
	public void checkIncorrectLoginUserCommandStaff()
	{
		// Set incorrect password
		String password = staff.getPassword();
		staff.setPassword("INCORRECT_PASSWORD");

		// Send new login command
		invoker.setCommand(new LoginUserCommand(staff));
		invoker.execute();

		// Retrieve data and assert
		User result = invoker.getResponse();
		assertTrue(result == null);

		staff.setPassword(password);
	}

	@Test
	@Order(9)
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

	@Test
	@Order(10)
	public void checkCreateReservationCommand()
	{
		// Create room for order
		Standard room = rooms.get(0);
		room.addOccupant(new Guest("Joe", "Stephan", -1));

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(startDate);
		builder.setEndDate(endDate);
		builder.addRoom(room);
		builder.setUser(customer);

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

		ArrayList<Standard> rooms = order.getRooms();
		ArrayList<Standard> resultRooms = resultOrder.getRooms();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			Standard roomStandard = rooms.get(i);
			Standard resultRoomStandard = resultRooms.get(i);

			assertEquals(roomStandard.getRoomNumber(), resultRoomStandard.getRoomNumber());
			assertEquals(roomStandard.getRoomName(), resultRoomStandard.getRoomName());
			assertEquals(roomStandard.getPerks(), resultRoomStandard.getPerks());
			assertEquals(roomStandard.getPrice(), resultRoomStandard.getPrice());

			ArrayList<User> occupants = roomStandard.getOccupants();
			ArrayList<User> resultOccupants = resultRoomStandard.getOccupants();
			assertEquals(occupants.size(), resultOccupants.size());
			for (int j = 0; j < occupants.size(); j++)
			{
				Guest guest = (Guest) occupants.get(j);
				Guest resultGuest = (Guest) resultOccupants.get(j);

				assertEquals(guest.getFirstName(), resultGuest.getFirstName());
				assertEquals(guest.getLastName(), resultGuest.getLastName());
				assertFalse(resultGuest.getId() == -1);
			}
		}

		reservation = resultOrder;
	}

	// @Test
	// @Order(11)
	public void checkGetAvailableRoomsCommand()
	{
		invoker.setCommand(new GetAvailableRoomsCommand(startDate, endDate));
		invoker.execute();

		// Weird behaviour I need to fix

		// Retrieve Response and assert
		Map<String, ArrayList<Standard>> availableRooms = invoker.getResponse();

		// Check for room reserved in Test 10 not being available
		assertFalse(contains(availableRooms.get("Standard"), rooms.get(0)));
		// Check for other generated rooms being available
		assertTrue(contains(availableRooms.get("Standard"), rooms.get(1)));
		assertTrue(contains(availableRooms.get("Standard"), rooms.get(2)));
	}

	@Test
	@Order(12)
	public void checkCancelReservationCommand()
	{
		invoker.setCommand(new CancelReservationCommand(reservation));
		invoker.execute();

		// Retrieve response and assert
		order.Order resultOrder = invoker.getResponse();
		assertEquals(reservation.getStartDate(), resultOrder.getStartDate());
		assertEquals(reservation.getEndDate(), resultOrder.getEndDate());
		assertEquals(reservation.getNumberOfDaysBooked(), resultOrder.getNumberOfDaysBooked());
		assertEquals(reservation.getFinalCost(), resultOrder.getFinalCost());
		assertEquals(reservation.getNumberOfOccupants(), resultOrder.getNumberOfOccupants());
		assertFalse(resultOrder.getOrderID() == null);

		ArrayList<Standard> rooms = reservation.getRooms();
		ArrayList<Standard> resultRooms = resultOrder.getRooms();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			Standard roomStandard = rooms.get(i);
			Standard resultRoomStandard = resultRooms.get(i);

			assertEquals(roomStandard.getRoomNumber(), resultRoomStandard.getRoomNumber());
			assertEquals(roomStandard.getRoomName(), resultRoomStandard.getRoomName());
			assertEquals(roomStandard.getPerks(), resultRoomStandard.getPerks());
			assertEquals(roomStandard.getPrice(), resultRoomStandard.getPrice());

			ArrayList<User> occupants = roomStandard.getOccupants();
			ArrayList<User> resultOccupants = resultRoomStandard.getOccupants();
			assertEquals(occupants.size(), resultOccupants.size());
			for (int j = 0; j < occupants.size(); j++)
			{
				Guest guest = (Guest) occupants.get(j);
				Guest resultGuest = (Guest) resultOccupants.get(j);

				assertEquals(guest.getFirstName(), resultGuest.getFirstName());
				assertEquals(guest.getLastName(), resultGuest.getLastName());
				assertEquals(guest.getId(), resultGuest.getId());
			}
		}
	}

	@Test
	@Order(13)
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
	@Order(14)
	public void checkRemoveUserCommandOnCustomer()
	{
		// Send new customer request
		invoker.setCommand(new RemoveUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert (NOTE: Removing user does not return username and password)
		User result = invoker.getResponse();
		assertFalse(result instanceof Staff);
		assertTrue(result instanceof Customer);
		assertEquals(customer.getEmail(), result.getEmail());
		assertEquals(customer.getId(), result.getId());
	}

	@Test
	@Order(15)
	public void checkRemoveUserCommandOnStaff()
	{
		// Send new customer request
		invoker.setCommand(new RemoveUserCommand(staff));
		invoker.execute();

		// Retrieve response and assert (NOTE: Removing user does not return username and password)
		User result = invoker.getResponse();
		assertFalse(result instanceof Customer);
		assertTrue(result instanceof Staff);
		assertEquals(staff.getEmail(), result.getEmail());
		assertEquals(staff.getId(), result.getId());
	}
}