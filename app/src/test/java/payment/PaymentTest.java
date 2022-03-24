/**
 * @author Jordan Marshall
 */

package payment;

import org.junit.jupiter.api.Test;

import hotelsystem.room.Standard;
import hotelsystem.user.Customer;
import hotelsystem.user.Guest;
import order.OrderBuilder;
import requestsystem.commands.CommandInvoker;
import requestsystem.commands.CreateReservationCommand;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    static Timestamp startDate = Timestamp.valueOf(LocalDateTime.now());
	static Timestamp endDate = Timestamp.valueOf(LocalDateTime.now().plusDays(2));
    static Customer customer = new Customer("testCustomer", "customer_password", "testCustomer@test.com");

    static CommandInvoker invoker = new CommandInvoker();

    // Payment Unit Tests

    @Test void payCreditCard(){
        Payment payment = new Payment();
        ArrayList<Standard> rooms = new ArrayList<>();
        rooms.add(new Standard("Test Name", 123, 2));
        

        Standard room = rooms.get(0);
		room.addOccupant(new Guest("Joe", "Stephan", -1));

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(startDate);
		builder.setEndDate(endDate);
		builder.addRoom(room);
		builder.setUser(customer);

		order.Order order = builder.getOrder();

		// Send new createRooms request
		invoker.setCommand(new CreateReservationCommand(order));
		invoker.execute();

		// Retrieve response and assert
		order.Order resultOrder = invoker.getResponse();
        
        int orderId = Integer.parseInt(resultOrder.getOrderID());
        assertTrue(payment.payCreditCard(orderId,"1123342534522342"," Eoin McDonough" ,0425,123));
    }

    @Test void isValidNum(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardNo", "1123342534522342"));
    }
    @Test void isValidDate(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardDate", 1405));
    }
    @Test void isValiddCSV(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardBack", 123));
    }
}