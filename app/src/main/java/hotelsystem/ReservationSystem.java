package hotelsystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Reservation System handling HttpClient requests
 * @author Marcin Sęk
 */
public class ReservationSystem
{
	private static HttpClient client = HttpClient.newHttpClient();

	public static Map<String, Object> sendRequest(String message)
	{
		try
		{
			HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://crys4b.dev:8080/graphql"))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(message))
				.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			System.out.println(response.body());

			Map<String, Map<String,Object>> mapping = new ObjectMapper().readValue(response.body(), HashMap.class);
			if (mapping.containsKey("data"))
			{
				return mapping.get("data");
			}
			else if(mapping.containsKey("errors"))
			{
				// TODO: handle errors
			}
		}
		catch(URISyntaxException | IOException | InterruptedException e)
		{
			System.out.println(e);
		}

		return null;
	}
}