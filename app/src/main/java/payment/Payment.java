package payment;

import hotelsystem.room.Standard;
import order.Order;
import java.util.ArrayList;

public class Payment {
    private final ArrayList<Standard> rooms;
    private final double finalCost;
   
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

