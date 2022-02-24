import userinterface.UserInterface;
import order.*;

public class App
{
	public static void main(String[] args)
	{
		Director director = new Director();
		OrderBuilder builder = new OrderBuilder();
		director.constructOrder(builder);
		Order order = builder.getOrder();
		System.out.println(order.toString());

		// Run terminal-based user interface
		UserInterface ui = new UserInterface();
		ui.run();
	}
}
