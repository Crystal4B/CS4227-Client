package requestsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import hotelsystem.Config;
import requestsystem.interceptors.InterceptorInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reservation System handling HttpClient requests and responses
 * @author Marcin Sęk
 */
public class RequestDispatcher
{
	private static Map<String, List<InterceptorInterface>> commandToInterceptorMap;
	private static Map<?, ?> response;

	/**
	 * Interceptor for sending and receiving requests from the GraphQL server
	 * @param message the message being sent to the server
	 */

	public void main(String message, String event){
		getRequest(message);
		runPreHandles(event);
		sendRequest(message);
		runPostHandles(event);
	}

	public static String getRequest(String message)
	{
		Pattern pattern = Pattern.compile("((?<=mutation:)|(?<=:query)).\\w+");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find())
		{
			return matcher.group();
		}
		return null;
	}

	public static Map<?, ?> sendRequest(String message)
	{
		response = RequestClient.sendRequest(message);
		return response;
	}

	public static void register(String event, InterceptorInterface interceptor)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.getOrDefault(event, new ArrayList<>());
		if(!interceptorList.contains(interceptor)) {
			interceptorList.add(interceptor);
			commandToInterceptorMap.put(event, interceptorList);
		}
	}

	public static void remove(String event, InterceptorInterface interceptor)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.getOrDefault(event, new ArrayList<>());
		if(interceptorList.contains(interceptor)) {
			interceptorList.remove(interceptor);
			commandToInterceptorMap.put(event, interceptorList);
		}
	}

	private static void runPreHandles(String event){
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(!interceptorList.isEmpty()) {
			for (int i = 0; i < interceptorList.size(); i++) {
				interceptorList.get(i).preHandle(event);
			}
		}
	}

	private static void runPostHandles(String event)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(!interceptorList.isEmpty()) {
			for (int i = 0; i < interceptorList.size(); i++) {
				interceptorList.get(i).postHandle(response, event);
			}
		}
	}

	private static void runAfterCompletions(String event)
	{
		List<InterceptorInterface> interceptorList = commandToInterceptorMap.get(event);
		if(!interceptorList.isEmpty()) {
			for (int i = 0; i < interceptorList.size(); i++) {
				interceptorList.get(i).afterCompletion();
			}
		}
	}
}