package hotelsystem.requestsystem;

import java.util.Map;

/**
 * @author Jakub Pažej
 * Interface for sending and receiving requests from the GraphQL server
 * allowing for addition of new implementations to communicate with server
 */
public interface RequestInterface {
	/**
	 * Function for sending a request to the GraphQL API
	 * @param message the message being sent to the API
	 * @return the response being recieved from the API
	 */
	static Map<?, ?> sendRequest(String message) {
		return null;
	}
}