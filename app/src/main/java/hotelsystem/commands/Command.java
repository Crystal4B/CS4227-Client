package hotelsystem.commands;

/**
 * interface representing commands in the reservation system
 * @author Marcin Sęk
 */
public interface Command
{
	/**
	 * abstract execute function for commands
	 */
	public abstract void execute();

	/**
	 * abstract undo function for commands
	 */
	public abstract void undo();

	/**
	 * abstract function for getting server response from command
	 * @return Object response
	 */
	public abstract Object getResponse();
}
