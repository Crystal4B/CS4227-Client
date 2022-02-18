//Developed by Jakub Pažej - 18260179@studentmail.ul.ie
public abstract class Room
{
    protected String roomName, perks;                              // name of room as String
    protected int roomNumber, numberBeds;                          // room number and number of beds in it as int
    protected double price;
    public abstract void setRoomName(String roomName);
    public abstract String getRoomName();
    public abstract void setPerks(String perks);
    public abstract String getPerks();
    public abstract void setRoomNumber(int roomNumber);
    public abstract int getRoomNumber();
    public abstract void setNumberBeds(int numberBeds);
    public abstract int getNumberBeds();
    public abstract void setPrice(double price);
    public abstract double getPrice();
}