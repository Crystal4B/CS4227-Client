package hotelsystem;

/**
 * Reservation System class for organizing and controlling the reservations
 * @author Marcin Sęk
 */
public class ReservationSystem
{
	private static Command command;

	public static void setCommand(Command newCommand)
	{
		command = newCommand;
	}

	public static void execute()
	{
		command.execute();
	}
}
