package hotelsystem.commands;

import java.sql.Timestamp;

import hotelsystem.ReservationSystem;

public class GetAvailableRoomsCommand implements Command
{
	Timestamp arrivalDate;
	Timestamp departureDate;

	public GetAvailableRoomsCommand(Timestamp arrivalDate, Timestamp departureDate)
	{
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	@Override
	public void execute()
	{
		String message = String.format("{\"query\":\"query{availableRoomsByDates(arrivalDate: \\\"%s\\\" departureDate: \\\"%s\\\"){id name perks numberOfBeds rate}}\"}", arrivalDate, departureDate);
		ReservationSystem.sendRequest(message);
	}

	@Override
	public void undo()
	{
		// Undo does not apply to this type of command
	}
	
}
