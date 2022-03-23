package requestsystem.commands;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hotelsystem.room.Standard;
import hotelsystem.user.Guest;
import order.Order;
import order.OrderBuilder;

public class UpdateReservationPaidCommand extends CommandTemplate<Order>
{
    private static final String MUTATION = "updateReservationPaid";

    private int id;
    private boolean paid;

    public UpdateReservationPaidCommand(int id, boolean paid)
    {
        this.id = id;
        this.paid = paid;
    }

    @Override
    public String createMessage(boolean undo)
    {
        boolean paidVal = undo ? !paid : paid;
		return String.format("{\"query\":\"mutation{%s(input:{id: %d paid: %b}){id checkIn checkOut paid user{id type} guests{id firstName lastName room{id type}}}}\"}", MUTATION, id, paidVal);
    }

    @Override
    public void parseResponse(Map<String, Object> response)
    {
        Map<String, Object> reservationData = (Map<String, Object>) response.get(MUTATION);
        OrderBuilder builder = new OrderBuilder();

        builder.setOrderID((String)reservationData.get("id"));
        builder.setStartDate(Timestamp.valueOf((String)reservationData.get("checkIn")));
        builder.setEndDate(Timestamp.valueOf((String)reservationData.get("checkOut")));

        ArrayList<Standard> rooms = new ArrayList<>();
        
        List<Map<String, Object>> guestsMap = (List<Map<String, Object>>) reservationData.get("guests");
        for (Map<String, Object> map : guestsMap)
        {
            Map<String, Object> roomData = (Map<String,Object>) map.get("room");
            int id = Integer.parseInt((String) roomData.get("id"));

            Standard standardRoom = null;

            boolean found = false;
            for (Standard room : rooms)
            {
                if (room.getRoomNumber() == id)
                {
                    standardRoom = room;
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                String type = (String) roomData.get("type");
                int numberOfBeds = Integer.parseInt((String) roomData.get("numberOfBeds"));
                standardRoom = new Standard(type, id, numberOfBeds);
            }

            int guestId = Integer.parseInt((String) map.get("id"));
            String firstName = (String) map.get("firstName");
            String lastName = (String) map.get("lastName");

            standardRoom.addOccupant(new Guest(firstName, lastName, guestId));
        }

        builder.setRooms(rooms);
        responseObject = builder.getOrder();
    }
}
