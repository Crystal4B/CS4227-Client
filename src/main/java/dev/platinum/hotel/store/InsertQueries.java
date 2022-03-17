package dev.platinum.hotel.store;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import dev.platinum.hotel.types.Reservation;
import dev.platinum.hotel.types.Room;
import dev.platinum.hotel.types.User;

/**
 * Store class for hanadling insert queries
 * @author Marcin Sęk
 */
public class InsertQueries extends StoreComponent
{
	/**
	 * Function for inserting a reservation into the system
	 * @param incomingReservation the Reservation Object being added
	 * @return A Reservation object with its database ids
	 */
	public static Reservation insertReservation(Reservation incomingReservation)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Statement statement = connection.createStatement();
			String insertReservation = "INSERT INTO " + RESERVATIONS_TABLE_NAME + "(" + CHECK_IN_COLUMN + "," + CHECK_OUT_COLUMN + "," + ROOM_ID_COLUMN + ") VALUES('" + sdf.format(incomingReservation.getCheckIn()) + "', '" + sdf.format(incomingReservation.getCheckOut()) + "', '" + 1 + "')"; //TODO: UPDATE QUERY TO FIT NEW REQUIREMENTS

			statement.execute(insertReservation);
			connection.commit();

			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next())
			{
				incomingReservation.setId((int) keys.getLong(1));
				return incomingReservation;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Function for inserting rooms into the system
	 * @param rooms a list of rooms being added to the system
	 * @return updated list of rooms where all Room objects got their database ids
	 */
	public static List<Room> insertRooms(List<Room> rooms)
	{
		try
		{
			Statement statement = connection.createStatement();

			for (int i = 0; i < rooms.size(); i++)
			{
				Room room = rooms.get(i);

				String insertRooms = "INSERT INTO " + ROOMS_TABLE_NAME + "(" + TYPE_COLUMN + "," + PERKS_COLUMN + "," + NUMBER_OF_BEDS_COLUMN + "," + RATE_COLUMN + ") VALUES('" + room.getType() + "', '" + room.getPerks() + "', '" + room.getNumberOfBeds() + "'," + room.getRate() + ")";
				statement.addBatch(insertRooms);
			}

			statement.executeBatch();
			connection.commit();

			// generatedKeys holds last key inserted
			ResultSet keys = statement.getGeneratedKeys();
			int lastKey = -1;
			while (keys.next())
			{
				lastKey = (int) keys.getLong(1);
			}

			// as No IDs are specified in create query we can calculate generated IDS based on lastKey inserted and number of Rooms inserted
			for (int i = rooms.size() - 1; i >= 0; i--)
			{
				rooms.get(i).setId(lastKey - (rooms.size() - 1 - i));
			}
			return rooms;
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Function for adding a user into the system
	 * @param user the user being added into the system
	 * @return a updated user object with its database id
	 */
	public static User insertUser(User user)
	{
		try
		{
			String insertUser = "INSERT INTO " + USERS_TABLE_NAME + "(" + TYPE_COLUMN + "," + EMAIL_COLUMN + "," + USERNAME_COLUMN + "," + PASSWORD_COLUMN + ") VALUES('" + user.getType() + "', '" + user.getEmail() + "', '" + user.getUsername() + "', '" + user.getPassword() + "')";

			Statement statement = connection.createStatement();
			statement.execute(insertUser);
			connection.commit();

			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next())
			{
				user.setId((int) keys.getLong(1));
				return user;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}

		return null;
	}

	/**
	 * Function for inserting a room into the system
	 * @param room being added to the system
	 * @return inserted Room object
	 */
	public static Room insertRoom(Room room)
	{
		try
		{
			Statement statement = connection.createStatement();

			String insertRoom = "INSERT INTO " + ROOMS_TABLE_NAME + "(" + TYPE_COLUMN + "," + PERKS_COLUMN + "," + NUMBER_OF_BEDS_COLUMN + "," + RATE_COLUMN + ") VALUES('" + room.getType() + "', '" + room.getPerks() + "', '" + room.getNumberOfBeds() + "'," + room.getRate() + ")";

			statement.execute(insertRoom);
			connection.commit();

			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next())
			{
				room.setId((int) keys.getLong(1));
				return room;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return null;
	}
}
