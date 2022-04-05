package hotelsystem.requestsystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import hotelsystem.Config;

/**
 * Reservation System handling HttpClient requests and responses
 * @author Marcin Sęk
 */
public class RequestClient
{
	private static final HttpClient client = HttpClient.newHttpClient();

	/**
	 * Function for sending and receiving requests from the GraphQL server
	 * @param message the message being sent to the server
	 * @return the data received in the response from the server, null if error occurred
	 */
	public static Map<?, ?> sendRequest(String message)
	{
		try
		{
			HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(Config.getProperty(Config.SERVER_IP)))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(message))
				.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Map<?, ?> mapping = new ObjectMapper().readValue(response.body(), HashMap.class);
			if (mapping.containsKey("errors"))
			{
				List<?> errorList = (List<?>) mapping.get("errors");
				for (Object o : errorList) {
					Map<?, ?> errorMap = (Map<?, ?>) o;
					System.out.println((String) errorMap.get("message"));
				}
			}
			if (mapping.containsKey("data"))
			{
				return (Map<?, ?>) mapping.get("data");
			}
		}
		catch(URISyntaxException | IOException | InterruptedException e)
		{
			System.out.println(e);
		}

		return null;
	}
}