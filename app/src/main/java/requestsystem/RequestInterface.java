package requestsystem;

import java.util.Map;

/**
 * @author Jakub Pažej
 * Interface for sending and receiving requests from the GraphQL server
 * allowing for addition of new implementations to communicate with server
 */
public interface RequestInterface {
	static Map<?, ?> sendRequest(String message) {
		return null;
	}
}