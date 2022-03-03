package hotelsystem.commands;

/**
 * Command invoker for controlling commands
 * @author Marcin Sęk
 */
public class CommandInvoker
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

	public Object getResponse()
	{
		return command.getResponse();
	}
}
