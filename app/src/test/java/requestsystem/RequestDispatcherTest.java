package requestsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import hotelsystem.order.OrderBuilder;
import hotelsystem.requestsystem.RequestDispatcher;
import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.requestsystem.commands.reservations.CancelReservationCommand;
import hotelsystem.requestsystem.commands.reservations.CreateReservationCommand;
import hotelsystem.requestsystem.commands.reservations.GetReservationsByUserCommand;
import hotelsystem.requestsystem.commands.reservations.SelectReservationCommand;
import hotelsystem.requestsystem.commands.reservations.UpdateReservationPaidCommand;
import hotelsystem.requestsystem.commands.rooms.CreateRoomCommand;
import hotelsystem.requestsystem.commands.rooms.CreateRoomsCommand;
import hotelsystem.requestsystem.commands.rooms.GetAllRoomsCommand;
import hotelsystem.requestsystem.commands.rooms.GetAvailableRoomsCommand;
import hotelsystem.requestsystem.commands.rooms.RemoveRoomCommand;
import hotelsystem.requestsystem.commands.rooms.RemoveRoomsCommand;
import hotelsystem.requestsystem.commands.users.ChangeUserPasswordCommand;
import hotelsystem.requestsystem.commands.users.GetAllStaffUsersCommand;
import hotelsystem.requestsystem.commands.users.LoginUserCommand;
import hotelsystem.requestsystem.commands.users.RegisterUserCommand;
import hotelsystem.requestsystem.commands.users.RemoveUserCommand;
import hotelsystem.roomFactory.Room;
import hotelsystem.roomFactory.RoomFactory;
import hotelsystem.userFactory.Customer;
import hotelsystem.userFactory.UserFactory;
import hotelsystem.userFactory.UserInterface;

public class RequestDispatcherTest
{
	private void validateMessageEvent(CommandTemplate<?> command, String expectedEvent)
	{
		String message = command.createMessage(false);
		String actualEvent = RequestDispatcher.getRequest(message);
		assertEquals(expectedEvent, actualEvent);
	}

	@Test
	public void CreateReservationCommandMessage()
	{
		// Create room for order
		Room room = RoomFactory.createStandard(-1, 2);
		room.addOccupant(UserFactory.createGuest("Joe", "Test", -1));

		Customer customer = (Customer) UserFactory.createCustomer("test", "pass", "test@test");

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(Timestamp.valueOf("2020-10-11 10:00:00"));
		builder.setEndDate(Timestamp.valueOf("2020-10-21 10:00:00"));
		builder.addRoom(room);
		builder.setUser(customer);

		hotelsystem.order.Order order = builder.getOrder();

		CreateReservationCommand command = new CreateReservationCommand(order);
		validateMessageEvent(command, CreateReservationCommand.MUTATION_NAME);
	}

	@Test
	public void CancelReservationCommandMessage()
	{
		// Create room for order
		Room room = RoomFactory.createStandard(-1, 2);
		room.addOccupant(UserFactory.createGuest("Joe", "Test", -1));

		Customer customer = (Customer) UserFactory.createCustomer("test", "pass", "test@test");

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(Timestamp.valueOf("2020-10-11 10:00:00"));
		builder.setEndDate(Timestamp.valueOf("2020-10-21 10:00:00"));
		builder.addRoom(room);
		builder.setUser(customer);

		hotelsystem.order.Order order = builder.getOrder();

		CancelReservationCommand command = new CancelReservationCommand(order);
		validateMessageEvent(command, CancelReservationCommand.MUTATION_NAME);
	}

	@Test
	public void GetReservationByUserCommandMessage()
	{
		Customer customer = (Customer) UserFactory.createCustomer("test", "pass", "test@test");

		GetReservationsByUserCommand command = new GetReservationsByUserCommand(customer);
		validateMessageEvent(command, GetReservationsByUserCommand.QUERY_NAME);
	}

