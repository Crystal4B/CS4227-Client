package hotelsystem.commands;

import hotelsystem.Person;
import hotelsystem.ReservationSystem;

public class RegisterUserCommand implements Command
{
	private Person user;

	public RegisterUserCommand(Person user)
	{
		this.user = user;
	}

	@Override
	public void execute()
	{
		//TODO: replace null with user.getType once it is implemented
		String message = String.format("{\"query\":\"mutation{registerUser(input:{type: \\\"%s\\\" email: \\\"%s\\\" username: \\\"%s\\\" password: \\\"%s\\\"}){id type email username password}}\"}", null, user.getEmail(), user.getUserName(), user.getPassword());
		ReservationSystem.sendRequest(message);
	}

	@Override
	public void undo()
	{
		// TODO Auto-generated method stub
	}
}
