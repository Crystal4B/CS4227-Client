package hotelsystem;

/**
 * A Macro command class for executing multiple commands together
 * @author Marcin Sęk
 */
public class MacroReservationCommand implements Command
{
	private Command commands[];

	/**
	 * Macro Command Constructor
	 * @param commands an Array of commands to execute
	 */
	public MacroReservationCommand(Command commands[])
	{
		this.commands = commands;
	}

	@Override
	public void execute()
	{
		for (Command command : commands)
		{
			command.execute();
		}
	}
}
