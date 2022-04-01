package requestsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import hotelsystem.userFactory.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import billingsystem.CouponVisitor;
import hotelsystem.roomFactory.Room;
import order.OrderBuilder;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.reservations.CancelReservationCommand;
import requestsystem.commands.reservations.CreateReservationCommand;
import requestsystem.commands.reservations.GetReservationsByUserCommand;
import requestsystem.commands.rooms.CreateRoomsCommand;
import requestsystem.commands.rooms.GetAvailableRoomsCommand;
import requestsystem.commands.rooms.RemoveRoomsCommand;
import requestsystem.commands.users.LoginUserCommand;
import requestsystem.commands.users.RegisterUserCommand;
import requestsystem.commands.users.RemoveUserCommand;
import requestsystem.commands.vouchers.CreateVoucherCommand;
import requestsystem.commands.vouchers.RemoveVoucherCommand;
import requestsystem.commands.vouchers.ValidateVoucherCommand;
import userinterface.LoginUI;

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
	static ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
		new Room("Room", 0, 2),
		new Room("Room", 0, 2),
		new Room("Room", 0, 2)
	));

	static order.Order reservation;

	static CouponVisitor coupan = new CouponVisitor();

	public boolean contains(ArrayList<Room> list, Room room)
	{
		for (Room roomStandard : list)
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
		UserInterface result = invoker.getResponse();
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
		UserInterface result = invoker.getResponse();
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
		UserFactory result = invoker.getResponse();
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
		UserFactory result = invoker.getResponse();
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
		UserInterface result = invoker.getResponse();
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
		UserInterface result = invoker.getResponse();
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
		UserFactory result = invoker.getResponse();
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
		UserFactory result = invoker.getResponse();
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
		ArrayList<Room> resultRooms = invoker.getResponse();
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
	public void checkCreateVoucherCommand()
	{
		// Create voucher for user
		CouponVisitor visitor = new CouponVisitor();
		visitor.TypeSet("Voucher");
		visitor.DiscountSet(2.5);
		LoginUI.setUser(staff);

		// Send request
		invoker.setCommand(new CreateVoucherCommand(visitor));
		invoker.execute();

		// Retrieve response and assert
		CouponVisitor result = invoker.getResponse();
		assertEquals(visitor.TypeGet(), result.TypeGet());
		assertEquals(visitor.DiscountGet(), result.DiscountGet());
		assertFalse(result.CodeGet().equals(""));

		
		coupan = result;
		System.out.println(coupan.CodeGet());
	}

	@Test
	@Order(11)
	public void checkValidateVoucherCommand()
	{
		// Send request
		invoker.setCommand(new ValidateVoucherCommand(coupan));
		invoker.execute();

		System.out.println(coupan.CodeGet());

		// Retrieve response and assert
		CouponVisitor result = invoker.getResponse();
		assertEquals(coupan.TypeGet(), result.TypeGet());
		assertEquals(coupan.DiscountGet(), result.DiscountGet());
		assertEquals(coupan.CodeGet(), result.CodeGet());
	}

	@Test
	@Order(12)
	public void checkCreateReservationCommand()
	{
		// Create room for order
		Room room = rooms.get(0);
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

		ArrayList<Room> rooms = order.getRooms();
		ArrayList<Room> resultRooms = resultOrder.getRooms();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			Room roomStandard = rooms.get(i);
			Room result = resultRooms.get(i);

			assertEquals(roomStandard.getRoomNumber(), result.getRoomNumber());
			assertEquals(roomStandard.getRoomName(), result.getRoomName());
			assertEquals(roomStandard.getPerks(), result.getPerks());
			assertEquals(roomStandard.getPrice(), result.getPrice());

			ArrayList<UserInterface> occupants = roomStandard.getOccupants();
			ArrayList<UserInterface> resultOccupants = result.getOccupants();
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
	public void checkGetReservationsByUserCommand()
	{
		invoker.setCommand(new GetReservationsByUserCommand(customer));
		invoker.execute();

		// Weird ordering issue

		//Retrieve Response and assert
		List<order.Order> reservations = invoker.getResponse();
		assertEquals(1, reservations.size());
		
		order.Order resultReservation = reservations.get(0);
		assertEquals(reservation.getOrderID(), resultReservation.getOrderID());
		assertEquals(resultReservation.getStartDate(), resultReservation.getStartDate());
		assertEquals(resultReservation.getEndDate(), resultReservation.getEndDate());
		assertEquals(resultReservation.getNumberOfDaysBooked(), resultReservation.getNumberOfDaysBooked());
		assertEquals(resultReservation.getRateCost(), resultReservation.getRateCost());
		assertEquals(resultReservation.getFinalCost(), resultReservation.getFinalCost());
		assertEquals(resultReservation.getNumberOfOccupants(), resultReservation.getNumberOfOccupants());

		ArrayList<Room> rooms = reservation.getRooms();
		ArrayList<Room> resultRooms = resultReservation.getRooms();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			Room room = rooms.get(i);
			Room result = resultRooms.get(i);

			assertEquals(room.getRoomNumber(), result.getRoomNumber());
			assertEquals(room.getRoomName(), result.getRoomName());
			assertEquals(room.getPerks(), result.getPerks());
			assertEquals(room.getPrice(), result.getPrice());

			ArrayList<UserInterface> occupants = room.getOccupants();
			ArrayList<UserInterface> resultOccupants = result.getOccupants();
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
	}

	// @Test
	// @Order(11)
	public void checkGetAvailableRoomsCommand()
	{
		invoker.setCommand(new GetAvailableRoomsCommand(startDate, endDate));
		invoker.execute();

		// Weird behaviour I need to fix

		// Retrieve Response and assert
		Map<String, ArrayList<Room>> availableRooms = invoker.getResponse();

		// Check for room reserved in Test 10 not being available
		assertFalse(contains(availableRooms.get("Standard"), rooms.get(0)));
		// Check for other generated rooms being available
		assertTrue(contains(availableRooms.get("Standard"), rooms.get(1)));
		assertTrue(contains(availableRooms.get("Standard"), rooms.get(2)));
	}

	// @Test
	// @Order(14)
	public void checkRemoveVoucherCommand()
	{
		invoker.setCommand(new RemoveVoucherCommand(coupan));
		invoker.execute();

		// Retrieve response and assert
		CouponVisitor result = invoker.getResponse();
		assertEquals(coupan.CodeGet(), result.CodeGet());
		assertEquals(coupan.TypeGet(), result.TypeGet());
		assertEquals(coupan.DiscountGet(), result.DiscountGet());
	}

	@Test
	@Order(14)
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

		ArrayList<Room> rooms = reservation.getRooms();
		ArrayList<Room> resultRooms = resultOrder.getRooms();
		assertEquals(rooms.size(), resultRooms.size());
		for (int i = 0; i < rooms.size(); i++)
		{
			Room room = rooms.get(i);
			Room result = resultRooms.get(i);

			assertEquals(room.getRoomNumber(), result.getRoomNumber());
			assertEquals(room.getRoomName(), result.getRoomName());
			assertEquals(room.getPerks(), result.getPerks());
			assertEquals(room.getPrice(), result.getPrice());

			ArrayList<UserInterface> occupants = room.getOccupants();
			ArrayList<UserInterface> resultOccupants = result.getOccupants();
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
	@Order(15)
	public void checkRemoveRoomsCommand()
	{
		// Send new createRooms request
		invoker.setCommand(new RemoveRoomsCommand(rooms));
		invoker.execute();

		// Retrieve response and assert
		ArrayList<Room> resultRooms = invoker.getResponse();
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
	@Order(16)
	public void checkRemoveUserCommandOnCustomer()
	{
		// Send new customer request
		invoker.setCommand(new RemoveUserCommand(customer));
		invoker.execute();

		// Retrieve response and assert (NOTE: Removing user does not return username and password)
		UserInterface result = invoker.getResponse();
		assertFalse(result instanceof Staff);
		assertTrue(result instanceof Customer);
		assertEquals(customer.getEmail(), result.getEmail());
		assertEquals(customer.getId(), result.getId());
	}

	@Test
	@Order(17)
	public void checkRemoveUserCommandOnStaff()
	{
		// Send new customer request
		invoker.setCommand(new RemoveUserCommand(staff));
		invoker.execute();

		// Retrieve response and assert (NOTE: Removing user does not return username and password)
		UserInterface result = invoker.getResponse();
		assertFalse(result instanceof Customer);
		assertTrue(result instanceof Staff);
		assertEquals(staff.getEmail(), result.getEmail());
		assertEquals(staff.getId(), result.getId());
	}
}