package hotelsystem.requestsystem.commands;

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
	 * @param newCommand is the newCommand to be set
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
	@SuppressWarnings("unchecked")
	public <T> T getResponse()
	{
		return (T) command.getResponse();
	}
}
