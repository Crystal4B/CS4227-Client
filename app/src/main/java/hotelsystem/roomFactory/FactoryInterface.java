package hotelsystem.roomFactory;

/**
 * @author Jakub Pažej
 * Interface for factory for abstract factory implementation.
 */

public interface FactoryInterface
{
    static Room createStandard(int roomNumber, int numberBeds) {return null;}
    static Room createDeluxe(int roomNumber, int numberBeds) {return null;}
    static Room createVIP(int roomNumber, int numberBeds) {return null;}
}