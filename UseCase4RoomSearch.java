/**
 * =============================================================================
 * MAIN CLASS - UseCase4RoomSearch
 * =============================================================================
 * 
 * Use Case 4: Room Search & Availability Check
 * 
 * Description:
 * This class demonstrates how guests
 * can view available rooms without
 * modifying inventory data.
 * 
 * The system enforces read-only access 
 * by design and usage discipline.
 * 
 * @version 4.0
 */
public class UseCase4RoomSearch {

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking Management System!");
        System.out.println("Use Case 4: Room Search & Availability Check\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize metadata rooms
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService();

        // 1. Initial search - all rooms available
        System.out.println("Test 1: Normal Search (Pre-initialized states)");
        searchService.searchAvailableRooms(inventory, single, doubleRoom, suite);

        // 2. Simulate one room type being unavailable
        System.out.println("Simulating Single Rooms becoming unavailable...");
        inventory.updateAvailability("Single Room", 0);

        // 3. Search again - should filter out Single Room
        System.out.println("\nTest 2: Search after Single Rooms are sold out");
        searchService.searchAvailableRooms(inventory, single, doubleRoom, suite);

        System.out.println("System execution completed.");
    }
}
