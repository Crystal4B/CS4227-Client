//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem.room;

public class Deluxe extends Standard
{
    public Deluxe(String roomName, int roomNumber, int numberBeds)
    {
        super(roomName, roomNumber, numberBeds);
        String temp = getPerks();
        setPerks("Free Breakfast, Minibar, Bath, "+temp);
        setPrice(500.00);
    }
}