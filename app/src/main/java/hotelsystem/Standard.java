//Developed by Jakub Pažej - 18260179@studentmail.ul.ie

package hotelsystem;

public class Standard extends Room
{
    String defaultPerks = "WiFi, TV, Toilet, Shower";
    Standard(String roomName, int roomNumber, int numberBeds)
    {
        setRoomName(roomName);
        setPerks(defaultPerks);
        setRoomNumber(roomNumber);
        setNumberBeds(numberBeds);
        setPrice(200.00);
    }

    Standard(String roomName, int roomNumber, int numberBeds, double price)
    {
        setRoomName(roomName);
        setPerks(defaultPerks);
        setRoomNumber(roomNumber);
        setNumberBeds(numberBeds);
        setPrice(price);
    }
    @Override public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }
    @Override public String getRoomName()
    {
        return roomName;
    }
    @Override public void setPerks(String perks)
    {
        this.perks = perks;
    }
    @Override public String getPerks()
    {
        return perks;
    }
    @Override public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    @Override public int getRoomNumber()
    {
        return roomNumber;
    }
    @Override public void setNumberBeds(int numberBeds)
    {
        this.numberBeds = numberBeds;
    }
    @Override public int getNumberBeds()
    {
        return numberBeds;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
    public double getPrice()
    {
        return price;
    }
}