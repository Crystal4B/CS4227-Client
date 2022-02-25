package hotelsystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import order.Order;

public class ReservationSystem
{
	private static HttpClient client = HttpClient.newHttpClient();

	/**
	 * Create new Reservation
	 * @param order the order reservation being made
	 * @return boolean value showing success or failure of action
	 */
	public static boolean createReservation(Order order)
	{
		String message = "{\"query\":\" query { bookById(id: \"book-1\"){id}}}";

		try
		{
			HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://crys4b.dev:8080/graphql"))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(message))
				.build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.body());
		}
		catch(URISyntaxException | IOException | InterruptedException e)
		{
			System.out.println(e);
		}
		return false;
	}

	/**
	 * Cancel an existing Reservation
	 * @param order the order reservation being cancelled
	 * @return boolean value showing success or failure of action
	 */
	public static boolean cancelReservation(Order order)
	{
		return false;
	}
}
