package hotelsystem.commands;

/**
 * Reservation System class for organizing and controlling the reservations
 * @author Marcin Sęk
 */
public class ReservationInvoker
{
	private Command command;

	public void setCommand(Command newCommand)
	{
		command = newCommand;
	}

	public void execute()
	{
		command.execute();
	}
}
