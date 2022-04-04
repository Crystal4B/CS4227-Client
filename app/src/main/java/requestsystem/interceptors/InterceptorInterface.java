package requestsystem.interceptors;

import java.util.Map;

/**
 * @author Jakub Pažej
 * Interface for Interceptors
 */

public interface InterceptorInterface {
	void preHandle(String command);
	void postHandle(Map<?, ?> response, String command);
	void afterCompletion();
}