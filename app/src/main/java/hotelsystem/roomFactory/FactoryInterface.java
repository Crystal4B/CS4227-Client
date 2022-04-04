package hotelsystem.roomFactory;

/**
 * @author Jakub Pažej
 * Interface for factory for abstract factory implementation.
 */
public interface FactoryInterface
{
    /**
     * Creates a Standard Room.
     * @param numberBeds number of beds in the room
     * @param roomNumber room number
     * @return a Room object
     */
    static Room createStandard(int roomNumber, int numberBeds) {return null;}

    /**
     * Creates a Deluxe Room.
     * @param numberBeds number of beds in the room
     * @param roomNumber room number
     * @return a Room object
     */
    static Room createDeluxe(int roomNumber, int numberBeds) {return null;}

    /**
     * Creates a VIP Room.
     * @param numberBeds number of beds in the room
     * @param roomNumber room number
     * @return a Room object
     */
    static Room createVIP(int roomNumber, int numberBeds) {return null;}
}