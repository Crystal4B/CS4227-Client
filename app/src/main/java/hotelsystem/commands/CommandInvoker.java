package hotelsystem.commands;

/**
 * Command invoker for controlling commands
 * @author Marcin Sęk
 */
public class CommandInvoker
{
	private CommandTemplate command;

	public void setCommand(CommandTemplate newCommand)
	{
		command = newCommand;
	}

	public void execute()
	{
		command.execute();
	}

	public Object getResponse()
	{
		return command.getResponse();
	}
}
