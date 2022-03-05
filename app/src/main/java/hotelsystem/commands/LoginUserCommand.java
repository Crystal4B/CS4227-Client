package hotelsystem.commands;

import hotelsystem.user.User;
import hotelsystem.ReservationSystem;

public class LoginUserCommand implements Command
{
	private User user;

	public LoginUserCommand(User user)
	{
		this.user = user;
	}

	@Override
	public void execute()
	{
		String message = String.format("{\"query\":\"query{loginUser(input:{email: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", user.getEmail(), user.getPassword());
		ReservationSystem.sendRequest(message);
	}

	@Override
	public void undo()
	{
		// Signout
	}
	
}
