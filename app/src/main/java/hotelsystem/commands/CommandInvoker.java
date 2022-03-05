package hotelsystem.commands;

/**
 * Command invoker for controlling commands
 * @author Marcin Sęk
 */
public class CommandInvoker
{
	private boolean commandExecuted;
	private CommandTemplate<?> command;

	/**
	 * function for setting the command in the invoker
	 * @param newCommand
	 */
	public void setCommand(CommandTemplate<?> newCommand)
	{
		commandExecuted = false;
		command = newCommand;
	}

	/**
	 * function for executing the command in the invoker
	 */
	public void execute()
	{
		commandExecuted = true;
		command.execute();
	}

	/**
	 * function for undoing the action of the previously executed command
	 * @return boolean value true: command undone, false: command can't be undone
	 */
	public boolean undo()
	{
		if (commandExecuted)
		{
			command.undo();
			return true;
		}
		return false;
	}

	/**
	 * function to get the response from the commands execution
	 * @param <T> The expected type of response [Must match command response type]
	 * @return The server response parsed into its object
	 */
	public <T> T getResponse()
	{
		System.out.println(command.getClass().getSimpleName());
		return (T) command.getResponse();
	}
}
