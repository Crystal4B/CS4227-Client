import userinterface.UserInterface;

// import java.util.ArrayList;

// import hotelsystem.Room;
// import order.*;

public class App
{
	public static void main(String[] args)
	{	
		// Use only for testing order builder. Remove after fully implemented.
		// ArrayList<Room> rooms = new ArrayList<>();

		// Director director = new Director();
		// OrderBuilder builder = new OrderBuilder();
		// director.constructOrder(builder, rooms);
		// Order order = builder.getOrder();
		// System.out.println(order.toString());

		// Run terminal-based user interface
		UserInterface ui = new UserInterface();
		ui.run();
	}
}
