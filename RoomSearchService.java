import java.util.Map;

/**
 * =============================================================================
 * CLASS - RoomSearchService
 * =============================================================================
 * 
 * Use Case 4: Room Search & Availability Check
 * 
 * Description:
 * This class provides search functionality
 * for guests to view available rooms.
 * 
 * It reads room availability from inventory
 * and room details from Room objects.
 * 
 * No inventory mutation or booking logic
 * is performed in this class.
 * 
 * @version 4.0
 */
public class RoomSearchService {

    /**
     * Displays available rooms along with 
     * their details and pricing.
     * 
     * This method performs read-only access 
     * to inventory and room data.
     * 
     * @param inventory centralized room inventory
     * @param singleRoom single room definition
     * @param doubleRoom double room definition
     * @param suiteRoom suite room definition
     */
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        System.out.println("--- Searching for Available Rooms ---\n");
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check and display Single Room availability
        if (availability.getOrDefault("Single Room", 0) > 0) {
            singleRoom.displayRoomDetails();
            System.out.println(" - Current Availability: " + availability.get("Single Room"));
            System.out.println();
        }

        // Check and display Double Room availability
        if (availability.getOrDefault("Double Room", 0) > 0) {
            doubleRoom.displayRoomDetails();
            System.out.println(" - Current Availability: " + availability.get("Double Room"));
            System.out.println();
        }

        // Check and display Suite Room availability
        if (availability.getOrDefault("Suite Room", 0) > 0) {
            suiteRoom.displayRoomDetails();
            System.out.println(" - Current Availability: " + availability.get("Suite Room"));
            System.out.println();
        }
    }
}
