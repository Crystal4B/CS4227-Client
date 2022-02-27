package hotelsystem.commands;

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

	@Override
	public void undo()
	{
		// Run from end to start to keep order of operations
		for (int i = commands.length-1; i > 0; i--)
		{
			commands[i].undo();
		}
	}
}
