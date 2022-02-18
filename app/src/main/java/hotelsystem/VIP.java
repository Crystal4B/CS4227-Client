//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem;

public class VIP extends Deluxe
{
    VIP(String roomName, int roomNumber, int numberBeds)
    {
        super(roomName, roomNumber, numberBeds);
        String temp = getPerks();
        setPerks("Complementary Exquisite Wine, Private Pool, Private massage table, "+temp);
        setPrice(1000.00);
    }
}