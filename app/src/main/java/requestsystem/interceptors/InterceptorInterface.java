package requestsystem.interceptors;

import java.util.Map;

/**
 * @author Jakub Pažej
 * Interface for reservation System handling HttpClient requests and responses
 */

public interface InterceptorInterface {
	void preHandle(String command);
	void postHandle(Map<?, ?> response, String command);
	void afterCompletion();
}