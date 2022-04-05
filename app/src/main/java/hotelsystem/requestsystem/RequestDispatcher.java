package hotelsystem.requestsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hotelsystem.requestsystem.interceptors.InterceptorInterface;

/**
 * @author Jakub Pažej
 * Dispatcher for coordinating interceptors
 */
public class RequestDispatcher
{
	private static Map<String, List<InterceptorInterface>> commandToInterceptorMap = new TreeMap<>();

	/**
	 * Function for parsing message from user before passing it to server
	 * @param message the message being sent to the server
	 * @return the command entered by user
	 */
	public static String getRequest(String message)
	{
		Pattern pattern = Pattern.compile("((?<=mutation\\{)|(?<=query\\{)).\\w+");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find())
		{
			return matcher.group();
		}
		return null;
	}

	/**
	 * Function that runs pre- and post-handles, afterCompletion is then ran in CommandTemplate
	 * after the server response is sent back to the user
	 * @param message Message to send
	 * @return Response
	 */
	public static Map<?, ?> sendRequest(String message)
	{
		String event = getRequest(message);
		runPreHandles(event);
		Map<?, ?> response = RequestClient.sendRequest(message);
		runPostHandles(event, response);
		return response;
	}

	/**
	 * Function for registering interceptors to commands
	 * @param event is the command
	 * @param interceptor is the interceptor
	 */
	public static void register(String event, InterceptorInterface interceptor)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.getOrDefault(event, new ArrayList<>());
		if(!interceptorList.contains(interceptor)) {
			interceptorList.add(interceptor);
			commandToInterceptorMap.put(event, interceptorList);
		}
	}

	/**
	 * Function for removing interceptors from commands
	 * @param event is the command
	 * @param interceptor is the interceptor
	 */
	public static void remove(String event, InterceptorInterface interceptor)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.getOrDefault(event, new ArrayList<>());
		if(!interceptorList.contains(interceptor)) {
			interceptorList.remove(interceptor);
			commandToInterceptorMap.put(event, interceptorList);
		}
	}

	/**
	 * Function for running all the preHandles of interceptors registered to certain command
	 * @param event is the command
	 */
	private static void runPreHandles(String event){
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(interceptorList!=null && !interceptorList.isEmpty()) {
			for (InterceptorInterface i : interceptorList) {
				i.preHandle(event);
			}
		}
	}

	/**
	 * Function for running all the postHandles of interceptors registered to certain command
	 * @param event is the command
	 */
	private static void runPostHandles(String event, Map<?, ?> response)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(interceptorList!=null && !interceptorList.isEmpty()) {
			for (InterceptorInterface i : interceptorList) {
				i.postHandle(response, event);
			}
		}
	}

	/**
	 * Function for running all the afterCompletions of interceptors registered to certain command
	 * @param event is the command
	 */
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