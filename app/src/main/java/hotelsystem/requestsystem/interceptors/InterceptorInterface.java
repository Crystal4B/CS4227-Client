package hotelsystem.requestsystem.interceptors;

import java.util.Map;

/**
 * @author Jakub Pažej
 * Interface for Interceptors
 */
public interface InterceptorInterface {

	/**
	 * Function that runs after user send message but before passing it to server
	 * @param command the command being sent to the server
	 */
	void preHandle(String command);

	/**
	 * Function that runs after server sends a response but before passing it to the user
	 * @param command the command sent to the server
	 * @param response the response from server
	 */
	void postHandle(Map<?, ?> response, String command);

	/**
	 * Function that runs after passing the message from server back to the user
	 */
	void afterCompletion();
}