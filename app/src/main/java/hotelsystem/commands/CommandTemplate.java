package hotelsystem.commands;

import java.util.Map;

import hotelsystem.ReservationSystem;

/**
 * interface representing commands in the reservation system
 * @author Marcin Sęk
 * @param <T> The expected type of Response
 */
public abstract class CommandTemplate<T>
{
	protected T responseObject;

	/**
	 * template execute function for commands
	 */
	public void execute()
	{
		String message = createMessage(false);
		Map<String, Object> response = ReservationSystem.sendRequest(message);
		parseResponse(response);
	};

	/**
	 * template undo function for commands
	 */
	public void undo()
	{
		String message = createMessage(true);
		Map<String, Object> response = ReservationSystem.sendRequest(message);
		parseResponse(response);
	}

	/**
	 * template function for getting server response from command
	 * @return Object response
	 */
	public T getResponse()
	{
		return this.responseObject;
	}

	/**
	 * abstract createMessage function for creating a query request for the server
	 * @param undo boolean value telling the command what type of message is required
	 * @return the final message being sent to the server
	 */
	public abstract String createMessage(boolean undo);

	/**
	 * abstract parseResponse function for converting response to desired Object
	 * @param response JSON response from server as Map<String, Object>
	 */
	public abstract void parseResponse(Map<String, Object> response);
}
