package hotelsystem.roomFactory;

/**
 * @author Jakub Pažej
 * Interface for factory for abstract factory implementation.
 */

public interface FactoryInterface
{
    static Room createStandard(String roomName, int roomNumber, int numberBeds) {return null;}
    static Room createDeluxe(String roomName, int roomNumber, int numberBeds) {return null;}
    static Room createVIP(String roomName, int roomNumber, int numberBeds) {return null;}
}