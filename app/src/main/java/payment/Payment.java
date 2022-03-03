package payment;

import order.Order;
import java.util.ArrayList;
import hotelsystem.Room;
import com.stripe.model.PaymentIntent;

public class Payment {
    private final ArrayList<Room> rooms;
    private final double finalCost;
    Stripe.apiKey = "sk_test_51KYs71FRdfb2B5MVANRv9o3S86v3qXEkhIbrfBPO9q7DhWMYypurCCoxNkfc4VxuBxc3FQk2UYRlk5tPmshVoowu00agNDgFoN";
   
    public Payment(Order order) {
        rooms = order.getRooms();
        finalCost = order.getFinalCost();
    }

    public String toString() {
        return "Ordering " + rooms.size() + " rooms for " + finalCost +"." ;
    }

    //TODO
    /*
    Order object
    Order.getfinalprice
    */
}