	@Test
	public void SelectReservationCommandMessage()
	{
		SelectReservationCommand command = new SelectReservationCommand("1");
		validateMessageEvent(command, SelectReservationCommand.QUERY_NAME);
	}

	@Test
	public void UpdateReservationPaidCommandMessage()
	{
		UpdateReservationPaidCommand command = new UpdateReservationPaidCommand(1, true);
		validateMessageEvent(command, UpdateReservationPaidCommand.MUTATION_NAME);
	}

	@Test
	public void CreateRoomCommandMessage()
	{
		Room room = RoomFactory.createStandard(-1, 2);
		room.addOccupant(UserFactory.createGuest("Joe", "Test", -1));

		CreateRoomCommand command = new CreateRoomCommand(room);
		validateMessageEvent(command, CreateRoomCommand.MUTATION_NAME);
	}

	@Test
	public void CreateRoomsCommandMessage()
	{
		List<Room> rooms = new ArrayList<>();
		rooms.add(RoomFactory.createStandard(-1, 2));
		rooms.add(RoomFactory.createDeluxe(-1, 2));

		CreateRoomsCommand command = new CreateRoomsCommand(rooms);
		validateMessageEvent(command, CreateRoomsCommand.MUTATION_NAME);
	}

	@Test
	public void GetAllRoomsCommandMessage()
	{
		GetAllRoomsCommand command = new GetAllRoomsCommand();
		validateMessageEvent(command, GetAllRoomsCommand.QUERY_NAME);
	}

	@Test
	public void GetAvailableRoomsCommandMessage()
	{
		GetAvailableRoomsCommand command = new GetAvailableRoomsCommand(Timestamp.valueOf("2020-10-10 10:00:00"), Timestamp.valueOf("2020-10-10 10:00:00"));
		validateMessageEvent(command, GetAvailableRoomsCommand.QUERY_NAME);
	}

	@Test
	public void RemoveRoomCommandMessage()
	{
		Room room = RoomFactory.createStandard(1, 2);
		RemoveRoomCommand command = new RemoveRoomCommand(room);
		validateMessageEvent(command, RemoveRoomCommand.MUTATION_NAME);
	}

	@Test
	public void RemoveRoomsCommandMessage()
	{
		List<Room> rooms = new ArrayList<>();
		rooms.add(RoomFactory.createStandard(-1, 2));
		rooms.add(RoomFactory.createDeluxe(-1, 2));

		RemoveRoomsCommand command = new RemoveRoomsCommand(rooms);
		validateMessageEvent(command, RemoveRoomsCommand.MUTATION_NAME);
	}

	@Test
	public void ChangeUserPasswordCommandMessage()
	{
		ChangeUserPasswordCommand command = new ChangeUserPasswordCommand(1, "new password");
		validateMessageEvent(command, ChangeUserPasswordCommand.MUTATION_NAME);
	}

	@Test
	public void GetAllStaffUsersCommandMessage()
	{
		GetAllStaffUsersCommand command = new GetAllStaffUsersCommand();
		validateMessageEvent(command, GetAllStaffUsersCommand.QUERY_NAME);
	}

	@Test
	public void LoginUserCommandMessage()
	{
		UserInterface user = UserFactory.createCustomer("test", "pass", "test@test.com");
		LoginUserCommand command = new LoginUserCommand(user);
		validateMessageEvent(command, LoginUserCommand.QUERY_NAME);
	}

	@Test
	public void RegisterUserCommandMessage()
	{
		UserInterface user = UserFactory.createCustomer("test", "pass", "test@test.com");
		RegisterUserCommand command = new RegisterUserCommand(user);
		validateMessageEvent(command, RegisterUserCommand.MUTATION_NAME);
	}

	@Test
	public void RemoveUserCommandMessage()
	{
		UserInterface user = UserFactory.createCustomer("test", "pass", "test@test.com");
		RemoveUserCommand command = new RemoveUserCommand(user);
		validateMessageEvent(command, RemoveUserCommand.MUTATION_NAME);
	}
}
