package hotelsystem;

public class App
{
	public String getGreeting()
	{
		return "Hello World!";
	}

	public static void main(String[] args)
	{
		UI myUi = new UI();
		myUi.run();
		System.out.println(new App().getGreeting());
	}
}
