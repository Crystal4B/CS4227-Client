package requestsystem;

import java.util.Map;

/**
 * @author Jakub Pažej
 * Interface for reservation System handling HttpClient requests and responses
 */

public interface RequestInterface {
	/**
	 * Interface for sending and receiving requests from the GraphQL server
	 * allowing for addition of new implementations to communicate with server
	 *
	 * @param message the message being sent to the server
	 */
	static Map<?, ?> sendRequest(String message) {
		return null;
	}
}