/**
 * @author Jordan Marshall
 * @author Eoin McDonough
 */

package payment;

import org.junit.jupiter.api.Test;

import hotelsystem.order.OrderBuilder;
import hotelsystem.payment.Payment;
import hotelsystem.requestsystem.commands.CommandInvoker;
import hotelsystem.requestsystem.commands.reservations.CreateReservationCommand;
import hotelsystem.roomfactory.Room;
import hotelsystem.roomfactory.RoomFactory;
import hotelsystem.userfactory.Customer;
import hotelsystem.userfactory.Guest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

class PaymentTest {

    static Timestamp startDate = Timestamp.valueOf(LocalDateTime.now());
	static Timestamp endDate = Timestamp.valueOf(LocalDateTime.now().plusDays(2));
    static Customer customer = new Customer("testCustomer", "customer_password", "testCustomer@test.com");

    static CommandInvoker invoker = new CommandInvoker();

    // Payment Unit Tests

    @Test void payCreditCard(){
        Payment payment = new Payment();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(RoomFactory.createStandard(123, 2));
        

        Room room = rooms.get(0);
		room.addOccupant(new Guest("Joe", "Stephan", -1));

		// Create order
		OrderBuilder builder = new OrderBuilder();
		builder.setStartDate(startDate);
		builder.setEndDate(endDate);
		builder.addRoom(room);
		builder.setUser(customer);

		hotelsystem.order.Order order = builder.getOrder();

		// Send new createRooms request
		invoker.setCommand(new CreateReservationCommand(order));
		invoker.execute();

		// Retrieve response and assert
		hotelsystem.order.Order resultOrder = invoker.getResponse();
        
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
    @Test void isValidCSV(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardBack", 123));
    }
}