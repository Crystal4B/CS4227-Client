package requestsystem;

import requestsystem.interceptors.InterceptorInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reservation System handling HttpClient requests and responses
 * @author Marcin Sęk
 */
public class RequestDispatcher
{
	private static Map<String, List<InterceptorInterface>> commandToInterceptorMap = new TreeMap<>();

	/**
	 * Interceptor for sending and receiving requests from the GraphQL server
	 * @param message the message being sent to the server
	 */

	public static String getRequest(String message)
	{
		Pattern pattern = Pattern.compile("((?<=mutation)|(?<=query\\{)).\\w+");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find())
		{
			return matcher.group();
		}
		return null;
	}

	public static Map<?, ?> sendRequest(String message)
	{
		String event = getRequest(message);
		runPreHandles(event);
		Map<?, ?> response = RequestClient.sendRequest(message);
		runPostHandles(event, response);
		return response;
	}

	public static void register(String event, InterceptorInterface interceptor)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.getOrDefault(event, new ArrayList<>());
		if(!interceptorList.isEmpty()) {
			interceptorList.add(interceptor);
			commandToInterceptorMap.put(event, interceptorList);
		}
	}

	public static void remove(String event, InterceptorInterface interceptor)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.getOrDefault(event, new ArrayList<>());
		if(!interceptorList.isEmpty()) {
			interceptorList.remove(interceptor);
			commandToInterceptorMap.put(event, interceptorList);
		}
	}

	private static void runPreHandles(String event){
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(interceptorList!=null && !interceptorList.isEmpty()) {
			for (InterceptorInterface i : interceptorList) {
				i.preHandle(event);
			}
		}
	}

	private static void runPostHandles(String event, Map<?, ?> response)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(interceptorList!=null && !interceptorList.isEmpty()) {
			for (InterceptorInterface i : interceptorList) {
				i.postHandle(response, event);
			}
		}
	}

	public static void runAfterCompletions(String event)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(interceptorList!=null && !interceptorList.isEmpty()) {
			for (InterceptorInterface i : interceptorList) {
				i.afterCompletion();
			}
		}
	}
}